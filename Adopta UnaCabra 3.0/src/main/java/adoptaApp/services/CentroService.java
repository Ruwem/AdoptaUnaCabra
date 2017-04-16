package adoptaApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adoptaApp.entity.Centro;
import adoptaApp.entity.Noticia;
import adoptaApp.entity.Persona;
import adoptaApp.repository.CabraRepository;
import adoptaApp.repository.CentroRepository;

@Service
public class CentroService {

	@Autowired
	private CentroRepository centroRep;

	public Centro findOne(Integer id) {
		return centroRep.findOne(id);
	}

	public List<Centro> findAll() {
		return centroRep.findAll();
	}

	public void save(Centro centro) {
		centroRep.save(centro);
	}

	public void delete(Integer id) {
		centroRep.delete(id);
	}
	
	public Centro updateCenter(Centro oldCenter, Centro newCenter){
		oldCenter.setCabras(newCenter.getCabras());
		oldCenter.setEmail(newCenter.getEmail());
		oldCenter.setLugar(newCenter.getLugar());
		oldCenter.setNombre(newCenter.getNombre());
		oldCenter.setProfileImage(newCenter.getProfileImage());
		oldCenter.setTelephone(newCenter.getTelephone());
		return oldCenter;
	}
}
