package com.anuj.jakson.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {
		try {
			// create object mapper
			ObjectMapper mapper= new ObjectMapper();
			
			//read JSON File and map convert to Java Pojo
			//data-sample-lite.json
			Student theStudnet = mapper.readValue(new File("data/sample-full.json"), Student.class);
			// print first name and last name
			System.out.println(theStudnet.getFirstName());
			System.out.println(theStudnet.getLastName());
			
			// print out adress
			Address tempAdress = theStudnet.getAddress();
			System.out.println("Street : "+tempAdress.getStreet());
			System.out.println("City : "+tempAdress.getCity());
			System.out.println("Zip : "+tempAdress.getZip());
			
			// print out array
			for(String templan:theStudnet.getLanguages()) {
				System.out.println("Languages are"+templan);
				
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
