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
import adoptaApp.entity.Centro;
import adoptaApp.entity.Persona;
import adoptaApp.repository.CabraRepository;
import adoptaApp.repository.CentroRepository;
import adoptaApp.repository.PersonaRepository;;



@Controller
public class MainController{
	
	@Autowired
	private PersonaRepository personaRep;
	@Autowired
	private CentroRepository centroRep;
	@Autowired
	private CabraRepository cabraRep;

	
	@RequestMapping("/")
	public String index(Model model, HttpServletRequest request){
		if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
			Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());
			model.addAttribute("user", loggedUser);
			model.addAttribute("logged", true);
		} else{
			model.addAttribute("unlogged", true);
		}
		List<Cabra> c = cabraRep.findAll();
		List<Cabra> cabras = c.subList(0, 9);
		model.addAttribute("cabras",cabras);
		List<Centro> centros = centroRep.findAll();
		List<Centro> centrosSub = centros.subList(0, 3);
		model.addAttribute("centros", centrosSub);
		return "index";
	}
	
	@RequestMapping("/goatPurchase/{id}")
	public String compracabraperfil(Model model, HttpServletRequest request,@PathVariable Integer id){
		if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
			Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());
			model.addAttribute("user", loggedUser);
			model.addAttribute("logged", true);
		} else{
			model.addAttribute("unlogged", true);
		}
		
		Cabra cabra = cabraRep.findOne(id);
		
		String nombre = cabra.getNombre();
		Integer idCabra = cabra.getId();
		String raza = cabra.getRaza();
		Date nacimiento = cabra.getNacimiento();
		String[] fecha = nacimiento.toString().split(" ");
		String[] partes = fecha[0].split("-");
		String birthdate = partes[2]+"-"+partes[1]+"-"+partes[0];
		Double peso = cabra.getWeight();
		Double precio = cabra.getPrice();
		String sex = cabra.getSexo();
		
		model.addAttribute("nombre", nombre);
		model.addAttribute("raza", raza);
		model.addAttribute("nacimiento", birthdate);
		model.addAttribute("peso", peso);
		model.addAttribute("precio", precio);
		model.addAttribute("sexo", sex);
		model.addAttribute("cabraId", idCabra);
		
		return "perfil";
	}
	
	@RequestMapping("/goatPurchase/{id}/payment")
	public String payment(Model model, HttpServletRequest request){
		return "payment";
	}
	
	

	
	
}