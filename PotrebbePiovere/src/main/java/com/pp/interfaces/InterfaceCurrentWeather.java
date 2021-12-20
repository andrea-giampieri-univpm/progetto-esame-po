package com.pp.interfaces;

import com.pp.exceptions.CurrentWeatherException;

/**
 * Interfacce per l'implementazione della classe
 * CurrentWeather specifica per il progetto
 *
 */
public interface InterfaceCurrentWeather {
	
	/**
	 * aggiunge una riga di json contenente il meteo ad un file di testo
	 */
	public void appendToFile();
		
	/**
	 * @return stringa json ottenuta con jsonsimple del modello semplificato specifico per l'applicazione
	 */
	public String toJsonString();

	/**
	 * @return valore della pressione come double
	 */
	public double getPressure();
	
	/**
	 * @return valore della temperatura come double
	 */
	public double getTemp();
	
	/**
	 * @return valore della temperatura come long
	 */
	public long getId();
	
	/**
	 * @return valore della temperatura come long
	 */
	public long getDt();
	
}
