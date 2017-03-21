package adoptaApp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import adoptaApp.entity.Noticia;
import adoptaApp.entity.Persona;

public interface NoticiaRepository extends JpaRepository<Noticia,Integer> {

	public List<Noticia> findByTitulo(String titulo);
	public List<Noticia> findByCuerpo(String cuerpo);
	public List<Noticia> findByDate(Date date);
}
