package adoptaApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import adoptaApp.entity.Noticia;
import adoptaApp.repository.NoticiaRepository;



@Controller
public class NewsController {

	@Autowired
	private NoticiaRepository noticiaRep;
	
	@RequestMapping("/noticias")
	public String listadonoticias(Model model){
		
		return "listadonoticias";
	}
	
	@RequestMapping("/noticias/id")
	public String noticias(Model model){

		return "noticias";
	}
	
	@GetMapping(value = { "/noticias/nueva", "/noticias/nueva/"})
	public String redactarNoticia(Model model){
		return "redactar-noticia";
	}
}
