package com.sriuk.example;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sriuk.example.models.CarType;
import com.sriuk.example.models.CarTypeDTO;
import com.sriuk.example.models.CarTypesModel;

@RestController
public class ServiceController {

	private CarTypesModel carTypesModel;

	public ServiceController(CarTypesModel carTypesModel) {
		this.carTypesModel = carTypesModel;
	}

	@GetMapping(path = "/all")
	public List<CarType> retrieveAllCarTypes() {
		return this.carTypesModel.getCarTypes();
	}

	@GetMapping(path = "/allWithLinks", produces = { "application/hal+json" })
	public List<CarTypeDTO> retrieveAllCarTypesWithLinks() {
		List<CarType> carTypes = this.carTypesModel.getCarTypes();
		List<CarTypeDTO> carTypeDTOs = new ArrayList<>();
		for (final CarType carType : carTypes) {
			CarTypeDTO carTypeDTO = new CarTypeDTO();
			Link selfLink = linkTo(methodOn(ServiceController.class).retrieveCarType(carType.getCode())).withSelfRel();
			
			carTypeDTO.setCode(carType.getCode());
			carTypeDTO.setType(carType.getType());
			carTypeDTO.setLinkToGetExamples(selfLink.getHref());
			carTypeDTOs.add(carTypeDTO);
		}
		return carTypeDTOs;

	}

	@GetMapping(path = "/carType/{code}")
	public CarType retrieveCarType(@PathVariable("code") Integer code) {
		return this.carTypesModel.getCarTypes().stream().filter(carType -> carType.getCode() == code).findAny()
				.orElse(new CarType());
	}

}
