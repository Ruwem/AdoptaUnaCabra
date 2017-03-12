package adoptaApp.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cabra {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private long id;
		
		private String nombre, raza;
		private Date nacimiento;
		
		protected Cabra(){
			}
		
		public Cabra(String nombre, String raza, Date nacimiento){
			this.nombre=nombre;
			this.raza=raza;
			this.nacimiento=nacimiento;
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

		public String getRaza() {
			return raza;
		}

		public void setRaza(String raza) {
			this.raza = raza;
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