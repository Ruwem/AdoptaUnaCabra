package adoptaApp.entity;


import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Centro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre, lugar;
	private ArrayList<Cabra> cabras;
	
	protected Centro(){
	}
	
	public Centro(String nombre, String lugar){
		this.nombre = nombre;
		this.lugar = lugar;
		cabras = new ArrayList();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ArrayList<Cabra> getCabras() {
		return cabras;
	}

	public void setCabras(ArrayList<Cabra> cabras) {
		this.cabras = cabras;
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
