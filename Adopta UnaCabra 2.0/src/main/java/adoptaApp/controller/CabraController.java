package adoptaApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CabraController{
	
	
	@RequestMapping("/perfilcabra")
	public String perfilcabra(Model model){
		return "perfil-cabra";
	}
}
