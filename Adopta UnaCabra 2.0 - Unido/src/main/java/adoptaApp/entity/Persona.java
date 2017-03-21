package adoptaApp.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
public class Persona {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String nombre,correo,passwordHash, apellidos;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Cabra> cabras;
	
	@ManyToMany(mappedBy="followers")
	private List<Cabra> following;
	
	@OneToMany
	private List<Noticia> news;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	@OneToMany
	private List<Comentario> comentarios;
	
	protected Persona(){		
	}
	
	public Persona( String nombre,String apellidos ,String correo, String password, String... roles){
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
				
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.comentarios = new ArrayList<>();
		this.cabras = new ArrayList<Cabra>();
		this.news = new ArrayList<>();
	}
	
	
	
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String password) {
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<Cabra> getCabras() {
		return cabras;
	}
	public void setCabras(List<Cabra> cabras) {
		this.cabras = cabras;
	}
	public List<Cabra> getFollowing() {
		return following;
	}
	public void setFollowing(List<Cabra> following) {
		this.following = following;
	}
	public List<Noticia> getNews() {
		return news;
	}
	public void setNews(List<Noticia> news) {
		this.news = news;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	
}
