package adoptaApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import adoptaApp.entity.Cabra;
import adoptaApp.repository.CabraRepository;
import adoptaApp.repository.PersonaRepository;

@Controller
public class PersonaController {

	@Autowired
	PersonaRepository personaRep;
	
	@Autowired
	CabraRepository cabraRep;
	
	
	@RequestMapping ("/perfil/id")
	public String index(Model model){
		return "perfil-persona";
	}
	
	@RequestMapping("/perfil/id/addcabra")
	public String addCabra(Cabra newCabra){
		return "add-cabra";
	}
}
