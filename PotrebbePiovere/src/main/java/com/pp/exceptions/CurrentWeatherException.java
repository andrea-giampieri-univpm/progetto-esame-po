package com.pp.exceptions;

public class CurrentWeatherException extends Exception {

	static final long serialVersionUID=0L; //implementazione consigliata (warning) da classe exception
	
	public String JsonError= "{error:exception}";
	
	/**
	 * eccezione personalizzata
	 * @author Andrea Giampieri
	 */
	public CurrentWeatherException(){
		super("By the orders of the fookin peaky blinders");
	}
	
}
