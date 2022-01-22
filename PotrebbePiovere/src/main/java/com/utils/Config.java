package com.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.data.DatabaseManagment;
import com.exception.ConfigException;
import com.service.WeatherService;

/**
 * Classe per gestire la configurazione dell'applicazione.
 * 
 */

@Configuration
public class Config implements JsonConversion{
	
	@Autowired
	WeatherService service;
	
	@Autowired
	DatabaseManagment database;
	

	private JSONObject conf; //configurazione gestita come keyvalue in un json obj

	/**
	 * Legge il file config.json nella root del progetto e termina nel caso in cui
	 * non trova l'API_KEY 
	 * @throws ConfigException
	 */
	@Bean
	public void initialize() throws ConfigException  {
		try {
			conf = JsonStringToJsonObject(FileToString("config.json"));
			//check dei parametri indispensabili ed output diagnostico
			if (!getElem("apiKey").equals("")) {
				setApiKey(getElem("apiKey").toString());
				if (!getElem("cities").equals("")) 
					startCitiesD​ataCapture(conf.getJSONArray("cities"));
				//Creazione oggetto CityList
				new CityList(JsonStringToJsonArray(FileToString("city.list.json")));		
			}
			else {
				System.out.println("ApiKey not found");
				System.exit(1);
			}
		}catch (JSONException e){
			throw new ConfigException("json object creation failed.");
		}
	}
	
	
	/**
	 * metodo che implementa l'interfaccia JsonConversion
	 * @throws ConfigException 
	 * @return String
	 * @see JsonConversion
	 */
    public String FileToString(String fileName) throws ConfigException {
    	String line;  
        StringBuilder stringbuilder = new StringBuilder();
    	try { 
    		BufferedReader br = new BufferedReader(new FileReader(fileName));
    		while((line = br.readLine()) !=null) {
    			stringbuilder.append(line);
    		}
    	}catch(FileNotFoundException e) {
    		System.out.println("file di configurazione " + fileName + " not found" );
    		System.exit(1);
    	} catch(IOException e) {
    		throw new ConfigException(" I/O error ");
    	}
        return stringbuilder.toString();
    }
 

	/**
	 * metodo che implementa l'interfaccia JsonConversion
	 * @throws ConfigException 
	 * @return JSONObject
	 * @see JsonConversion
	 */
	public JSONObject JsonStringToJsonObject(String jsonString) throws ConfigException {
		try {
			return new JSONObject(jsonString);
		} catch (JSONException e) {
			throw new ConfigException("ERRORE: Creazione oggetto json non riuscita");
		}
	}
	
	/**
	 * Metodo per ottenere un parametro di configurazione specifico
	 * L'oggetto di ritorno deve essere controllato, può essere stringa, long, double, ecc...
	 * @param param Stringa del nome parametro da ottenere
	 * @return il valore del parametro associato come Object o una stringa vuota se non esistente
	 */
	public Object getElem(String param) {
		if(conf.has(param) && !conf.isEmpty() && !conf.isNull(param)) 
			return conf.get(param); 
		else 
			return "";
	}
	
	
	/**
	 * Metodo che passa l'API_KEY alla classe che ha il compito di effettuare 
	 * la chiamata alla API OpenWeatherMap
	 * @see WeatherService
	 */	
	private void setApiKey(String key) {
		service.setApiKey(conf.getString("apiKey"));		
	}

	
	/**
	 * metodo che inizializza le città su cui effettuare statistiche meteo
	 * @throws ConfigException 
	 */
	private void startCitiesD​ataCapture(JSONArray json) throws ConfigException {
		try {
			for (int i=0; i<json.length(); i++) {
				database.addCity(json.getInt(i));
			}
		} catch(JSONException e) {
			throw new ConfigException("Cities value are not valid in the configuration file.");
		}
		
	}




	/**
	 * metodo per creare l'oggetto CityList che tarmite costruttore converte il JSONArray  
	 * in due liste, una per tutti i nomi e una per tutti gli ID di città
	 * @throws ConfigException 
	 * @see CityList
	 */
    public JSONArray JsonStringToJsonArray(String jsonString) throws ConfigException {
    	try {
    		return new JSONArray(jsonString);
    	} catch(JSONException e) {
    		throw new ConfigException("Creazione oggetto json non riuscita");
    	}
    }
    
    

    
    

    
    
}
