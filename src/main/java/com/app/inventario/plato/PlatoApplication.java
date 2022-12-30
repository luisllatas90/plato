package com.app.inventario.plato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.inventario.plato.infrastructure.config.AxonConfig;

@SpringBootApplication
@Import({ AxonConfig.class })
public class PlatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatoApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}

}
