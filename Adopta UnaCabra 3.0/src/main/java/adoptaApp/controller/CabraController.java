package adoptaApp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import adoptaApp.entity.Cabra;
import adoptaApp.entity.Comentario;
import adoptaApp.entity.Noticia;
import adoptaApp.entity.Persona;
import adoptaApp.repository.CabraRepository;
import adoptaApp.repository.PersonaRepository;
import adoptaApp.security.UserAuthComponent;

@Controller
public class CabraController{
	
	@Autowired
	private CabraRepository cabraRep;
	@Autowired
	private PersonaRepository personaRep;
	@Autowired
	private UserAuthComponent user;
	
	@RequestMapping("/perfilcabra/{id}")
	public String perfilcabra(Model model, @PathVariable Integer id,HttpServletRequest request){
		  if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
			   Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());
			   model.addAttribute("user", loggedUser);
			   model.addAttribute("logged", true);
			   model.addAttribute("eresUsuario", true);
			   if(cabraRep.getOne(id).getOwner() != null){
				   if(request.getUserPrincipal().getName().equals(cabraRep.getOne(id).getOwner().getNombre())){
					   model.addAttribute("eresOwner", true );
				   }
			   }
			   if(personaRep.getOne(loggedUser.getId()).getFollowing().contains(cabraRep.getOne(id))){
				   model.addAttribute("SiFavorita", true);
			   }else{
				   model.addAttribute("NoFavorita", true);				   
			   }
		  } else{
			   model.addAttribute("unlogged", true);
			  }
		
		Cabra cabra = cabraRep.findOne(id);

		
		String image = cabra.getProfileImage();
		String nombre = cabra.getNombre();
		String raza = cabra.getRaza();
		Date nacimiento = cabra.getNacimiento();
		String[] fecha = nacimiento.toString().split(" ");
		String[] partes = fecha[0].split("-");
		String birthdate = partes[2]+"-"+partes[1]+"-"+partes[0];
		Double peso = cabra.getWeight();
		Persona dueño = cabra.getOwner();
		
		model.addAttribute("image", image);
		model.addAttribute("name", nombre);
		model.addAttribute("raza", raza);
		model.addAttribute("nacimiento", birthdate);
		model.addAttribute("weight", peso);
		model.addAttribute("dueño", dueño);
		model.addAttribute("id", id);
		
		List<Noticia> cabraNews = cabra.getNews();
		model.addAttribute("cabraNews",cabraNews);
	
		
		return "perfil-cabra";
	}
	
	@RequestMapping("/pefilcabra/{id}/followSuccess")
	public String followGoat(Model model, @PathVariable Integer id, HttpServletRequest request){
		if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
			   Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());	
			   model.addAttribute("user", loggedUser);
			   model.addAttribute("logged", true);
		  } else{
			  model.addAttribute("unlogged",true);
		  }
		
		if(request.getUserPrincipal().equals(personaRep.getOne(id))){
			model.addAttribute("eresUsuario", true);
		}
		
		Cabra c = cabraRep.findOne(id);
		Persona p = personaRep.findByNombre(request.getUserPrincipal().getName());
		Integer idUser = p.getId();
		
		try {
			if(c.getFollowers().contains(p)){
				c.getFollowers().add(p);
			} else {
				p.getFollowing().add(c);
				personaRep.save(p);
				c.getFollowers().add(p);
				cabraRep.save(c);
			}
		} catch (Exception e) {
				return "redirect:/perfilcabra/" + id;
		}
		return "redirect:/perfilcabra/" + id;
	}
	
	@RequestMapping("/pefilcabra/{id}/unfollowSuccess")
	public String unfollowGoat(Model model, @PathVariable Integer id, HttpServletRequest request){
		if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
			   Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());	
			   model.addAttribute("user", loggedUser);
			   model.addAttribute("logged", true);
		  } else{
			  model.addAttribute("unlogged",true);
		  }
		
		if(request.getUserPrincipal().equals(personaRep.getOne(id))){
			model.addAttribute("eresUsuario", true);
		}
		
		Cabra c = cabraRep.findOne(id);
		Persona p = personaRep.findByNombre(request.getUserPrincipal().getName());
		Integer idUser = p.getId();
		
		try {
			if(c.getFollowers().contains(p)){
				p.getFollowing().remove(c);
				personaRep.save(p);
				c.getFollowers().remove(p);
				cabraRep.save(c);
			} else {
				p.getFollowing().remove(c);
			}
		} catch (Exception e) {
				return "redirect:/perfilcabra/" + id;
		}
		return "redirect:/perfilcabra/" + id;
	}
		
		
		/*try {
			if(c.getFollowers().contains(loggedUser)){
				return "redirect:/perfil/" + idLogged;
			} else {
				loggedUser.getFollowing().add(c);
				personaRep.save(loggedUser);
				c.getFollowers().add(loggedUser);
				cabraRep.save(c);
			}
			
		} catch(Exception e) {
			return "redirect:/perfilcabra/{id}";
		}*/
		
}
