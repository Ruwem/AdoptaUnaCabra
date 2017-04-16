package adoptaApp.util;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import adoptaApp.entity.Cabra;
import adoptaApp.entity.Centro;
import adoptaApp.entity.Comentario;
import adoptaApp.entity.Noticia;
import adoptaApp.entity.Persona;
import adoptaApp.repository.*;

@Component
public class DataBaseInit {

	@Autowired
	private CabraRepository cabraRep;
	@Autowired
	private CentroRepository centroRep;
	@Autowired
	private NoticiaRepository noticiaRep;
	@Autowired
	private PersonaRepository personaRep;
	@Autowired
	private ComentarioRepository comentarioRep;
	
	
	@PostConstruct
	private void initDataBase(){
		Cabra c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12;
		Centro l1,l2,l3,l4,l5,l6,l7,l8;
		Noticia n1,n2,n3;
		Persona p1,p2,p3,p4,p5;
		Comentario comentary1,comentary2,comentary3;
		
		LocalDateTime now = LocalDateTime.now();
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(1993, 12, 23, 22, 12, 13);
		Date d = cal.getTime();
		
		
	
		
		c1 = new Cabra("David", "Jamnapari", d, 123, 3,"masculino");
		c2 = new Cabra("Topo", "Jamnapari", d, 122, 67,"masculino");
		c3 = new Cabra("Marco", "Awen", d, 150, 78,"masculino");
		c4 = new Cabra("TT", "Albantinah", d, 40, 87,"femenino");
		c5 = new Cabra("Luz", "Abergelle", d, 96, 78,"masculino");	
		c6 = new Cabra("Emilio", "Jamnapari", d, 58, 3,"masculino");
		c7 = new Cabra("Emo", "Azpi Gorri", d, 70, 67,"masculino");
		c8 = new Cabra("Po", "boer", d, 53, 78,"masculino");
		c9 = new Cabra("Lala", "Albantinah", d, 95, 87,"masculino");
		c10 = new Cabra("Tinkywinki", "Abergelle", d, 223, 78,"masculino");
		c11 = new Cabra("Gorri", "Haimen", d, 24, 30,"femenino");
		c12 = new Cabra("Julio", "Hailun", d, 76, 90,"masculino");
		
		c1.setProfileImage("goat-1.jpg");
		c2.setProfileImage("goat-2.jpg");
		c3.setProfileImage("goat-3.jpg");
		c4.setProfileImage("goat-4.jpg");
		c5.setProfileImage("goat-5.jpg");
		c6.setProfileImage("goat-6.jpg");
		c7.setProfileImage("goat-7.jpg");
		c8.setProfileImage("goat-8.jpg");
		c9.setProfileImage("goat-9.jpg");
		c10.setProfileImage("goat-10.jpg");
		c11.setProfileImage("goat-11.jpg");
		c12.setProfileImage("goat-12.jpg");
		

		

			
		l1 = new Centro("Rancho Cabril", "Barcelona", "cabrilRancho@cabritas.com", 916975432);
		l2 = new Centro("VillaCabra", "Talavera de la Reina", "villacabrita@cabritas.com", 925975432);
		l3 = new Centro("Coloquio Cabril", "Cáceres", "coloquio@cabritas.com", 985489627);
		l4 = new Centro("Rancho Serrano", "Valencia", "ranchoSerrano@cabritas.com", 93465432);
		l5 = new Centro("Cabrilidad", "Murcia", "cabrildad@cabritas.com", 957125844);
		l6 = new Centro("MuchaCabra", "Galicia", "muchacabra@cabritas.com", 987582469);
		l7 = new Centro("Cabra y más", "Bilbao", "cabraymas@cabritas.com", 985471432);
		l8 = new Centro("Guia de cabras", "Burgos", "cabraguia@cabritas.com", 925471582);

		l1.setProfileImage("center-1.jpg");
		l2.setProfileImage("center-2.jpg");
		l3.setProfileImage("center-3.jpg");
		l4.setProfileImage("center-4.jpg");
		l5.setProfileImage("center-5.jpg");
		l6.setProfileImage("center-6.jpg");
		l7.setProfileImage("center-7.jpg");
		l8.setProfileImage("center-8.jpg");
		

		p1 = new Persona("David", "Talavera de la Reina","davidcabra@hotmail.com","12341234", "ROLE_USER");
		p2 = new Persona("Paquirrin", "Madrid", "paquirrin@hotmail.com","11223344", "ROLE_USER");
		p3 = new Persona("Marcela", "Mérida", "LizethM@hotmail.com","43214321", "ROLE_USER");
		p4 = new Persona("Pedro", "Barcelona", "peterEspinosa@hotmail.com","123123pe", "ROLE_USER");
		p5 = new Persona("Javier", "Fuenlabrada", "j.lopez@hotmail.com","1234", "ROLE_USER");
		

		p1.setProfileImage("usuario-1.jpg");
		p2.setProfileImage("usuario-2.jpg");
		p3.setProfileImage("usuario-3.jpg");
		p4.setProfileImage("usuario-4.jpg");
		p5.setProfileImage("usuario-5.jpg");
		

		
		
		cabraRep.save(c1);
		cabraRep.save(c2);
		cabraRep.save(c3);
		cabraRep.save(c4);
		cabraRep.save(c5);
		cabraRep.save(c6);
		cabraRep.save(c7);
		cabraRep.save(c8);
		cabraRep.save(c9);
		cabraRep.save(c10);
		cabraRep.save(c11);
		cabraRep.save(c12);
		
		centroRep.save(l1);
		centroRep.save(l2);
		centroRep.save(l3);
		centroRep.save(l4);
		centroRep.save(l5);
		centroRep.save(l6);
		centroRep.save(l7);
		centroRep.save(l8);
		
		personaRep.save(p1);
		personaRep.save(p2);
		personaRep.save(p3);
		personaRep.save(p4);
		personaRep.save(p5);
		
		
		c1.setOwner(p1);
		cabraRep.save(c1);
		/*c2.setHome(l1);
		c3.setHome(l2);
		c3.setOwner(p2);
		c4.setOwner(p4);
		c5.setOwner(p3);*/

		
		n1 = new Noticia("Cabra se despeña", "Una cabra cae por un precipio aunque parecia confiada. Estas cabras...", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vitae nibh semper "
				+ "leo maximus tempus. Duis vitae erat mollis, tincidunt dolor ac, dignissim metus. Sed"
				+ " molestie purus eget posuere sodales. Maecenas non ante aliquet, maximus metus ac", now, p1);
		
		n2 = new Noticia("Felicidad en el rancho", "Nace una nueva cabrita que alegra a todos", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vitae nibh semper "
				+ "leo maximus tempus. Duis vitae erat mollis, tincidunt dolor ac, dignissim metus. Sed"
				+ " molestie purus eget posuere sodales. Maecenas non ante aliquet, maximus metus ac", now, p2);
		
		n3 = new Noticia("Titulo otro", "Descripción de relleno. Asique hola", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vitae nibh semper "
				+ "leo maximus tempus. Duis vitae erat mollis, tincidunt dolor ac, dignissim metus. Sed"
				+ " molestie purus eget posuere sodales. Maecenas non ante aliquet, maximus metus ac, ", now, p3);
		
		n1.setProfileImage("news-1.jpg");
		n2.setProfileImage("news-2.jpg");
		n3.setProfileImage("news-3.jpg");
		
		noticiaRep.save(n1);
		noticiaRep.save(n2);
		noticiaRep.save(n3);
		n1.getCabras().add(c12);
		noticiaRep.save(n1);
		c12.getNews().add(n1);
		cabraRep.save(c12);
		
		p1.getNews().add(n1);
		p1.getCabras().add(c1);
		
		personaRep.save(p1);
		
		comentary1 = new Comentario("Me parece muy mal la noticia", now, p1, n1);
		comentary2 = new Comentario("Me parece muy bien la noticia", now, p2, n1);
		comentary3 = new Comentario("Me parece muy anormal la noticia", now, p3, n1);
		
		comentarioRep.save(comentary1);
		comentarioRep.save(comentary2);
		comentarioRep.save(comentary3);
		
		
		n1.getComentarios().add(comentary1);
		n1.getComentarios().add(comentary2);
		n1.getComentarios().add(comentary3);
		noticiaRep.save(n1);
		
		
	}
}
