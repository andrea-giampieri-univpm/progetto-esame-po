package com.pp.model;


import java.text.MessageFormat;

import com.pp.utils.Config;

/**
 * CLASSE DI PROVA, NON UTILIZZARE
 * ESPERIMENTI VARI
 * @author Andrea Giampieri
 * @version 1.0.0
 * 
 * Oggetto per creare ed inviare una richiesta https alle api di openweathermap.
 * Instanziare l'oggetto con i parametri (al minimo quelli del costruttore) ed inviare la richiesta.
 * Richiede json simple library.
 */
public class CurrentWeatherLinkDRAFT {

	private final String baseUrl="https://api.openweathermap.org/data/2.5/weather?id={0}&appid={1}";
	private final String baseLang="&lang={0}";
	private final String baseUnits="&units={0}";
	private String id;
	private String apikey;
	private String lang;
	private String units;
	private String requestLink;
	
	/**
	* Crea l'oggetto per inviare una richiesta alle <a href="https://openweathermap.org/current#cityid"> api openweathermap</a>. 
	* L'api di riferimento è current#cityid.
	* I parametro del costruttore sono obbligatori, i parametri aggiuntivi hanno metodi specifici.  
	* Le stringhe dei parametri vengono iniettate nell'url base tramite metodi MessageFormat. 
	*
	* @param  id  id città come specificato da <a href="http://bulk.openweathermap.org/sample/"> lista di openwheathermap</a>
	*/
	public CurrentWeatherLinkDRAFT(String id) {
		this.id=id;
		this.apikey=(String)Config.getConf("apikey");
		this.apikey=apikey.substring(apikey.length()-5);
		this.requestLink = MessageFormat.format(baseUrl, this.id, Config.getConf("apikey"));
	}
	
	/**
	* Aggiunge la lingua come parametro all'url da chiamare. 
	*
	* @param  lang  identificativo della lingua come specificato da <a href="https://openweathermap.org/current#multi"> owm</a>
	*/
	public void setLang(String lang) {
		this.lang= lang;
		this.requestLink+=MessageFormat.format(this.baseLang,lang);
	}
	
	/**
	* Aggiunge il tipo di unità di misura come parametro all'url da chiamare. 
	* @param  units  identificativo delle unità di misura come specificato da owm
	*/
	public void setUnits(String units) {
		this.units= units;
		this.requestLink+=MessageFormat.format(this.baseUnits,units);
	}
	
	public String getRequestLink() {
		return requestLink;
	}
	
	/**
	 * Override del metodo toString per diagnostica.
	 * @return stringa leggibile dei parametri della richiesta.
	 */
	
	public String toString() { 
		return "Params: \nID: "+id+"\n Key: *****"+this.apikey+"\n Language: "+this.lang +"\n Units: "+this.units +"\nFinal link: "+this.requestLink;
	}
}
