package adoptaApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import adoptaApp.entity.Centro;
import adoptaApp.entity.Persona;
import adoptaApp.repository.CentroRepository;
import adoptaApp.repository.PersonaRepository;

@Controller
public class CentroController {

	@Autowired
	private PersonaRepository personaRep;
	@Autowired
	private CentroRepository centroRep;
	
	
	@RequestMapping("/centros/{id}")
	 public String centros(Model model,HttpServletRequest request,@PathVariable Integer id){
	  if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
	   Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());
	   model.addAttribute("user", loggedUser);
	   model.addAttribute("logged", true);
	  } else{
	   model.addAttribute("unlogged", true);
	  }
	  Centro centro = centroRep.findOne(id);
	  
	  model.addAttribute("centro",centro);
	  
	  return "centros";
	}
	
	@RequestMapping("/listadocentros")
	 public String listadocentros(Model model,HttpServletRequest request){
	  if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
	   Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());
	   model.addAttribute("user", loggedUser);
	   model.addAttribute("logged", true);
	  } else{
	   model.addAttribute("unlogged", true);
	  }
	  
	  List<Centro> centro = centroRep.findAll();
	  
	  model.addAttribute("centro", centro); 
	  return "listadocentros";
	 }
}
