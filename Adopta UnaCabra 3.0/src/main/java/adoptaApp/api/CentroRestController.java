package adoptaApp.api;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import adoptaApp.api.CabraRestController.CabraDetail;
import adoptaApp.api.PersonaRestController.UserDetail;
import adoptaApp.entity.Cabra;
import adoptaApp.entity.Centro;
import adoptaApp.entity.Noticia;
import adoptaApp.entity.Persona;
import adoptaApp.repository.CabraRepository;
import adoptaApp.services.CentroService;

@RestController
@RequestMapping("/api/centros")
public class CentroRestController {
	
	public interface CentroDetail extends Centro.Basic,Cabra.NoOwner{}
	
	@Autowired
	private CentroService centroServ;
	
	@JsonView(CentroDetail.class)
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Centro> getCenters() {
		return centroServ.findAll();
	}
	
	@JsonView(CentroDetail.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Centro> getCenter(@PathVariable Integer id) {

		Centro center = centroServ.findOne(id);
		if (center != null) {
			return new ResponseEntity<>(center, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Centro addcenter(@RequestBody Centro center, HttpSession session) {
		session.setMaxInactiveInterval(-1);
		Centro newCenter = new Centro(center.getNombre(),center.getLugar(),center.getEmail(),center.getTelephone());
		centroServ.save(newCenter);
		return newCenter;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Centro> updateCenter(@PathVariable Integer id, @RequestBody Centro updatedCenter, HttpSession session) {

		session.setMaxInactiveInterval(-1);
		Centro center = centroServ.findOne(id);
		if ((center != null) && (center.getId() == updatedCenter.getId())) {
			center = centroServ.updateCenter(center, updatedCenter);
			centroServ.save(center);;
			return new ResponseEntity<>(center, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Centro> deleteCenter(@PathVariable Integer id) {
		Centro centerSelected = centroServ.findOne(id);
		if (centerSelected == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			centroServ.delete(centerSelected.getId());
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
	

}
	

