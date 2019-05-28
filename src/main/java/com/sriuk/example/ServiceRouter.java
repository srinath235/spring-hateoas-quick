package com.sriuk.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ServiceRouter {
	@Bean
	public RouterFunction<ServerResponse> route(ServiceHandler serviceHandler) {
		return RouterFunctions.route(RequestPredicates.GET("/all"), serviceHandler::retrieveAllCarTypes);
	}
}
