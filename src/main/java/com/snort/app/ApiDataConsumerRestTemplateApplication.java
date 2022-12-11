package com.snort.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ApiDataConsumerRestTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDataConsumerRestTemplateApplication.class, args);

		log.info("SBT-ApiDataConsumerRestTemplateApplication started..!");
	}


	@Bean
	public WebClient init() {
		return  WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com/")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

}
