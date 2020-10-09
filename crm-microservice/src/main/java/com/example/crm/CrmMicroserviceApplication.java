package com.example.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

// -Duser.language=tr -Duser.country=TR
@SpringBootApplication
@EnableMongoRepositories
public class CrmMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmMicroserviceApplication.class, args);
	}

}
