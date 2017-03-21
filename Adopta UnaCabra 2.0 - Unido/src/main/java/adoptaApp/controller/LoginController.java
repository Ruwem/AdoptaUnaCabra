package adoptaApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import adoptaApp.entity.Persona;
import adoptaApp.repository.PersonaRepository;

@Controller
public class LoginController {

	@Autowired
	private PersonaRepository personaRep;
	
	@RequestMapping("/login")
	public String formulario(Model model, HttpServletRequest request, HttpSession session){
		if (request.isUserInRole("ADMIN") || request.isUserInRole("USER"))
			return "redirect:/";
		model.addAttribute("unlogged", true);
		
		return "loginandregister";
	}
	
	@RequestMapping("/loginError")
	public String loginError(Model model) {
		model.addAttribute("unlogged", true);
		return "loginandregister";
	}
	
	@RequestMapping("/register/add")
	public String addUserAction(@RequestParam String name,@RequestParam String lastname, @RequestParam String password,
			@RequestParam String email) {
		
		Persona p = new Persona(name, lastname, email, password, "ROLE_USER");
		
		try {
			personaRep.save(p);
		} catch (Exception e) {
			return "redirect:/registerError";
		}

		return "redirect:/";
	}
	
	@RequestMapping("/registerError")
	public String registerError(Model model) {
		model.addAttribute("unlogged", true);
		model.addAttribute("alreadyReg",true);

		return "loginandregister";
	}
	@RequestMapping("/logOut")
	public String loginOut(HttpServletRequest request, Model model, HttpSession session) {

		if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")){
			return "redirect:/";
		}else{
			session.invalidate();
			return "redirect:/";
		}

	}
}
