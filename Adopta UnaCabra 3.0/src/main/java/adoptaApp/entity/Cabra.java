package adoptaApp.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

import adoptaApp.util.Gender;
import adoptaApp.util.Raza;

@Entity
public class Cabra {
	
		public interface Basic{}
		public interface NoOwner{}
		

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		
		@JsonView({Basic.class, NoOwner.class})
		private Integer id;
		
		@JsonView({Basic.class, NoOwner.class})
		private String nombre;
		
		@JsonView({Basic.class, NoOwner.class})
		private String raza;
		
		private Date nacimiento;
		
		@JsonView(Basic.class)
		private double price;
		
		@JsonView(Basic.class)
		private double weight;
		
		@JsonView({Basic.class, NoOwner.class})
		private String sexo;
		
		@JsonView(Basic.class)
		private String profileImage;
		
		@OneToOne
		@JsonView(Basic.class)
		private Persona owner;
		
		@ManyToMany(fetch = FetchType.EAGER)
		@JsonView(Basic.class)
		private Set<Persona> followers;
		
		@OneToOne
		@JsonView(Basic.class)
		private Centro home;
		
		@ManyToMany
		@JsonView(Basic.class)
		private List<Noticia> news;
		
		protected Cabra(){
			}
		
		public Cabra(String nombre, String raza, Date nacimiento, double price, double weight, String sexo){
			this.nombre=nombre;
			
			if(Raza.contains(raza)){
				this.raza = raza;
			}else{
				this.setRaza(Raza.Desconocida);
			}
			
			if(Gender.contains(sexo)){
				this.sexo = sexo;
			}else{
				this.setSexo(Gender.Desconocido);
			}
			this.profileImage = "goat-pordefecto.jpg";
			this.nacimiento=nacimiento;
			this.price = price;
			this.weight = weight;
			this.news  = new ArrayList<>();
			this.followers = new HashSet<>();
			this.owner = null;
			this.home = null;
		}
		
		
		
		

		public String getProfileImage() {
			return profileImage;
		}

		public void setProfileImage(String profileImage) {
			this.profileImage = profileImage;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public void setSexo(String sexo) {
			this.sexo = sexo;
		}

		public String getSexo() {
			return sexo;
		}

		public void setSexo(Gender sexo) {
			this.sexo = sexo.toString();
		}

		public List<Noticia> getNews() {
			return news;
		}

		public void setNews(List<Noticia> news) {
			this.news = news;
		}

		public void setRaza(String raza) {
			this.raza = raza;
		}

		public double getWeight() {
			return weight;
		}

		public void setWeight(double weight) {
			this.weight = weight;
		}

		public Centro getHome() {
			return home;
		}

		public void setHome(Centro home) {
			this.home = home;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public Persona getOwner() {
			return owner;
		}

		public void setOwner(Persona owner) {
			this.owner = owner;
		}

		public Set<Persona> getFollowers() {
			return followers;
		}

		public void setFollowers(Set<Persona> followers) {
			this.followers = followers;
		}


		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getRaza() {
			return raza;
		}

		public void setRaza(Raza raza) {
			this.raza = raza.toString();
		}

		public Date getNacimiento() {
			return nacimiento;
		}

		public void setNacimiento(Date nacimiento) {
			this.nacimiento = nacimiento;
		}
		
		@Override
		public String toString() {
			return "Cabra [id=" + id + ", nombre=" + nombre + ", raza=" + raza + "nacimiento=" + nacimiento +"]";
		}

		public Integer getId() {
			return id;
		}
}