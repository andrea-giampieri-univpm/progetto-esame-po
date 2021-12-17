package com.pp.exceptions;

public class ConfigUnavailableException extends Exception {
	
	static final long serialVersionUID=0L; //implementazione consigliata (warning) da classe exception
	
	/**
	 * Errore per la configurazione non disponibile per qualche motivo non specificato.
	 */
	public ConfigUnavailableException() {
		super("Errore file di configurazione");
	}
	
	/**
	 * Errore per la configurazione non disponibile 
	 * l'errore viene specificato indicando la classe di errore che l'ha generato
	 * 
	 * @param err errore chiamante con specifica errore precedente
	 */
	public ConfigUnavailableException(Throwable err) {
		super("Errore file di configurazione: " + err.getClass() );
	}

}
