package com.sriuk.example;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sriuk.example.models.Applicant;

public class DataBindApp {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		Applicant applicant = mapper.readValue(DataBindApp.class.getClassLoader().getResource("sample.json").openStream(), Applicant.class);
		System.out.println("Applicant"+applicant);
	}

}
