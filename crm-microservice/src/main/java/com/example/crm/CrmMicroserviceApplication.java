package com.example.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// -Duser.language=tr -Duser.country=TR
@SpringBootApplication
public class CrmMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmMicroserviceApplication.class, args);
	}

}
