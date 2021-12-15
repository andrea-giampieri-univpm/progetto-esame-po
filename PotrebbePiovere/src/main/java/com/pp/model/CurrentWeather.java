package com.pp.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.pp.model.owm.OwmCurrentJson;

/**
 * modello contenente i dati e i metodi
 * sul meteo specializzati per il progetto
 * @author Andrea Giampieri
 *
 */
public class CurrentWeather extends OwmCurrentJson {

	/**
	 * Costruttore vuoto
	 */
	public CurrentWeather() {
	}
	
	/**
	 * Costruttore a partire da stringa json.
	 * questo non è equivalente al requestmapper in quanto mappa 
	 * solo i dati rilevanti per l'applicazione
	 * ovvero  id, temperatura, pressione, data
	 * da utilizzarsi per import/export da file
	 * sarebbe dovuto essere un metodo separato, creato per mostrare override costruttore
	 * @param jsonString stringa contenente l'oggetto in formato json
	 */
	public CurrentWeather(String jsonString) {
		JSONParser jparser = new JSONParser();
		try {
			JSONObject json = (JSONObject) jparser.parse(jsonString);
			this.setId((Long) json.get("id"));
			this.getMain().setTemp((Double) json.get("temp"));
			this.getMain().setPressure((Double) json.get("pressure"));
			this.setDt((Long)json.get("dt"));
		} catch (Exception e) {
			System.out.println("Errore parsing stringa json");
		}
	}
	
	/**
	 * Ritorna la data del meteo in formato LocalDateTime
	 * il dato è lo stesso di getDt() ma come oggetto
	 * @return oggetto LocalDateTime con data e ora di riferimento della previsione meteo 
	 */
	public LocalDateTime getFormattedDt() {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(super.getDt()), TimeZone.getTimeZone("Europe/Rome").toZoneId());
	}
	
	/**
	 * Non è un override perchè il getTemp non è della superclasse ma di una classe separata
	 * @return valore della pressione come double
	 */
	public double getPressure() {
		return super.getMain().getPressure();
	}
	
	/**
	 * Non è un override perchè il getTemp non è della superclasse ma di una classe separata
	 * @return valore della pressione come double
	 */
	public double getTemp() {
		return super.getMain().getTemp();
	}
	
	
	
	/**
	 * Override del metodo toString per loggare i dati in console
	 * non utile per json
	 */
	@Override
	public String toString() {
		return "City ("+super.getName()+"):"+super.getId()+" Temp: "+this.getTemp()+" Press: "+this.getPressure()+ " Time: "+this.getFormattedDt();
	}
	
}
