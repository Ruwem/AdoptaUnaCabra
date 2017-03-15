package adoptaApp.controller;

import java.util.Date;

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
	public String addCabra(Model model){
		return "add-cabra";
	}
	@RequestMapping("/perfil/id/addcabra/nueva")
	public String anyadeCabra(@RequestParam String name, @RequestParam String raza,@RequestParam String sex,
							@RequestParam String date, @RequestParam String weight){
		
		String[] parts = date.split("/");
		int month = Integer.parseInt(parts[0]); 
		int day = Integer.parseInt(parts[1]); 
		int year = Integer.parseInt(parts[2]);
		
		double peso = Double.parseDouble(weight);
	
		Date d = new Date(year, month, day);
		
		Cabra c= new Cabra(name, raza, d, 0, peso, sex);
		try {
			cabraRep.save(c);
			
		} catch (Exception e) {
			return "redirect:/perfil/id/addcabra";
		}

		return "redirect:/perfilcabra";
	}
}
