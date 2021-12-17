package com.pp.exceptions;

/**
 * Eccezioni per la classe CurrentWeather
 * @author Andrea Giampieri
 *
 */
public class CurrentWeatherException extends Exception {

	static final long serialVersionUID=0; //implementazione consigliata (warning) da classe exception
	
	private String JsonError= "{error:exception}";
	
	/**
	 * eccezione personalizzata
	 */
	public CurrentWeatherException(){
		super("id non numerico o sbagliato. By the orders of the fookin peaky blinders");
	}
	
	/**
	 * eccezione personalizzata con parametro, passa il parametro in output.
	 */
	public CurrentWeatherException(String string){
		super("id non numerico o sbagliato:\n"+string);
	}
	
}
