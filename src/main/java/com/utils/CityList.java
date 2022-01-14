package com.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;

public class CityList {
	
	public static ArrayList<String> listName = new ArrayList<String>();
	
	public static ArrayList<Integer> listId = new ArrayList<Integer>();
	
    public CityList() throws IOException {
    	//Reading JSON from file   
        BufferedReader br = new BufferedReader(new FileReader("city.list.json"));            
        String line;  
        StringBuilder stringbuilder = new StringBuilder();
        while((line=br.readLine()) !=null) {
            stringbuilder.append(line);
        }
        //Create JSONArray from stringbuilder
        JSONArray arrObj = new JSONArray(stringbuilder.toString());

        //Add all "name" key values to the list
        for (int i = 0; i < arrObj.length(); i++) {
        	listName.add(arrObj .getJSONObject(i).getString("name"));
        	listId.add(arrObj .getJSONObject(i).getInt("id"));
        }
    }
    

}

