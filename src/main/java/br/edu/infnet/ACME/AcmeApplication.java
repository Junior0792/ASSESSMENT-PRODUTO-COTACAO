package br.edu.infnet.ACME;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AcmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcmeApplication.class, args);
		
	}

}
