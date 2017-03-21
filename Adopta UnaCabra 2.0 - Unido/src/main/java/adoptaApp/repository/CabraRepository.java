package adoptaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import adoptaApp.entity.Cabra;
import java.lang.String;
import java.util.List;


public interface CabraRepository extends JpaRepository<Cabra,Integer>{

	public List<Cabra> findByRaza(String raza);
	public List<Cabra> findByNombre(String nombre);
	public List<Cabra> findByPrice(double price);
	public List<Cabra> findByWeight(double weight);
	
}
