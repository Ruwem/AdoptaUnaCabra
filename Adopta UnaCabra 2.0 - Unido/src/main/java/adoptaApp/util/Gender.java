package adoptaApp.util;

import java.util.ArrayList;
import java.util.HashSet;

public enum Gender {
	Masculino,
	Femenino,
	Desconocido;
	
	public static ArrayList<String> getGenderEnums(){
		
		ArrayList<String> values = new  ArrayList<String>();
		for (Gender c: Gender.values()){
			values.add(c.name().toUpperCase());
		}
		return values;
	}
	public static boolean contains(String test){
		ArrayList<String> values = getGenderEnums();
		return values.contains(test.toUpperCase());
	}

}
