package com.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import com.exception.ConfigException;

/**
 * Interfaccia di conversione da file .json a JSONObject
 * 
 */

public interface JsonConversion {

	/**
	 * Lettura file e conversione del contenuto in stringa
	 * @return String
	 */
	abstract public String FileToString(String filename) throws ConfigException;
	
	/**
	 * Deserializza la stringa in un JSONObject
	 * @return JSONObject
	 */
	abstract public JSONObject JsonStringToJsonObject(String jsonString) throws ConfigException;
	
	
	/**
	 * Deserializza la stringa in un JSONArray
	 * @return JSONArray
	 */
	abstract public JSONArray JsonStringToJsonArray(String jsonString) throws ConfigException;
	
	
}
