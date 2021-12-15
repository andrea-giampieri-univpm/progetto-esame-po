package com.pp.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

import com.pp.model.owm.OwmCurrentJson;

/**
 * modello contenente i dati e i metodi
 * sul meteo necessari per il progetto
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
