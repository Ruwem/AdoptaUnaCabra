package adoptaApp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import adoptaApp.entity.Cabra;
import adoptaApp.entity.Persona;
import adoptaApp.repository.CabraRepository;
import adoptaApp.repository.PersonaRepository;

@Controller
public class SearchController {
	@Autowired
	private PersonaRepository personaRep;
	
	@Autowired
	private CabraRepository cabraRep;
	
	@RequestMapping("/search")
	public String search(Model model, HttpServletRequest request, @RequestParam String mySearch) {
		if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
			Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());
			model.addAttribute("user", loggedUser);
			model.addAttribute("logged", true);
		} else
			model.addAttribute("unlogged", true);
		List<Persona> searchPersona = new ArrayList<Persona>();
		List <Persona> allUsers = personaRep.findAll();
		for(int i=0; i<=allUsers.size()-1; i++){
			if (allUsers.get(i).getNombre().equals(mySearch)){
				Persona userFound = allUsers.get(i);
				searchPersona.add(userFound);
			}
		}
		
		List<Cabra> searchCabra = new ArrayList<Cabra>();
		List <Cabra> allGoat = cabraRep.findAll();
		for(int i=0; i<=allGoat.size()-1; i++){
			if (allGoat.get(i).getNombre().equals(mySearch)){
				Cabra goatFound = allGoat.get(i);
				searchCabra.add(goatFound);
			}
		}
		
		if (searchPersona.size() != 0) 
			model.addAttribute("UserFound", true);
		else
			model.addAttribute("UserFound", false);
		if(searchCabra.size() != 0)
			model.addAttribute("NoGoatFound",true);
		else
			model.addAttribute("NoGoatFound",false);
		
		model.addAttribute("search", mySearch);
		model.addAttribute("personas", searchPersona);
		model.addAttribute("cabras", searchCabra);

		return "search";
	}
}
