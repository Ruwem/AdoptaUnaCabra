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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Persona {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String nombre,correo;
	private Date birthday;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Cabra> cabras;
	
	@ManyToMany(mappedBy="followers")
	private List<Cabra> following;
	
	@OneToMany
	private List<Noticia> news;
	
	protected Persona(){
		
	}
	public Persona( String nombre ,String correo, Date birthday){
		this.nombre = nombre;
		this.correo = correo;
		this.birthday = birthday;
		this.cabras = new ArrayList<Cabra>();
		this.news = new ArrayList<>();
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
