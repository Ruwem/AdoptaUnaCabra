package adoptaApp.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import adoptaApp.entity.Cabra;
import adoptaApp.entity.Persona;
import adoptaApp.entity.Noticia;
import adoptaApp.repository.CabraRepository;
import adoptaApp.repository.PersonaRepository;
import adoptaApp.security.UserAuthComponent;

@Controller
public class PersonaController {

	@Autowired
	PersonaRepository personaRep;
	
	@Autowired
	CabraRepository cabraRep;
	
	@Autowired
	private UserAuthComponent userAuth;
	
	
	
	/*@RequestMapping("/user_profile")
	public String userProfile(Model model, HttpServletRequest request, @PathVariable Integer id){

		Persona user = personaRep.findByNombre(request.getUserPrincipal().getName());
		model.addAttribute("user", user);
		
		model.addAttribute("logged",true);

		return "perfil-persona";
	}*/
	
	@RequestMapping ("/perfil/{id}")
	public String index(Model model, @PathVariable Integer id,HttpServletRequest request){
		  if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
			   Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());
			   model.addAttribute("user", loggedUser);
			   model.addAttribute("logged", true);
			   model.addAttribute("eresUsuario", false);
				if(request.getUserPrincipal().getName().equals(personaRep.getOne(id).getNombre())){
					model.addAttribute("eresUsuario", true);
				}
			  } else{
			   model.addAttribute("unlogged", true);
			  }
		Persona persona = personaRep.findOne(id);
		
		String nombre = persona.getNombre();
		String apellidos = persona.getApellidos();
		String email = persona.getCorreo();
		String image = persona.getProfileImage();

		model.addAttribute("name", nombre);
		model.addAttribute("surnames", apellidos);
		model.addAttribute("email", email);
		model.addAttribute("image", image);
		
		List<Noticia> authorNews = persona.getNews();
		model.addAttribute("authorNews",authorNews);
		List<Cabra> cabrasOwner = persona.getCabras();
		model.addAttribute("cabrasOwner",cabrasOwner);
		List<Cabra> cabrasFav = persona.getFollowing();
		model.addAttribute("cabrasFav", cabrasFav);
		
		return "perfil-persona";
	}
	
	
	@RequestMapping("/perfil/{id}/addcabra")
	public String addCabra(Model model, @PathVariable Integer id,HttpServletRequest request){
		boolean login = userAuth.isLoggedUser();
		if (login){
			Integer idLogged = userAuth.getIdLoggedUser();
			Persona user = personaRep.findOne(idLogged);
			model.addAttribute("logged", true);
			model.addAttribute("unlogged",false);
			model.addAttribute("user", user);
			//return "index";
			}
		return "add-cabra";
	}
	
	@RequestMapping("/perfil/{id}/addcabra/nueva")
	public String anyadeCabra(@RequestParam String name, @RequestParam String raza,@RequestParam String sex,
							@RequestParam String date, @RequestParam String weight, @PathVariable Integer id){
		
		String[] parts = date.split("/");
		int month = Integer.parseInt(parts[0]); 
		int day = Integer.parseInt(parts[1]); 
		int year = Integer.parseInt(parts[2]);
		
		double peso = Double.parseDouble(weight);
	
		Calendar myCalendar = new GregorianCalendar(year, month, day);
		Date myDate = myCalendar.getTime();
		
		Cabra c= new Cabra(name, raza, myDate, 0, peso, sex);
		try {
			c.setOwner(personaRep.getOne(id));
			cabraRep.save(c);
			personaRep.getOne(id).getCabras().add(c);
			personaRep.save(personaRep.getOne(id));
		} catch (Exception e) {
			return "redirect:/perfil/"+ id + "/addcabra";
		}
		Integer idCabra = c.getId();
		return "redirect:/perfilcabra/" + idCabra;
	}
	
	
}
