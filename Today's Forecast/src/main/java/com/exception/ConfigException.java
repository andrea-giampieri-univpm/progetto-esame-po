package com.exception;

/**
 * Classe per errori da classe Config
 */
public class ConfigException  extends Exception{

	//implementazione consigliata (warning) da classe exception
	static final long serialVersionUID=0; 
	
	
	public ConfigException(){
		System.out.println("Configuration Exception: "+ super.getMessage());
	}
	
	
	/**
	 * eccezione personalizzata con parametro, passa il parametro in output.
	 * @param string testo aggiuntivo da inserire nell'errore
	 */
	public ConfigException(String string){
		super("Configuration Exception: "+ string);
	}
	
}
