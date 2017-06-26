package adoptaApp.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonView;




@Entity
public class Persona {

	public interface Basico{}
	public interface LoginInt{}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	@JsonView({Basico.class,LoginInt.class})
	private Integer id;
	
	@JsonView({Basico.class,LoginInt.class})
	@Column(nullable = false)
	private String nombre;
	
	@JsonView({Basico.class,LoginInt.class})
	@Column(nullable = false)
	private String correo;
	
	@Column(nullable = false)
	private String passwordHash;
	
	@Column(nullable = false)
	@JsonView(Basico.class)
	private String apellidos;
	
	@Column
	@JsonView({Basico.class,LoginInt.class})
	private String profileImage;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonView(Basico.class)
	private Set<Cabra> cabras;
	
	@ManyToMany(mappedBy="followers",fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonView(Basico.class)
	private Set<Cabra> following;
	
	@OneToMany(mappedBy = "author",fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonView(Basico.class)
	private List<Noticia> news;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@JsonView(Basico.class)
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
	
		this.cabras = new HashSet<>();
		this.following = new HashSet<>();
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.comentarios = new ArrayList<>();
		this.news = new ArrayList<>();
		this.profileImage = "usuario-pordefecto.jpg";
	}
	
	
	
	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
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

	public Set<Cabra> getCabras() {
		return cabras;
	}
	public void setCabras(Set<Cabra> cabras) {
		this.cabras = cabras;
	}
	public Set<Cabra> getFollowing() {
		return following;
	}
	public void setFollowing(Set<Cabra> following) {
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
