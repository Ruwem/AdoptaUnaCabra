package adoptaApp.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import adoptaApp.entity.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia,Long> {

}
