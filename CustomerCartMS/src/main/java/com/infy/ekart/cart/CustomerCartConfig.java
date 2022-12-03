package com.infy.ekart.cart;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerCartConfig {
    
private RestTemplate template = new RestTemplate();
	
	@Bean
	@LoadBalanced
	//Completed: Make this as load-balanced rest template
	public RestTemplate restTemplate() {
		//Completed: return template object created above
		return template;
	}
	
}
