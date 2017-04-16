package adoptaApp.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

import adoptaApp.entity.Centro.Basic;


@Entity
public class Comentario {

	public interface Basic{}
	public interface NoOwner{}
	public interface NoNews{}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonView({Basic.class,NoOwner.class,NoNews.class})
	private Integer id;
	
	@JsonView({Basic.class,NoOwner.class,NoNews.class})
	private String comentario;
	
	private LocalDateTime fecha;
	
	@ManyToOne
	@JsonView({Basic.class,NoNews.class})
	private Persona author;
	
	@ManyToOne
	@JsonView({Basic.class,NoOwner.class})
	private Noticia noticia;

	protected Comentario(){
		
	}
	

	public Comentario(String comentario, LocalDateTime fecha, Persona author, Noticia noticia) {
		super();
		this.comentario = comentario;
		this.fecha = fecha;
		this.author = author;
		this.noticia = noticia;
	}





	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Persona getAuthor() {
		return author;
	}

	public void setAuthor(Persona author) {
		this.author = author;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}


	
	
}
