package adoptaApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adoptaApp.entity.Cabra;
import adoptaApp.repository.CabraRepository;


@Service
public class CabraService {

	@Autowired
	private CabraRepository cabraRep;
	
	public Cabra findOne(Integer id) {
		return cabraRep.findOne(id);
	}

	public List<Cabra> findAll() {
		return cabraRep.findAll();
	}

	public void save(Cabra cabra) {
		cabraRep.save(cabra);
	}

	public void delete(Integer id) {
		cabraRep.delete(id);
	}
	
	public boolean hasOwner(Cabra goat){
		if (cabraRep.findOne(goat.getId()).getOwner() == null)
			return false;
		else
			return true;
	}
}
