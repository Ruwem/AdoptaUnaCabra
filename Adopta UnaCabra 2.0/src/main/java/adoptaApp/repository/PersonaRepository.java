package adoptaApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import adoptaApp.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona,Long>{

	public List<Persona> findByNombre(String nombre);
	public List<Persona> findByCorreo(String correo);
	
}
