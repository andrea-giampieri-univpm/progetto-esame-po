package com.pp;

import static org.junit.Assert.assertThrows;
import java.io.File;
import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.pp.utils.Config;

class ConfigTest {
	
	@BeforeAll
	public static void secureConfig() {
		File conf = new File("config.json");
		File confbak = new File("config.json.bak");
		conf.renameTo(confbak);
	}
		 
	@Test
	@DisplayName("Test lettura configurazione inesistente")
	public void configReadUnavailable() {
		assertThrows(NullPointerException.class, () -> {
			File conf = new File("config.json");
			conf.delete();
			Config.initialize();
			});	
	}

	@After
	public static void restoreConf() {
		File conf = new File("config.json");
		File confbak = new File("config.json.bak");
		conf.delete();
		conf = new File("config.json");
		confbak.renameTo(conf);
	}
	


}