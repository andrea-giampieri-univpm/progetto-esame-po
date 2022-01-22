package com.exception;

/**
 * Eccezioni per le classi che includono l'oggetto Weather
 */
public class WeatherException extends Exception {

	//implementazione consigliata (warning) da classe exception
	static final long serialVersionUID=0; 
		
	/**
	 * eccezione personalizzata
	 */
	public WeatherException(){
		super("WeatherException: Error weather objects being created");
	}
	
	/**
	 * eccezione personalizzata con parametro, passa il parametro in output.
	 */
	public WeatherException(String string){
		super(string);
	}
	
	
}
