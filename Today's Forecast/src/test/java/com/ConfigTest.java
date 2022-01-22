package com;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.exception.ConfigException;
import com.utils.Config;

/**
 * Test per la classe config
 *
 */
class ConfigTest {
	Config config = new Config();
	@Test
	@Order(1)
	@DisplayName("Test letture parametri errati")
	public void configReadparam() {
		try {
			assertEquals("", config.getElem("asdd"));
			assertEquals("", config.getElem(""));
			assertEquals("", config.getElem(null));
		} catch (Exception e) {
			
		}
	}	
	
	
	@Test
	@Order(2)
	@DisplayName("Test conversione stringa in JSONObject")
	public void construtor2() {
		String jsonString="{\"cities\":[1639608458,3183089],\"apiKey\":7deb142293a7b78618d16a8b358dede3}";
		assertDoesNotThrow(() ->  config.JsonStringToJsonObject(jsonString));
	}

	
	@SuppressWarnings("unused")
	@Test
	@Order(3)
	@DisplayName("Test conversione stringa in JSONObject")
	public void constructor1() {
		String jsonString="";
		assertThrows(ConfigException.class, () -> {
			JSONObject json = config.JsonStringToJsonObject("");
	  });
	}

}