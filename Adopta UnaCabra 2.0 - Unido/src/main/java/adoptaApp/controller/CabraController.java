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

@Controller
public class CabraController{
	
	@Autowired
	private CabraRepository cabraRep;
	@Autowired
	private PersonaRepository personaRep;
	
	@RequestMapping("/perfilcabra/{id}")
	public String perfilcabra(Model model, @PathVariable Integer id,HttpServletRequest request){
		  if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
			   Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());
			   model.addAttribute("user", loggedUser);
			   model.addAttribute("logged", true);
			  } else{
			   model.addAttribute("unlogged", true);
			  }
		
		Cabra cabra = cabraRep.findOne(id);
		
		String nombre = cabra.getNombre();
		String raza = cabra.getRaza();
		Date nacimiento = cabra.getNacimiento();
		String[] fecha = nacimiento.toString().split(" ");
		String[] partes = fecha[0].split("-");
		String birthdate = partes[2]+"-"+partes[1]+"-"+partes[0];
		Double peso = cabra.getWeight();
		Persona dueño = cabra.getOwner();
		
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
}
