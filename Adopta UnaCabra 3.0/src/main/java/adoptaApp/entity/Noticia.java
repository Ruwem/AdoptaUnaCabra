package adoptaApp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Noticia {
	
	public interface Basic{}
	public interface NoOwner{}
	public interface NoGoats{}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonView({Basic.class,NoOwner.class,NoGoats.class})
	private Integer id;
	
	@JsonView({Basic.class,NoOwner.class,NoGoats.class})
	private String titulo;
	@JsonView({Basic.class,NoOwner.class,NoGoats.class})
	private String descripcion;
	@JsonView({Basic.class,NoOwner.class,NoGoats.class})
	private String cuerpo;
	@JsonView({Basic.class,NoGoats.class,NoOwner.class})
	private String profileImage;
	
	private LocalDateTime date;
	
	@ManyToOne
	@JsonView(Basic.class)
	private Persona author;
	
	@ManyToMany(mappedBy = "news")
	private Set<Cabra> cabras;
	
	@ManyToMany(mappedBy = "news")
	private List <Centro> centros;
	
	@OneToMany
	@JsonView(Basic.class)
	private List<Comentario> comentarios;
	
	protected Noticia (){
		
	}

	public Noticia( String titulo, String descripcion, String cuerpo, LocalDateTime d, Persona author) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.cuerpo = cuerpo;
		this.date = d;
		this.author = author;
		this.profileImage = "noticia-pordefecto.jpg";
		
		this.comentarios = new ArrayList<>();
		this.cabras = new HashSet<>();
		this.centros = new ArrayList<>();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Centro> getCentros() {
		return centros;
	}

	public void setCentros(List<Centro> centros) {
		this.centros = centros;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Persona getAuthor() {
		return author;
	}

	public void setAuthor(Persona author) {
		this.author = author;
	}

	public Set<Cabra> getCabras() {
		return cabras;
	}

	public void setCabras(Set<Cabra> cabras) {
		this.cabras = cabras;
	}
	public String getProfileImage() {
		  return profileImage;
		 }

	public void setProfileImage(String profileImage) {
		  this.profileImage = profileImage;
		 }
	
	
	
	
	
	
}
