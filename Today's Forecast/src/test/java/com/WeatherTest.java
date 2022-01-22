package com;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import com.model.Weather;
import com.service.WeatherService;
import com.utils.Config;


/**
 * Test per la classe CurrentWeather
 */
class WeatherTest {	
	

	Weather weather = new Weather();
	
	static WeatherService service = new WeatherService();
	
	static JSONObject json;
	
	@BeforeAll
	public static void init() {
		try {
			 Config config = new Config();
			 json = new JSONObject(config.FileToString("config.json"));
			 service.setApiKey(json.getString("apiKey"));
			 } catch (Exception e) {
			System.out.println("Errore configurazione apiKey");
		}
	}
	
	
	@Test
	@Order(1)
	@DisplayName("Test creazione oggeto meteo regolare")
	public void weatherTest() throws JSONException {
		String jsonString="{\n"
				+ "  \"coord\": {\n"
				+ "    \"lon\": -122.08,\n"
				+ "    \"lat\": 37.39\n"
				+ "  },\n"
				+ "  \"weather\": [\n"
				+ "    {\n"
				+ "      \"id\": 800,\n"
				+ "      \"main\": \"Clear\",\n"
				+ "      \"description\": \"clear sky\",\n"
				+ "      \"icon\": \"01d\"\n"
				+ "    }\n"
				+ "  ],\n"
				+ "  \"base\": \"stations\",\n"
				+ "  \"main\": {\n"
				+ "    \"temp\": 282.55,\n"
				+ "    \"feels_like\": 281.86,\n"
				+ "    \"temp_min\": 280.37,\n"
				+ "    \"temp_max\": 284.26,\n"
				+ "    \"pressure\": 1023,\n"
				+ "    \"humidity\": 100\n"
				+ "  },\n"
				+ "  \"visibility\": 16093,\n"
				+ "  \"wind\": {\n"
				+ "    \"speed\": 1.5,\n"
				+ "    \"deg\": 350\n"
				+ "  },\n"
				+ "  \"clouds\": {\n"
				+ "    \"all\": 1\n"
				+ "  },\n"
				+ "  \"dt\": 1560350645,\n"
				+ "  \"sys\": {\n"
				+ "    \"type\": 1,\n"
				+ "    \"id\": 5122,\n"
				+ "    \"message\": 0.0139,\n"
				+ "    \"country\": \"US\",\n"
				+ "    \"sunrise\": 1560343627,\n"
				+ "    \"sunset\": 1560396563\n"
				+ "  },\n"
				+ "  \"timezone\": -25200,\n"
				+ "  \"id\": 420006353,\n"
				+ "  \"name\": \"Mountain View\",\n"
				+ "  \"cod\": 200\n"
				+ "  }";
		json =new JSONObject(jsonString);
		weather.setWeather(json);
		assertNotNull(weather);
	}
	
	
	@Test
	@Order(2)
	@DisplayName("Test lettura id errato")
	public void serviceTestInvalid() {
		assertThrows(Exception.class, () -> {
			service.getWeatherByCityId(123);
	  });
	}
	
	
	@Test
	@Order(3)
	@DisplayName("Test lettura id corretto")
	public void serviceTest() {
		assertNotNull(service.getWeatherByCityId(6542152));
	}
	



}