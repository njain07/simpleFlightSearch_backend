package com.united.flightsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
//		File file = new File("/home/nikita/Documents/workspace-spring-tool-suite-4-4.2.2.RELEASE/demo/src/main/resources/flight-docs/flight-sample.json");		
		
		JSONParser parser = new JSONParser();
		JSONObject flight;
		String jsonText;
		JSONArray flights = new JSONArray();
		try 
		{
			jsonText = new String(Files.readAllBytes(Paths.get("src/main/resources/flight-docs/flight-sample.json")));
			flights = (JSONArray) parser.parse(jsonText);
		} 
		catch (Exception e) 
		{
			System.out.print("ERROR: " + e);
			System.exit(1);
		};
		
		
		String flightNumberQuery = "1128";
		String originQuery = "xxx";
		String destinationQuery = "xxx";
		String dateQuery = "2018-01-31";
		String departure = "";
		JSONArray matchingFlights = new JSONArray();
		for(int i=0; i<flights.size(); i++)
		{
			flight = (JSONObject) flights.get(i);
			
			departure = (String) flight.get("departure");
			departure = departure.substring(0, 10);
			
			if(dateQuery.equals(departure))
			{
				if(flightNumberQuery.equals(flight.get("flightNumber")))
				{
					matchingFlights.add(flight);
				}
				if(originQuery.equals(flight.get("origin")) && destinationQuery.equals(flight.get("destination")))
				{
					matchingFlights.add(flight);
				}
			}			
		}
		
		System.out.print(matchingFlights);
	}
}
