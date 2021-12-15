package com.pp.interfaces;

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
	 * ritorna stringa json ottenuta con jsonsimple del modello semplificato specifico per l'applicazione
	 */
	public String toJsonString();


}
