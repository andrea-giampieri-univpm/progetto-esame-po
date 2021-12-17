package com.pp.exceptions;

public class WrongConfigException extends Exception{

	static final long serialVersionUID=0L; //implementazione consigliata (warning) da classe exception

	/**
	 * Parametri del file di configurazione minima non corretti
	 */
	public WrongConfigException() {
		super("Errore parametri configurazione mancanti o errati");
	}
}
