package adoptaApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import adoptaApp.entity.Noticia;
import adoptaApp.repository.NoticiaRepository;



@Controller
public class NewsController {

	@Autowired
	private NoticiaRepository noticiaRep;
	
	@RequestMapping("/listadonoticias")
	public String listadonoticias(Model model){
		
		List<Noticia> noticia = noticiaRep.findAll();
		
		model.addAttribute("noticiaRep", noticiaRep);
		
		return "listadonoticias";
	}
	
	@RequestMapping("/noticias")
	public String noticias(Model model){
		return "noticias";
	}
	
	@RequestMapping("/redactarNoticia")
	public String redactarNoticia(Model model){
		return "redactar-noticia";
	}
}
