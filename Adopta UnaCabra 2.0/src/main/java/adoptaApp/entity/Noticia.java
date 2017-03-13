package adoptaApp.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Noticia {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String titulo,descripcion,cuerpo;
	private Date date;
	
	@OneToOne
	private Persona author;
	
	@ManyToMany(mappedBy = "news")
	private List<Cabra> cabras;
	
	@ManyToMany(mappedBy = "news")
	private List <Centro> centros;
	
	protected Noticia (){
		
	}

	public Noticia( String titulo, String descripcion, String cuerpo, Date date, Persona author) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.cuerpo = cuerpo;
		this.date = date;
		this.author = author;
		this.cabras = new ArrayList<>();
		this.centros = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
	
	
	
	
	
	
	
}
