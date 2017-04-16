package adoptaApp.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Order;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import adoptaApp.entity.Cabra;
import adoptaApp.entity.Comentario;
import adoptaApp.entity.Noticia;
import adoptaApp.entity.Persona;
import adoptaApp.repository.CabraRepository;
import adoptaApp.repository.ComentarioRepository;
import adoptaApp.repository.NoticiaRepository;
import adoptaApp.repository.PersonaRepository;

@Service
public class UserService {
	
	@Autowired
	private PersonaRepository personaRep;
	
	@Autowired
	private CabraRepository cabraRep;
	
	@Autowired
	private NoticiaRepository newsRep;
	
	@Autowired
	private ComentarioRepository commentRep;
	

	
	private Persona findByEmail(String email){
		
		return personaRep.findByCorreo(email);
	
	}
	
	public Persona findById(Integer id){
		
		return personaRep.findOne(id);
		
	}
	
	public List<Persona> findAll(){
	
		return personaRep.findAll();
	
	}
	
	public void save (Persona p){
		
		personaRep.save(p);
		
	}
	public void delete (Persona p){
		 
		 personaRep.delete(p);
	 
	}
	 
	public Persona updatePersona(Persona oldUser, Persona newUser){
		oldUser.setCabras(newUser.getCabras());
		oldUser.setApellidos(newUser.getApellidos());
		oldUser.setComentarios(newUser.getComentarios());
		oldUser.setCorreo(newUser.getCorreo());
		oldUser.setFollowing(newUser.getFollowing());
		oldUser.setNews(newUser.getNews());
		oldUser.setNombre(newUser.getNombre());
		oldUser.setPasswordHash(newUser.getPasswordHash());
		oldUser.setProfileImage(newUser.getProfileImage());
		oldUser.setRoles(newUser.getRoles());
		return oldUser;
	}
	
    public Persona createNew(Map<String, String> allValues) {
	    Persona newUser = null;
	    try{
            Persona placeholder = new Persona(allValues.get("nombre"),allValues.get("apellidos"),allValues.get("correo"),allValues.get("passwordHash"),"ROLE_USER");
            personaRep.save(placeholder);
            newUser = placeholder;
        }catch (Exception e){
	        System.err.println(e.getMessage());
        }
        return newUser;
    }
    
    public void follow(Persona loggedUser, Cabra toFollow) {
        toFollow.getFollowers().add(loggedUser);
        cabraRep.save(toFollow);
        loggedUser.getFollowing().add(toFollow);
        personaRep.save(loggedUser);
    }
    
    public void unfollow(Persona loggedUser, Cabra toUnfollow) {
        toUnfollow.getFollowers().remove(loggedUser);
        cabraRep.save(toUnfollow);
        loggedUser.getFollowing().remove(toUnfollow);
        personaRep.save(loggedUser);

    }
    
    public void setOwner(Persona loggedUser, Cabra goat){
    	Persona update = personaRep.findOne(loggedUser.getId());
    	update.getCabras().add(goat);
    	personaRep.save(update);
    	goat.setOwner(update);
    	cabraRep.save(goat);
    }
    
    public void setNewsAuthor(Persona loggedUser, Noticia news){
    	Persona update = personaRep.findOne(loggedUser.getId());
    	update.getNews().add(news);
    	personaRep.save(update);
    	news.setAuthor(update);
    	newsRep.save(news);
    }
    
    public void setComentario(Persona loggedUser, Comentario comment){
    	Persona update = personaRep.findOne(loggedUser.getId());
    	update.getComentarios().add(comment);
    	personaRep.save(update);
    	comment.setAuthor(update);
    	commentRep.save(comment);
    }
    
}