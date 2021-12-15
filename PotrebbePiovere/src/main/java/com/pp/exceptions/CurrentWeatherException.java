package com.pp.exceptions;

public class CurrentWeatherException extends Exception {

	static final long serialVersionUID=0; //implementazione consigliata (warning) da classe exception

	/**
	 * eccezione personalizzata
	 * @author Andrea Giampieri
	 */
	public CurrentWeatherException(){
		super("By the orders of the fookin peaky blinders");
	}
}
