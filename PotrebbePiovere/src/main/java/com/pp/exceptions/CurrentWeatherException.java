package com.pp.exceptions;

public class CurrentWeatherException extends Exception {

<<<<<<< HEAD
	static final long serialVersionUID=0L; //implementazione consigliata (warning) da classe exception
	
	public String JsonError= "{error:exception}";
	
=======
	static final long serialVersionUID=0; //implementazione consigliata (warning) da classe exception

>>>>>>> parent of abeca66 (aggiunto controllo eccezioni trycatch getinstant)
	/**
	 * eccezione personalizzata
	 * @author Andrea Giampieri
	 */
	public CurrentWeatherException(){
		super("By the orders of the fookin peaky blinders");
	}
}
