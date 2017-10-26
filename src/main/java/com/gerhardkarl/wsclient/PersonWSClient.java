package com.gerhardkarl.wsclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.gerhardkarl.ws.StringListConverter;
import com.gerhardkarl.ws.Person;
import com.gerhardkarl.ws.PersonWrapper;
import com.sun.istack.Builder;


public class PersonWSClient {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private StringListConverter converter = new StringListConverter();

	// Default URL
	private String BASE_URL = "http://localhost:8080/GKRestWebService/rs/json";
	private Properties properties = new Properties();

	
	private void initProperties() {
		InputStream is = getClass().getClassLoader().getResourceAsStream("application.properties");

		if (is != null) {
			try {
				properties.load(is);
				
				BASE_URL = (String)properties.getProperty("person.restws.url");	
				
			} catch (IOException e) {				
				logger.error("Error when reading properties: ", e);
				throw new RuntimeException("Can not load application.properties file.");
			}
		} else {		
			logger.error("Error when finding application.properties.");
			throw new RuntimeException("Error when finding application.properties.");
		}
	}
	
	/**
	 * Retrieve over 20 years
	 * @param anAge
	 * @return
	 */
	public List<Person> getPersonsOverAge(String anAge) {	
		
		Client client = ClientBuilder.newClient();
		WebTarget myResource = client.target(BASE_URL + "/overAge").path(anAge);
		
		PersonWrapper wrapper = null;
		try {
			
		  wrapper = myResource.request(MediaType.APPLICATION_JSON).get( PersonWrapper.class);
			
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}
		return wrapper.getList();
	}

	
	
	/**
	 * Persons by name
	 * @param PersonNames
	 * @return
	 */
	public List<Person> getPersonsByName(List<String> PersonNames) {	
		
		Client client = ClientBuilder.newClient();
		WebTarget myResource = client.target(BASE_URL + "/PersonsByName").queryParam( "names", converter.toString(PersonNames));
		
		PersonWrapper wrapper = null;
		try {
			
		  wrapper = myResource.request(MediaType.APPLICATION_JSON).get( PersonWrapper.class);
			
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}
		return wrapper.getList();
	}
	
	/**
	 * 
	 * @param PersonNames
	 * @return
	 */
	public List<Person> getPersonsByNameJson(List<String> PersonNames) {	
		
		Client client = ClientBuilder.newClient();
		String nameJson = converter.toJson(PersonNames);
		WebTarget myResource = client.target(BASE_URL + "/PersonsByNameJson");
		
		PersonWrapper wrapper = null;
		try {
			
		  wrapper = myResource.request(MediaType.APPLICATION_JSON).put(Entity.text(nameJson), PersonWrapper.class);
			
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}
		return wrapper.getList();
	}
	
}
