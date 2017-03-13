package adoptaApp.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Centro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre, lugar,email;
	private  int telephone;
	
	@ManyToMany
	private List<Noticia> news;
	
	
	@OneToMany
	private List<Cabra> cabras;
	
	
	protected Centro(){
	}
	


	public Centro(long id, String nombre, String lugar, String email, int telephone) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.lugar = lugar;
		this.email = email;
		this.telephone = telephone;
		this.cabras = new ArrayList<>();
		this.news = new ArrayList<>();
	}
	
	

	public List<Cabra> getCabras() {
		return cabras;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getTelephone() {
		return telephone;
	}



	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}



	public List<Noticia> getNews() {
		return news;
	}



	public void setNews(List<Noticia> news) {
		this.news = news;
	}



	public void setCabras(List<Cabra> cabras) {
		this.cabras = cabras;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	@Override
	public String toString() {
		return "Centro [id=" + id + ", nombre=" + nombre + "lugar=" + lugar +"]";
	}
}
