package com.serai.POS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Springboot application main class
 * @author Brian
 *
 */
@SpringBootApplication
public class POSApplication extends SpringBootServletInitializer{

	/**
	 * Main method to run entire pizza order system
	 * @author Brian
	 */
	public static void main(String[] args) {
		SpringApplication.run(POSApplication.class, args);
	}

}
