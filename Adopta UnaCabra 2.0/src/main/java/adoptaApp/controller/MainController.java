package adoptaApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MainController{
	

	
	@RequestMapping("/")
	public String index(Model model){
		return "index";
	}
	
	@RequestMapping("/centros")
	public String centros(Model model){
		return "centros";
	}
	
	@RequestMapping("/formulario")
	public String formulario(Model model){
		return "formulario";
	}
	
	@RequestMapping("/about")
	public String about(Model model){
		return "about";
	}
	
	@RequestMapping("/addcabra")
	public String addCabra(Model model){
		return "add-cabra";
	}
	
	@RequestMapping("/perfilcabra")
	public String perfilcabra(Model model){
		return "perfil-cabra";
	}
	
	@RequestMapping("/compracabraperfil")
	public String compracabraperfil(Model model){
		return "perfil";
	}
	
	@RequestMapping("/payment")
	public String payment(Model model){
		return "payment";
	}
	
	

	
	
}