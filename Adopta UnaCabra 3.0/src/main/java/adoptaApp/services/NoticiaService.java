package adoptaApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adoptaApp.entity.Cabra;
import adoptaApp.entity.Noticia;
import adoptaApp.repository.CabraRepository;
import adoptaApp.repository.NoticiaRepository;


@Service
public class NoticiaService {

	@Autowired
	private NoticiaRepository newsRep;
	
	public Noticia findOne(Integer id) {
		return newsRep.findOne(id);
	}

	public List<Noticia> findAll() {
		return newsRep.findAll();
	}

	public void save(Noticia noticia) {
		newsRep.save(noticia);
	}

	public void delete(Integer id) {
		newsRep.delete(id);
	}
	
}
