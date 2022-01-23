package com.deloitte.netflixdataparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan({"com.deloitte.netflix.resource", "com.deloitte.netflix.service"})
public class NetflixDataParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixDataParserApplication.class, args);
	}

}
