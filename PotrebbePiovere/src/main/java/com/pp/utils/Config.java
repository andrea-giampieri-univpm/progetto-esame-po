package com.pp.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Classe per gestire la configurazione dell'applicazione.
 * La classe richiede di lanciare il metodo initialize() per leggere il file e rendere disponibile la configurazione
 * Volutamente non implementato un modello dati specifico ma utilizza esclusivamente l'oggetto JSONObject
 * @author Andrea Giampieri
 *
 */
public class Config {

	private static JSONObject conf; //configurazione gestita come keyvalue in un json obj

	/**
	 * Legge il file config.json nella root del progetto 
	 * rende disponibili i vari parametri
	 * se il file è assente/illegibile, termina l'applicazione (in quanto indispensabile)
	 */
	public static void initialize() {
		JSONParser jparser = new JSONParser();
		try {
			conf = (JSONObject) jparser.parse(new BufferedReader (new FileReader ("config.json" ))); //parsing diretto del flusso	
			//check dei parametri indispensabili ed output diagnostico
			if(conf.containsKey("owm_apikey"))System.out.println("Using API KEY: "+conf.get("owm_apikey")); else throw new ParseException(0);
			if(conf.containsKey("h_period")) System.out.println("Using period H: "+conf.get("h_period")); else throw new ParseException(0);
			if(conf.containsKey("data_path")) System.out.println("Using data path: "+conf.get("data_path")); else throw new ParseException(0);
		} catch (FileNotFoundException e) {
			//dimostrazione gestione separata degli errori (in questo caso poco utile)
			System.out.println("ERRORE: file di configurazione non trovato, app termina esecuzione.");
			System.out.println(e);
			System.exit(1);
		} catch (Exception e){
			System.out.println("ERRORE NON SPECIFICATO: verifica il log, app termina esecuzione.");
			System.out.println(e);
			System.exit(1);
		}
	}
	
	/**
	 * Metodo per ottenere un parametro di configurazione specifico
	 * L'oggetto di ritorno deve essere controllato, può essere stringa, long, double, ecc...
	 * @param param Stringa del nome parametro da ottenere
	 * @return il valore del parametro associato come Object o null se non esistente
	 */
	public static Object getConf(String param) {
		if(conf.containsKey(param)) return conf.get(param); else return null;
	}
	
	/**
	 * Metodo per impostare un parametro nuovo o sovrascrivere un parametro esistente
	 * @param param stringa del nome parametro
	 * @param value oggetto contenente il valore associato al parametro
	 */
	public static void setConf(String param, Object value)  {
		try {
			HashMap<String,Object> keyvalue = new HashMap<>();
			keyvalue.put(param, value);
			conf.putAll(keyvalue);
		} catch (UnsupportedOperationException e ) {
			System.out.println(e);
		}
		
	}
	
	/**
	 * Metodo aggiuntivo a setconf che permette di scegliere se effettuare il salvataggio della configurazione su file
	 * @overload setConf()
	 * @param param stringa del nome parametro
	 * @param value oggetto contenente il valore associato al parametro
	 * @param commit impostare su true per salvare il file
	 * @return
	 */
	public static void setConf(String param, Object value, boolean commit)  {
		conf.put(param, value);
		if (commit) Config.commit();
	}
	
	/**
	 * metodo per salvare la configurazione in json su file. sovrascrive la vecchia config
	 */
	public static void commit() {//ma perchè il try ha le parentesi
        try (BufferedWriter br = new BufferedWriter(new FileWriter("config.json" ))) {
            br.write(conf.toJSONString());
            br.close();
        } catch (Exception e) {
        	System.out.println("Errore scrittura file configurazione");
        }
	}
	
	/**
	 * metodo diretto per ottenere un array con la lista delle città di cui salvare i dati
	 * da valutare inserimento in eventuale sottoclasse
	 * @return arraylist di long contenente gli id delle città, null se errore o vuoto 
	 */
	public static ArrayList<Long> getCities(){
		try {
			ArrayList<Long> cities = (ArrayList<Long>) conf.get("cities");
			return cities;
		} catch (ClassCastException e) {
			System.out.println("Errore formato dati da json");
			return null;
		}	
	}
	
	/**
	 * Converte l'oggetto configurazione in una stringa json
	 * @return String con la configurazione completa
	 */
	public static String toJsonString() {
		return conf.toString();
	}
}
