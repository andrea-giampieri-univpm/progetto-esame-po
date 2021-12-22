package com.pp.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.TimeZone;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.pp.exceptions.CurrentWeatherException;
import com.pp.interfaces.InterfaceCurrentWeather;
import com.pp.interfaces.InterfaceJsonString;
import com.pp.model.owm.OwmCurrentJson;
import com.pp.model.owm.OwmMain;
import com.pp.utils.Config;

/**
 * modello contenente i dati e i metodi
 * sul meteo specializzati per il progetto
 * @author Andrea Giampieri
 *
 */
public class CurrentWeather extends OwmCurrentJson implements InterfaceCurrentWeather,InterfaceJsonString {

	/**
	 * Costruttore vuoto
	 */
	public CurrentWeather() {
	}

	/**
	 * Costruttore a partire da stringa json.
	 * questo non è equivalente al requestmapper in quanto mappa 
	 * solo i dati rilevanti per l'applicazione
	 * ovvero  id, temperatura, pressione, data
	 * da utilizzarsi per import/export da file
	 * @param jsonString stringa contenente l'oggetto in formato json (data in unix timestamp)
	 * @throws CurrentWeatherException in caso di stringa vuota o malformata o con dati mancanti
	 */
	public CurrentWeather(String jsonString) throws CurrentWeatherException{
		JSONParser jparser = new JSONParser();
		try {
			JSONObject json = (JSONObject) jparser.parse(jsonString);
			Object id=json.get("id");
			if ((id instanceof Long) && (Long)id!=0)  //se l'id non è numerico o è 0 vado in errore
				this.setId((Long) id);
			else
				throw new CurrentWeatherException("id non valido o non numerico"); //all'eccezione passo il json per diagnostica
			OwmMain main = new OwmMain(); //utilizzo l'oggetto main perchè così è strutturato l'oggetto OwmCurrentJson
			main.setTemp((Double) json.get("temp"));
			main.setPressure((Double) json.get("pressure"));
			this.setMain(main);
			this.setDt((Long)json.get("dt"));
		} catch (ParseException e) {
			throw new CurrentWeatherException("Stringa Json non interpretabile");
		}
	}

	/**
	 * costruisce l'oggetto dalle api. da aggiungere come parametro l'oggetto link quando sarà
	 * @param cityId id città da usare per costruire l'oggetto
	 */
	public CurrentWeather(Long cityId) throws CurrentWeatherException {
		RestTemplate restTemplate = new RestTemplate(); //oggetto mapper di spring
		try {
			//costruisco l'oggetto a partire dall'oggetto ritornato da resttemplate con i soli dati indispensabili
			CurrentWeather cw = restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?id="+cityId+"&appid="+Config.getConf("owm_apikey")+"&units=metric&lang=it", CurrentWeather.class);
			this.setId(cw.getId());
			this.setDt(cw.getDt());
			this.setMain(cw.getMain());
			this.setName(cw.getName());
		} catch (RestClientException e) {
			throw new CurrentWeatherException("errore chiamata api");
		}
	}

	/**
	 * Ritorna la data del meteo in formato LocalDateTime
	 * il dato è lo stesso di getDt() ma come oggetto
	 * @return oggetto LocalDateTime con data e ora di riferimento della previsione meteo 
	 */
	public LocalDateTime getFormattedDt() {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(super.getDt()), TimeZone.getTimeZone("Europe/Rome").toZoneId());
	}

	/**
	 * Non è un override perchè il getTemp non è della superclasse ma di una classe separata
	 * @return valore della pressione come double
	 */
	public double getPressure() {
		return super.getMain().getPressure();
	}

	/**
	 * Non è un override perchè il getTemp non è della superclasse ma di una classe separata
	 * @return valore della pressione come double
	 */
	public double getTemp() {
		return super.getMain().getTemp();
	}

	/**
	 * Consente il salvataggio dell'oggetto come stringa json all'interno di un file
	 * se il file è già esistente, viene aggiunto l'oggetto come riga
	 * il file sarà nel datapath specificato in configurazione
	 * il nome file sarà data_cityid.json
	 */
	public void appendToFile() {
		try {
			//salvo il file
			FileWriter fw = new FileWriter(Config.getConf("data_path")+"data_"+this.getId()+".json",true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(this.toJsonString());
			pw.close(); bw.close(); fw.close();   
		} catch (Exception e) {
			System.out.println("Errore scrittura file");
		} 
	}

	/**
	 * implementazione dell'interfaccia
	 * Ottengo i parametri essenziali del progetto in formato json
	 * @return String con l'oggetto codificato in json 
	 */
	public String toJsonString() {
		HashMap<String, Object> keyvalue= new HashMap<>(); //costruisco un hashmap chiave/valore
		keyvalue.put("id", this.getId());
		keyvalue.put("dt", this.getDt()); //uso la data in unix timestamp per evitare conversioni inutili
		keyvalue.put("temp", this.getTemp());
		keyvalue.put("pressure", this.getPressure());
		JSONObject jsonobj = new JSONObject(keyvalue); //creo l'oggetto JSONObj a partire dall'hashmap per evitare problemi di casting
		return jsonobj.toJSONString();
	}

	/**
	 * Override del metodo toString per loggare i dati in console
	 * non utile per json
	 */
	@Override
	public String toString() {
		return "City ("+super.getName()+"):"+super.getId()+" Temp: "+this.getTemp()+" Press: "+this.getPressure()+ " Time: "+this.getFormattedDt();
	}

}
