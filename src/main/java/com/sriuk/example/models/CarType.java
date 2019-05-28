package com.sriuk.example.models;

import org.springframework.hateoas.ResourceSupport;

import lombok.Data;

@Data
public class CarType{

	private String type;
	private Integer code;
	private String[] examples;
}
