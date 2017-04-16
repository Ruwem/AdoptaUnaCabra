package adoptaApp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		
		Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());
		List<Noticia> noticiasfavoritas = new ArrayList<>();
		Set<Cabra> siguiendo = loggedUser.getFollowing();
		
		for (Cabra c : siguiendo) {
			for (Noticia n : c.getNews()) {
				if(!noticiasfavoritas.contains(n)){
					noticiasfavoritas.add(n);
				}
			}
		}
		
		Page<Noticia> noticiero = new PageImpl<>(noticiasfavoritas, new PageRequest(0, 2),noticiasfavoritas.size());
		
		model.addAttribute("noticia", noticiero);	
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
		
		List<Noticia> noticias = noticiaRep.findAll();
		Page<Noticia> noticiasTodas = noticiaRep.findAll(new PageRequest(0, 2));
		model.addAttribute("noticia", noticiasTodas);	
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
		Integer idAuthor; 
		try{
			author = noticia.getAuthor().getNombre();
			idAuthor = noticia.getAuthor().getId();
			model.addAttribute("idAuthor",idAuthor);
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
	  
	  List<Cabra> cabras = cabraRep.findAll();
	  
	  model.addAttribute("cabras", cabras);
	  
	  return "redactar-noticia";
	 }
	
	@RequestMapping("noticias/nueva/add")
	 public String anyadeNoticia(Model model, @RequestParam String title, @RequestParam String description,
			 @RequestParam String cuerpo,@RequestParam String[] cabras,HttpServletRequest request){
		Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());
		if (request.isUserInRole("ADMIN") || request.isUserInRole("USER")) {
			   model.addAttribute("user", loggedUser);
			   model.addAttribute("logged", true);
			  } else{
			   model.addAttribute("unlogged", true);
			  }
	  LocalDateTime d = LocalDateTime.now();
	  
	  Noticia n = new Noticia(title, description, cuerpo, d, loggedUser);
	  List<String> cabralist = Arrays.asList(cabras);

	  try {
		  noticiaRep.save(n);
	  } catch (Exception e) {
	   return "redirect:/noticias/nueva";
	  }
	  
	  for (int i=0;i< cabralist.size();i++){
	   Cabra c = cabraRep.findOne(Integer.parseInt(cabralist.get(i)));
	   c.getNews().add(n);
	   cabraRep.save(c);
	   n.getCabras().add(c);
	   noticiaRep.save(n);
	  }

	  Integer id = n.getId();
	  
	  return "redirect:/noticias/"+ id+"/addImage";
	 }
	
	@RequestMapping("/noticias/{id}/addImage")
	public String cambiarImagen(Model model,@PathVariable Integer id){
		model.addAttribute("id", id);
		return "addimagenew";
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
	@RequestMapping(method = RequestMethod.GET, value = "/moreNews")
	public String moreNews(Model model, @RequestParam int page) {
		
		Page<Noticia> noticiasTodas = noticiaRep.findAll(new PageRequest(page, 2));
		
		model.addAttribute("noticias", noticiasTodas);

		return "noticiasPage";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/moreFavNews")
	public String moreFavNews(Model model, @RequestParam int page,HttpServletRequest request) {
		Persona loggedUser = personaRep.findByNombre(request.getUserPrincipal().getName());
		List<Noticia> noticiasfavoritas = new ArrayList<>();
		Set<Cabra> siguiendo = loggedUser.getFollowing();

        int count = 2;
		for (Cabra c : siguiendo) {
			for (Noticia n : c.getNews()) {
				if(!noticiasfavoritas.contains(n)){
					noticiasfavoritas.add(n);
				}
			}
		}
		
		
		Page<Noticia> noticiero = new PageImpl<>(noticiasfavoritas,new PageRequest(page, 2),noticiasfavoritas.size());
		
		model.addAttribute("noticia", noticiero);	
		return "noticiasPage";
	}
}
