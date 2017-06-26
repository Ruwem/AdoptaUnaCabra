package adoptaApp.api;

import java.io.File;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import adoptaApp.api.CabraRestController.CabraDetail;
import adoptaApp.entity.Cabra;
import adoptaApp.entity.Noticia;
import adoptaApp.entity.Persona;
import adoptaApp.repository.PersonaRepository;
import adoptaApp.security.UserAuthComponent;
import adoptaApp.services.CabraService;
import adoptaApp.services.UserService;


@RestController
@RequestMapping("/api/user")
public class PersonaRestController {
	
	
	public interface UserDetail extends Persona.Basico,Cabra.NoOwner,Noticia.NoOwner{}
	public interface CabraDetail extends Cabra.NoOwner{};
	public interface NoticiaDetail extends Noticia.NoOwner{};
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private CabraService goatServ;
	
	@Autowired
	private UserAuthComponent logger;
	
	@JsonView(UserDetail.class)
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Persona> getUsers() {
		return userServ.findAll();
	}
	
	@JsonView(UserDetail.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Persona> getUser(@PathVariable Integer id) {

		Persona user = userServ.findById(id);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(CabraDetail.class)
	@RequestMapping(value = "/{id}/favs", method = RequestMethod.GET)
	public Set<Cabra> getCabrasFavoritas(@PathVariable Integer id){
		Persona user = userServ.findById(id);
		if ( user != null){
			if(user.getFollowing() != null){
				return user.getFollowing();
			}
		}
		return null;
	}
	@JsonView(CabraDetail.class)
	@RequestMapping(value = "/{id}/goats", method = RequestMethod.GET)
	public Set<Cabra> getCabras(@PathVariable Integer id){
		Persona user = userServ.findById(id);
		if ( user != null){
			if(user.getFollowing() != null){
				return user.getCabras();
			}
		}
		return null;		
	}
	@JsonView(NoticiaDetail.class)
	@RequestMapping(value = "/{id}/news", method = RequestMethod.GET)
	public List<Noticia> getNews(@PathVariable Integer id){
		Persona user = userServ.findById(id);
		if ( user != null){
			if(user.getNews() != null){
				return user.getNews();
			}
		}
		return null;		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Persona addUser(@RequestBody Persona user, HttpSession session) {
		session.setMaxInactiveInterval(-1);
		Persona newUser = new Persona(user.getNombre(),user.getApellidos(),user.getCorreo(),user.getPasswordHash(),"ROLE_USER");
		userServ.save(newUser);
		return newUser;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Persona> updateUser(@PathVariable Integer id, @RequestBody Persona updatedUser, HttpSession session) {

		session.setMaxInactiveInterval(-1);
		Persona user = userServ.findById(id);
		if ((user != null) && (user.getId() == updatedUser.getId())) {
			user = userServ.updatePersona(user, updatedUser);
			userServ.save(user);;
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Persona> deleteUser(@PathVariable Integer id) {
		Persona userSelected = userServ.findById(id);
		if (userSelected == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			userServ.delete(userSelected);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
	
	@JsonView(UserDetail.class)
	@RequestMapping(value = "/cabra/{id}/follow", method = RequestMethod.PUT)
	public ResponseEntity<Persona> followGoat(@PathVariable Integer id, HttpSession session){
		session.setMaxInactiveInterval(-1);
		if(goatServ.findOne(id) != null){	
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(logger.isLoggedUser()){
			if(!userServ.findById(logger.getIdLoggedUser()).getFollowing().contains(goatServ.findOne(id))){
				userServ.follow(userServ.findById(logger.getIdLoggedUser()), goatServ.findOne(id));
				return new ResponseEntity<>(userServ.findById(logger.getIdLoggedUser()),HttpStatus.OK);
			}else{
				return new ResponseEntity<>(userServ.findById(logger.getIdLoggedUser()),HttpStatus.NOT_ACCEPTABLE);
			}
		}else{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(UserDetail.class)
	@RequestMapping(value = "/cabra/{id}/unfollow", method = RequestMethod.PUT)
	public ResponseEntity<Persona> unfollowGoat(@PathVariable Integer id, HttpSession session){
		session.setMaxInactiveInterval(-1);
		if(goatServ.findOne(id) != null){	
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(logger.isLoggedUser()){
			if(userServ.findById(logger.getIdLoggedUser()).getFollowing().contains(goatServ.findOne(id))){
				userServ.unfollow(userServ.findById(logger.getIdLoggedUser()), goatServ.findOne(id));
				return new ResponseEntity<>(userServ.findById(logger.getIdLoggedUser()),HttpStatus.OK);
			}else{
				return new ResponseEntity<>(userServ.findById(logger.getIdLoggedUser()),HttpStatus.NOT_ACCEPTABLE);
			}
		}else{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(UserDetail.class)
	@RequestMapping(value = "/cabra/{id}/purchase", method = RequestMethod.PUT)
	public ResponseEntity<Persona> purchaseGoat(@PathVariable Integer id, HttpSession session){
		session.setMaxInactiveInterval(-1);
		if(goatServ.findOne(id) != null){	
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(logger.isLoggedUser()){
			if(goatServ.hasOwner(goatServ.findOne(id))){
				return new ResponseEntity<>(userServ.findById(logger.getIdLoggedUser()),HttpStatus.NOT_ACCEPTABLE);
			}else{
				userServ.setOwner(userServ.findById(logger.getIdLoggedUser()), goatServ.findOne(id));
				return new ResponseEntity<>(userServ.findById(logger.getIdLoggedUser()),HttpStatus.OK);
			}
		}else{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	private static final String USER_IMAGE_FOLDER = "src/main/resources/static/imgProfile";
	 @RequestMapping(value = "/image/upload",method = RequestMethod.PUT)
	 public ResponseEntity<Persona> handleFileUpload(
	   @RequestBody MultipartFile file) {

	  //String fileName = file.getOriginalFilename() + ".jpg";
	  if(logger.isLoggedUser()){
	   
	   Persona u= logger.getLoggedUser();
	   Integer idUser = logger.getIdLoggedUser();
	   String fileName = "usuario-" + idUser  + ".jpg";

	   if (!file.isEmpty()) {
	    try {

	     File filesFolder = new File(USER_IMAGE_FOLDER);
	     if (!filesFolder.exists()) {
	      filesFolder.mkdirs();
	     }

	     File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
	     file.transferTo(uploadedFile);
	     
	     u.setProfileImage(fileName);
	     userServ.save(u);

	     return new ResponseEntity<>(u, HttpStatus.OK);


	    } catch (Exception e) {
	     return new ResponseEntity<>(u, HttpStatus.NOT_FOUND);
	    }
	   } else {
	    return new ResponseEntity<>(u, HttpStatus.NOT_FOUND);

	   }
	  }else{
	   return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	  }
	   
	 }
	
	
}
