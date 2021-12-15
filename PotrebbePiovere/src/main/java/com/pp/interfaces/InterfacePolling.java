package com.pp.interfaces;

/**
 * interfaccia per i metodi della classe che si occuperà di 
 * scaricare i dati in polling
 *
 */
public interface InterfacePolling {

	/**
	 * Definizione di un metodo per il download dei dati da owm
	 */
	public void getCurrentWeather();

}
