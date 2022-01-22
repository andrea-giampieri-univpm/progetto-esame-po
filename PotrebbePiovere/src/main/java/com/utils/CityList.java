package com.utils;

import java.util.ArrayList;
import org.json.JSONArray;

/**
 *Classe per il controllo della validità delle città fornite in input
 */
public class CityList {
	
	//ArrayList contenente tutti i nomi di città
	public static ArrayList<String> nameList = new ArrayList<String>();
	
	//ArrayList contenente tutti gli ID di città
	public static ArrayList<Integer> idList = new ArrayList<Integer>();
	
	
	public CityList(JSONArray array) {
		for (int i = 0; i < array.length(); i++) {
			CityList.nameList.add(array.getJSONObject(i).getString("name"));
			CityList.idList.add(array.getJSONObject(i).getInt("id"));
		} 
	}


	/**
	 *metodo che verifica l'esistenza di un ID di città
	 */
    public static boolean isPresent(int elem) {
    	boolean found = false;
    	if (idList.contains(elem))
    		found = true;
    	return found;
    }
   
    /**
	 *metodo che verifica l'esistenza di un nome di città
	 */
    public static boolean isPresent(String elem) {
    	boolean found = false;
    	if (nameList.contains(elem) || nameList.contains(uppercaseFirstChar(elem)))
    		found = true;
    	return found;
    }
    
    
	public static String uppercaseFirstChar(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
    
}

