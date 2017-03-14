package adoptaApp.util;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import adoptaApp.entity.Cabra;
import adoptaApp.entity.Centro;
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
	
	
	@PostConstruct
	private void initDataBase(){
		Cabra c1,c2,c3,c4,c5;
		Centro l1,l2;
		Noticia n1,n2,n3;
		Persona p1,p2,p3,p4,p5;
		
		Date d = new Date(1996,12,27);
		
		c1 = new Cabra("David", "Jamnapari", d, 3456, 3);
		c2 = new Cabra("Topo", "Jamnapari", d, 3453, 67);
		c3 = new Cabra("Marco", "Nazi", d, 53, 78);
		c4 = new Cabra("TT", "Pedri", d, 42343, 87);
		c5 = new Cabra("Luzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz", "PaCabra", d, 423, 78);		
		

			
		l1 = new Centro("Rancho de tu puta madre", "tu puta madre", "tuputamadre@cabritas.com", 916975432);
		l2 = new Centro("VillaCabra", "Talavera de la mierda", "villacabrita@cabritas.com", 925975432);

		
		p1 = new Persona("David", "cabraySusCabras@hotmail.com",d);
		p2 = new Persona("Paquirrin", "soyunhijoputa@hotmail.com", d);
		p3 = new Persona("Marcela", "soyunmono@hotmail.com", d);
		p4 = new Persona("Pedro", "soyunnazi@hotmail.com", d);
		p5 = new Persona("Javier", "soyunputoamo@hotmail.com", d);
		

		
		
		cabraRep.save(c1);
		cabraRep.save(c2);
		cabraRep.save(c3);
		cabraRep.save(c4);
		cabraRep.save(c5);
		
		centroRep.save(l1);
		centroRep.save(l2);
		
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

		
		n1 = new Noticia("Marco recluta a Chus", "Chus intenta librarse de la css pero no lo consigue", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vitae nibh semper "
				+ "leo maximus tempus. Duis vitae erat mollis, tincidunt dolor ac, dignissim metus. Sed"
				+ " molestie purus eget posuere sodales. Maecenas non ante aliquet, maximus metus ac", d, p1);
		
		n2 = new Noticia("Cabra despeñada", "Llora puta llora que no va a resucitar", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vitae nibh semper "
				+ "leo maximus tempus. Duis vitae erat mollis, tincidunt dolor ac, dignissim metus. Sed"
				+ " molestie purus eget posuere sodales. Maecenas non ante aliquet, maximus metus ac", d, p2);
		
		n3 = new Noticia("Titulo otro", "Chus intenta librarse de la css pero no lo consigue", 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vitae nibh semper "
				+ "leo maximus tempus. Duis vitae erat mollis, tincidunt dolor ac, dignissim metus. Sed"
				+ " molestie purus eget posuere sodales. Maecenas non ante aliquet, maximus metus ac, ", d, p3);

		
		noticiaRep.save(n1);
		noticiaRep.save(n2);
		noticiaRep.save(n3);
		
		
	}
}
