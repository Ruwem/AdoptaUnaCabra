package adoptaApp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Noticia {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String titulo,descripcion,cuerpo, profileImage;
	private LocalDateTime date;
	
	@ManyToOne
	private Persona author;
	
	@ManyToMany(mappedBy = "news")
	private List<Cabra> cabras;
	
	@ManyToMany(mappedBy = "news")
	private List <Centro> centros;
	
	@OneToMany
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
		this.cabras = new ArrayList<>();
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

	public List<Cabra> getCabras() {
		return cabras;
	}

	public void setCabras(List<Cabra> cabras) {
		this.cabras = cabras;
	}
	public String getProfileImage() {
		  return profileImage;
		 }

	public void setProfileImage(String profileImage) {
		  this.profileImage = profileImage;
		 }
	
	
	
	
	
	
}
