package adoptaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import adoptaApp.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona,Long>{

}
