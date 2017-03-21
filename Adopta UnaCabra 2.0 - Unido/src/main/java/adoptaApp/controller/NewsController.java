package adoptaApp.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import adoptaApp.entity.Comentario;
import adoptaApp.entity.Noticia;
import adoptaApp.entity.Persona;
import adoptaApp.entity.Cabra;
import adoptaApp.repository.CabraRepository;
import adoptaApp.repository.ComentarioRepository;
import adoptaApp.repository.NoticiaRepository;
import adoptaApp.repository.PersonaRepository;



@Controller
public class NewsController {

	@Autowired
	private NoticiaRepository noticiaRep;
	@Autowired
	private ComentarioRepository comentarioRep;	
	@Autowired
	private PersonaRepository personaRep;
	@Autowired
	private CabraRepository cabraRep;
	
	@RequestMapping("/noticias")
	public String listadonoticias(Model model,HttpServletRequest request){
		
		if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
			Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());
			model.addAttribute("user", loggedUser);
			model.addAttribute("logged", true);
		} else{
			return "redirect:/noticiasTodas";
		}
		
		List<Noticia> noticia = noticiaRep.findAll();
		model.addAttribute("noticia", noticia);	
		return "listadonoticiasfav";
	}
	@RequestMapping("/noticiasTodas")
	
	public String listadonoticiaslogged(Model model,HttpServletRequest request){
		
		if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
			Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());
			model.addAttribute("user", loggedUser);
			model.addAttribute("logged", true);
		} else{
			model.addAttribute("logged",false);
			model.addAttribute("unlogged", true);
		}
		
		List<Noticia> noticia = noticiaRep.findAll();
		model.addAttribute("noticia", noticia);	
		return "listadonoticiastodas";
	}
	
	@RequestMapping("/noticias/{id}")
	public String noticias(Model model,@PathVariable Integer id,HttpServletRequest request){
		if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
			Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());
			model.addAttribute("user", loggedUser);
			model.addAttribute("logged", true);
		} else{
			model.addAttribute("unlogged", true);
		}
		Noticia noticia = noticiaRep.findOne(id);
		
		String author;
		try{
			author = noticia.getAuthor().getNombre();
		} catch (Exception e){
			author = "Desconocido";
		}
		
		List<Comentario> comentarios = noticia.getComentarios();
		
		model.addAttribute("comentarios", comentarios);
		
		model.addAttribute("nombreautor",author);
		model.addAttribute("noticia", noticia);
	
		return "noticias";
	}
	
	@GetMapping(value = { "/noticias/nueva", "/noticias/nueva/"})
	 public String redactarNoticia(Model model){
	  
	  List<Cabra> cabras =cabraRep.findAll();
	  
	  model.addAttribute("cabras", cabras);
	  
	  return "redactar-noticia";
	 }
	
	@RequestMapping("noticias/nueva/add")
	 public String anyadeNoticia(@RequestParam String title, @RequestParam String description,@RequestParam String cuerpo,@RequestParam String[] cabras){
	  
	  LocalDateTime d = LocalDateTime.now();
	  
	  Noticia n = new Noticia(title, description, cuerpo, d, null);
	   List<String> cabralist = Arrays.asList(cabras);

	  try {
	   noticiaRep.save(n);
	  } catch (Exception e) {
	   return "redirect:/noticias/add";
	  }
	  
	  for (int i=0;i< cabralist.size();i++){
	   Cabra c = cabraRep.findOne(Integer.parseInt(cabralist.get(i)));
	   c.getNews().add(n);
	   cabraRep.save(c);
	   n.getCabras().add(c);
	   noticiaRep.save(n);
	  }

	  Integer id = n.getId();
	  
	  return "redirect:/noticias/"+ id;
	 }
	
	@RequestMapping("/noticias/{id}/nuevoComentario")
	public String nuevoComentario(@RequestParam String comentario,@PathVariable Integer id){
		
		LocalDateTime d = LocalDateTime.now();
		Noticia n = noticiaRep.findOne(id);
		Persona p = personaRep.findOne(1);
		Comentario c = new Comentario(comentario, d, p, n);

		try {
			comentarioRep.save(c);		
		} catch (Exception e) {			
			return "redirect:/noticias/" + id;
		}
		
			n.getComentarios().add(c);
			noticiaRep.save(n);
			
			
		return "redirect:/noticias/" + id;
	}
}
