package adoptaApp.api;


import java.io.File;
import java.util.ArrayList;
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

import adoptaApp.entity.Cabra;
import adoptaApp.entity.Centro;
import adoptaApp.entity.Comentario;
import adoptaApp.entity.Noticia;
import adoptaApp.entity.Persona;
import adoptaApp.repository.NoticiaRepository;
import adoptaApp.repository.PersonaRepository;
import adoptaApp.security.UserAuthComponent;
import adoptaApp.services.CabraService;
import adoptaApp.services.NoticiaService;
import adoptaApp.services.UserService;


@RestController
@RequestMapping("/api/news")
public class NewsRestController {
	
	public interface NewsDetail extends Noticia.Basic, Cabra.NoOwner, Persona.LoginInt, Comentario.NoNews, Centro.Basic{}
	
	public interface ComentarioDetail extends Comentario.NoNews,Persona.LoginInt{}
	@Autowired
	private NoticiaService newsServ;
	@Autowired
	private UserService userServ;
	@Autowired
	private CabraService cabraServ;
	
	@Autowired
	private UserAuthComponent logger;
	
	@JsonView(NewsDetail.class)
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Noticia> getNoticias() {
		return newsServ.findAll();
	}

	@JsonView(NewsDetail.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Noticia> getNoticia(@PathVariable Integer id) {

		Noticia noticia = newsServ.findOne(id);
		if (noticia != null) {
			return new ResponseEntity<>(noticia, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(ComentarioDetail.class)
	@RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
	public List<Comentario> getComentarios (@PathVariable Integer id){
		Noticia noticia = newsServ.findOne(id);
		if( noticia != null){
			if(noticia.getComentarios() != null){
				return noticia.getComentarios();
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(NewsDetail.class)
	public ResponseEntity<Noticia> redactarNoticia(@RequestBody Noticia noticia, HttpSession session) {
		session.setMaxInactiveInterval(-1);
		try {
			Noticia newNoticia = new Noticia(noticia.getTitulo(), noticia.getDescripcion(), noticia.getCuerpo(), noticia.getDate(), noticia.getAuthor());
			newNoticia.setProfileImage("noticia-pordefecto.jpg");
			newNoticia.setCentros(noticia.getCentros());
			newNoticia.setCabras(noticia.getCabras());
			newsServ.save(newNoticia);
			for(Cabra c: noticia.getCabras()){
				c.getNews().add(newNoticia);
				cabraServ.save(c);
			}
			
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
	}
	
	@JsonView(NewsDetail.class)
	@RequestMapping(value = "/noticiasfav", method = RequestMethod.GET)
	public List<Noticia> getAllFav(){
		List<Noticia> noticiasfavoritas = new ArrayList<>();
		if(logger.isLoggedUser()){			
			Persona loggedUser = userServ.findById(logger.getIdLoggedUser());
			Set<Cabra> siguiendo = loggedUser.getFollowing();
			
			for (Cabra c : siguiendo) {
				for (Noticia n : c.getNews()) {
					if(!noticiasfavoritas.contains(n)){
						noticiasfavoritas.add(n);
					}
				}
			}
		}
		return noticiasfavoritas;
	}
	
	private static final String USER_IMAGE_FOLDER = "src/main/resources/static/imgProfile";
	 @RequestMapping(value = "/{id}/image/upload",method = RequestMethod.PUT)
	 public ResponseEntity<Noticia> handleFileUpload(
	   @RequestBody MultipartFile file,
	   @PathVariable Integer id) {

	  //String fileName = file.getOriginalFilename() + ".jpg";
	  if(newsServ.findOne(id).getAuthor().equals(logger.getLoggedUser())){
	   
	   Noticia n= newsServ.findOne(id);
	   String fileName = "news-" + id  + ".jpg";

	   if (!file.isEmpty()) {
	    try {

	     File filesFolder = new File(USER_IMAGE_FOLDER);
	     if (!filesFolder.exists()) {
	      filesFolder.mkdirs();
	     }

	     File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
	     file.transferTo(uploadedFile);
	     
	     n.setProfileImage(fileName);
	     newsServ.save(n);

	     return new ResponseEntity<>(n, HttpStatus.OK);


	    } catch (Exception e) {
	     return new ResponseEntity<>(n, HttpStatus.NOT_FOUND);
	    }
	   } else {
	    return new ResponseEntity<>(n, HttpStatus.NOT_FOUND);

	   }
	  }else{
	   return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	  }
	   
	 }
}
