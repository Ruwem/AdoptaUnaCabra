package adoptaApp.api;

import java.io.File;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import adoptaApp.entity.Cabra;
import adoptaApp.entity.Persona;
import adoptaApp.entity.Centro;
import adoptaApp.entity.Noticia;
import adoptaApp.repository.CabraRepository;
import adoptaApp.repository.PersonaRepository;
import adoptaApp.security.UserAuthComponent;


@RestController
@RequestMapping("/api/cabras")
public class CabraRestController {
	
	public interface CabraDetail extends Cabra.Basic,Noticia.NoGoats,Persona.LoginInt{}
	public interface UserDetail extends Persona.LoginInt{}
	public interface NewsDetail extends Noticia.NoGoats{}
	private static final Logger log = LoggerFactory.getLogger(LoginRestController.class);
	@Autowired
	private CabraRepository cabraServ;
	@Autowired 
	private UserAuthComponent logger;
	
	@JsonView(CabraDetail.class)
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Cabra> getCabras() {
		return cabraServ.findAll();
	}
	
	@JsonView(CabraDetail.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cabra> getCabra(@PathVariable Integer id) {

		Cabra cabra = cabraServ.findOne(id);
		if (cabra != null) {
			return new ResponseEntity<>(cabra, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@JsonView(UserDetail.class)
	@RequestMapping(value = "/{id}/owner", method = RequestMethod.GET)
	public ResponseEntity<Persona> getOwner(@PathVariable Integer id){
		Cabra cabra = cabraServ.findOne(id);
		if(cabra != null){
			if (cabra.getOwner() != null){
				return  new ResponseEntity<>(cabra.getOwner(),HttpStatus.OK);
			}
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}
	@JsonView(NewsDetail.class)
	@RequestMapping(value = "/{id}/news", method = RequestMethod.GET)
	public List<Noticia> getNews(@PathVariable Integer id){
		Cabra cabra = cabraServ.findOne(id);
		if(cabra != null){
			if (cabra.getNews() != null){
				return cabra.getNews();
			}
		}
		return null;		
	}
	
	
	@RequestMapping(value = "/{id}/isFollowing", method = RequestMethod.GET)
	public boolean isFollowing(@PathVariable Integer id, HttpSession session){
		Cabra cabra = cabraServ.findOne(id);
		if (cabra != null){
			log.info("cabra no es null");
			if(logger.isLoggedUser()){
				session.setMaxInactiveInterval(-1);
				log.info("hay un usuario loggeado");
				if(logger.getLoggedUser().getFollowing().contains(cabra)){
					log.info(logger.getLoggedUser().getNombre());
					return true;
				}
			}else{
				log.info("Hago lo que me sale de la poya y me voy por aqui");
			}
		}
		return false;
	}
	


	@JsonView(CabraDetail.class)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cabra> addCabra(@RequestBody Cabra cabra, HttpSession session) {
		if(logger.isLoggedUser()){
			session.setMaxInactiveInterval(-1);
			Cabra newCabra = new Cabra(cabra.getNombre(), cabra.getRaza(), cabra.getNacimiento(), cabra.getPrice(), cabra.getWeight(), cabra.getSexo());
			newCabra.setProfileImage("goat-pordefecto.jpg");
			newCabra.setFollowers(cabra.getFollowers());
			newCabra.setNews(cabra.getNews());
			newCabra.setOwner(logger.getLoggedUser());
			newCabra.setHome(cabra.getHome());
			cabraServ.save(newCabra);
			return new ResponseEntity<>(newCabra,HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				
	}
	
	private static final String USER_IMAGE_FOLDER = "src/main/resources/static/imgProfile";
	 @RequestMapping(value = "/{id}/image/upload",method = RequestMethod.PUT)
	 public ResponseEntity<Cabra> handleFileUpload(
	   @RequestBody MultipartFile file,
	   @PathVariable Integer id) {

	  //String fileName = file.getOriginalFilename() + ".jpg";
	  if(cabraServ.getOne(id).getOwner().equals(logger.getLoggedUser())){
	   
	   Cabra g= cabraServ.findOne(id);
	   String fileName = "goat-" + id  + ".jpg";

	   if (!file.isEmpty()) {
	    try {

	     File filesFolder = new File(USER_IMAGE_FOLDER);
	     if (!filesFolder.exists()) {
	      filesFolder.mkdirs();
	     }

	     File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
	     file.transferTo(uploadedFile);
	     
	     g.setProfileImage(fileName);
	     cabraServ.save(g);

	     return new ResponseEntity<>(g, HttpStatus.OK);


	    } catch (Exception e) {
	     return new ResponseEntity<>(g, HttpStatus.NOT_FOUND);
	    }
	   } else {
	    return new ResponseEntity<>(g, HttpStatus.NOT_FOUND);

	   }
	  }else{
	   return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	  }
	   
	 }
	
	/*@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cabra> updateCabra(@PathVariable Integer id, @RequestBody Cabra updatedCabra, HttpSession session) {

		session.setMaxInactiveInterval(-1);
		Cabra cabra = cabraServ.findOne(id);
		if ((cabra != null) && (cabra.getId() == updatedCabra.getId())) {
			cabra.setNombre(updatedCabra.getNombre());
			cabra.setRaza(updatedCabra.getRaza());
			cabra.setNacimiento(updatedCabra.getNacimiento());
			cabra.setPrice(updatedCabra.getPrice());
			cabra.setWeight(updatedCabra.getWeight());
			cabra.setSexo(updatedCabra.getSexo());
			cabra.setHome(updatedCabra.getHome());
			cabra.setOwner(updatedCabra.getOwner());
			cabra.setNombre(updatedCabra.getNombre());
			cabraServ.save(cabra);
			return new ResponseEntity<>(updatedCabra, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/
	

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cabra> deleteCabra(@PathVariable Integer id) {
		if(cabraServ.getOne(id).getOwner().equals(logger.getLoggedUser())){
			Cabra cabraSelected = cabraServ.findOne(id);
			if (cabraSelected == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				cabraServ.delete(id);
				return new ResponseEntity<>(null, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);		
	}
		
}
