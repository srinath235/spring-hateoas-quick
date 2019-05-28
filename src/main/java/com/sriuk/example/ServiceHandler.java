package com.sriuk.example;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sriuk.example.models.CarType;
import com.sriuk.example.models.CarTypesModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ServiceHandler {
	
	private CarTypesModel carTypesModel;
	
	public ServiceHandler(CarTypesModel carTypesModel) {
		this.carTypesModel = carTypesModel;
	}

	public Mono<ServerResponse> retrieveAllCarTypes(ServerRequest request) {
		Flux<CarType> carTypes = Mono.just(this.carTypesModel.getCarTypes()).flatMapMany(Flux::fromIterable);
		return ServerResponse.ok().body(BodyInserters.fromPublisher(carTypes, CarType.class));
		
	}
}
