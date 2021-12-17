package com.pp.exceptions;

public class ConfigException  extends Exception{

	static final long serialVersionUID=0; //implementazione consigliata (warning) da classe exception

	/**
	 * eccezione personalizzata con parametro, passa il parametro in output.
	 */
	public ConfigException(String string){
		super("Errore configurazione:\n"+string);
	}
	
}
