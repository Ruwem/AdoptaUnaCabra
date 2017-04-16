package adoptaApp.api;

import java.time.LocalDateTime;

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

import adoptaApp.entity.Comentario;
import adoptaApp.entity.Noticia;
import adoptaApp.repository.ComentarioRepository;
import adoptaApp.security.UserAuthComponent;
import adoptaApp.services.NoticiaService;
import adoptaApp.services.UserService;

@RestController("/api/comment")
public class CommentRestController {

	@Autowired 
	private UserAuthComponent logger;
	@Autowired
	private NoticiaService newsServ;
	@Autowired
	private ComentarioRepository commentRep;
	@Autowired
	private UserService userServ;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Comentario> addComment(@RequestBody String comentario , HttpSession session,@PathVariable Integer id) {
		session.setMaxInactiveInterval(-1);
		if(logger.isLoggedUser()){
			if(newsServ.findOne(id)!= null){
				Noticia n = newsServ.findOne(id);
				LocalDateTime d = LocalDateTime.now();
				Comentario newComment = new Comentario(comentario,d,logger.getLoggedUser(),newsServ.findOne(id));
				try{
					commentRep.save(newComment);
				}catch(Exception e){
					return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
				}
				
				n.getComentarios().add(newComment);
				try{
					newsServ.save(n);
				}catch(Exception e){
					return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
				}
				logger.getLoggedUser().getComentarios().add(newComment);
				try{
					userServ.save(logger.getLoggedUser());
				}catch(Exception e){
					return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
				}
				return new ResponseEntity<>(newComment,HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else{
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
		
	
}
