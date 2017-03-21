package adoptaApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import adoptaApp.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona,Integer>{

	public Persona findByNombre(String nombre);
	public Persona findByCorreo(String correo);
	
}
