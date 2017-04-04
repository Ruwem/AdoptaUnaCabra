package adoptaApp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import adoptaApp.entity.Cabra;
import adoptaApp.entity.Noticia;
import adoptaApp.entity.Persona;
import adoptaApp.repository.CabraRepository;
import adoptaApp.repository.NoticiaRepository;
import adoptaApp.repository.PersonaRepository;
import adoptaApp.security.UserAuthComponent;

@Controller
public class FileUploadController {

	private static final String USER_IMAGE_FOLDER = "src/main/resources/static/imgProfile";
	
	
	@Autowired
	private PersonaRepository userRepository;
	@Autowired
	private CabraRepository goatRepository;
	@Autowired
	private NoticiaRepository newsRepository;


	@RequestMapping(value = "/perfilcabra/{id}/image/upload", method = RequestMethod.POST)
	public String handleFileUpload(Model model, 
			@RequestParam("file") MultipartFile file,
			@PathVariable Integer id) {

		Cabra g = goatRepository.findOne(id);
		String fileName = "goat-" + id  + ".jpg";

		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(USER_IMAGE_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);
				
				g.setProfileImage(fileName);
				goatRepository.save(g);
				return "redirect:/perfilcabra/"+id;

			} catch (Exception e) {
				
				model.addAttribute("fileName",fileName);
				model.addAttribute("error",
						e.getClass().getName() + ":" + e.getMessage());
				
				model.addAttribute("u",g);
				//model.addAttribute("imgProfile",true);
				return  "redirect:/perfilcabra/" + id;
			}
		} else {
			
			model.addAttribute("error",	"The file is empty");
			
			model.addAttribute("u",g);
			//model.addAttribute("imgProfile",true);
			return "redirect:/perfilcabra/" + id;
		}
	}	

	@RequestMapping(value = "/noticias/{id}/image/upload", method = RequestMethod.POST)
	public String addImageToNew(Model model, 
			@RequestParam("file") MultipartFile file,
			@PathVariable Integer id) {

		Noticia g = newsRepository.findOne(id);
		String fileName = "news-" + id  + ".jpg";

		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(USER_IMAGE_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);
				
				g.setProfileImage(fileName);
				newsRepository.save(g);
				return "redirect:/noticias/"+id;

			} catch (Exception e) {
				
				model.addAttribute("fileName",fileName);
				model.addAttribute("error",
						e.getClass().getName() + ":" + e.getMessage());
				
				model.addAttribute("u",g);
				//model.addAttribute("imgProfile",true);
				return  "redirect:/noticias/" + id;
			}
		} else {
			
			model.addAttribute("error",	"The file is empty");
			
			model.addAttribute("u",g);
			//model.addAttribute("imgProfile",true);
			return "redirect:/noticias/" + id;
		}
	}
	
	@RequestMapping(value = "/perfil/{id}/image/upload", method = RequestMethod.POST)
	public String PersonahandleFileUpload(Model model, 
			@RequestParam("file") MultipartFile file,
			@PathVariable Integer id) {

		Persona u= userRepository.findOne(id);
		String fileName = "usuario-" + id  + ".jpg";

		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(USER_IMAGE_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);
				
				u.setProfileImage(fileName);
				userRepository.save(u);
				return "redirect:/perfil/"+id;

			} catch (Exception e) {
				
				model.addAttribute("fileName",fileName);
				model.addAttribute("error",
						e.getClass().getName() + ":" + e.getMessage());
				
				model.addAttribute("u",u);
				//model.addAttribute("imgProfile",true);
				return  "redirect:/perfil/" + id;
			}
		} else {
			
			model.addAttribute("error",	"The file is empty");
			
			model.addAttribute("u",u);
			//model.addAttribute("imgProfile",true);
			return "redirect:/perfil/" + id;
		}
	}

	@RequestMapping("/image/{fileName}")
	public void handleFileDownload(@PathVariable String fileName,
			HttpServletResponse res) throws FileNotFoundException, IOException {

		File file = new File(USER_IMAGE_FOLDER, fileName + ".jpg");
		//File file = new File(FILES_FOLDER, fileName);

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils
					.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileName + "(" + file.getAbsolutePath()
					+ ") does not exist");
		}
	}

}