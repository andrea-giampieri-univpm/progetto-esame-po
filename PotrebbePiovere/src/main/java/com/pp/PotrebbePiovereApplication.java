package com.pp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.pp.exceptions.ConfigException;
import com.pp.utils.Config;

@SpringBootApplication
@EnableScheduling
public class PotrebbePiovereApplication {

	public static void main(String[] args) {
		//inizializzo configurazione, se non c'è viene terminata l'app
		try {
			Config.initialize();
		} catch (ConfigException e) {
			System.out.println(e);
			System.exit(1);
		}
		
		//avvio spring
		SpringApplication.run(PotrebbePiovereApplication.class, args);
	}

}
