package com.sriuk.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sriuk.example.models.CarTypesModel;

@SpringBootApplication
public class SpringHateoasQuickApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHateoasQuickApplication.class, args);
	}

	@Bean
	public CarTypesModel carTypesModel() {
		CarTypesModel carTypesModel = null;

		try {
			File file = ResourceUtils.getFile("classpath:car-types.json");
			ObjectMapper mapper = new ObjectMapper();
			try {
				carTypesModel = mapper.readValue(file, CarTypesModel.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return carTypesModel;
	}
}
