package adoptaApp.entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import adoptaApp.util.Raza;

@Entity
public class Cabra {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Integer id;
		
		private String nombre;
		private String raza;
		private Date nacimiento;
		private double price, weight;
		
		@OneToOne
		private Persona owner;
		
		@ManyToMany
		private List<Persona> followers;
		
		@OneToOne
		private Centro home;
		
		@ManyToMany
		private List<Noticia> news;
		
		protected Cabra(){
			}
		
		public Cabra(String nombre, String raza, Date nacimiento, double price, double weight){
			this.nombre=nombre;
			if(Raza.contains(raza)){
				this.raza = raza;
			}else{
				this.setRaza(Raza.Desconocido);
			}
			
			this.nacimiento=nacimiento;
			this.price = price;
			this.weight = weight;
			this.followers = new ArrayList<>();
			this.news  = new ArrayList<>();
			
			this.owner = null;
			this.home = null;
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

		public List<Persona> getFollowers() {
			return followers;
		}

		public void setFollowers(List<Persona> followers) {
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
}