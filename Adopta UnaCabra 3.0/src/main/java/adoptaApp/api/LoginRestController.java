package adoptaApp.api;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import adoptaApp.entity.Cabra;
import adoptaApp.entity.Persona;
import adoptaApp.repository.CabraRepository;
import adoptaApp.security.UserAuthComponent;
import adoptaApp.services.UserService;

@RestController
@RequestMapping(value = "/api/")
public class LoginRestController {

	public interface LoginDetail extends Persona.LoginInt {}

	private static final Logger log = LoggerFactory.getLogger(LoginRestController.class);

	@Autowired
	private UserService userService;
	@Autowired 
	private UserAuthComponent userAuth;
	@Autowired
	private CabraRepository cabraServ;

	@JsonView(LoginDetail.class)
	@RequestMapping(value = "logIn/")
	public ResponseEntity<Persona> logIn() {

		if (!userAuth.isLoggedUser()) {
			log.info("Not user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			Persona loggedUser = userAuth.getLoggedUser();
			log.info("Logged as " + loggedUser.getNombre());
			return new ResponseEntity<>(loggedUser, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "logOut/")
	public ResponseEntity<Boolean> logOut(HttpSession session) {

		if (!userAuth.isLoggedUser()) {
			log.info("No user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			session.invalidate();
			log.info("Logged out");
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "register/", method = RequestMethod.POST)
	public ResponseEntity<Boolean> register(HttpSession session, @RequestBody Persona user) {
		if (userAuth.isLoggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		try {
			Persona newUser = new Persona(user.getNombre(), user.getApellidos(), user.getCorreo(), user.getPasswordHash(),"ROLE_USER");

			userService.save(newUser);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	
	
}