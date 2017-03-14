package adoptaApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import adoptaApp.entity.Centro;
import adoptaApp.entity.Persona;

public interface CentroRepository extends JpaRepository<Centro,Long>{

	public List<Centro> findByNombre(String nombre);
	public List<Centro> findByLugar(String lugar);
	public List<Centro> findByEmail(String email);
	public List<Centro> findByTelephone(String telephone);
	
}
