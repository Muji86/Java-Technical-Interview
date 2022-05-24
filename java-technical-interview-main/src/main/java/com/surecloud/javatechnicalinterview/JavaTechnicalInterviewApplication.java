package com.surecloud.javatechnicalinterview;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class JavaTechnicalInterviewApplication {

	public static void main( String[] args) {
		SpringApplication.run(JavaTechnicalInterviewApplication.class, args);
	}

	@PostConstruct
	public void batchProcess()
	{
		List<UUID> input = List.of( UUID.fromString( "a70cee93-140e-4451-8940-2da7e5a42f22" ),
		                            UUID.randomUUID(),
		                            UUID.fromString( "0c428cfb-34b5-4d57-8627-2f31a59f2abd" ) );
//		// display all names and scores with these ids
	}
}
