package ru.edu.penzgtu.lab;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@OpenAPIDefinition(
	info = @Info(
			title = "PenzGTU Java Lab API",
			description = "API fro labs", version = "1.0.0",
			contact = @Contact(
					name = "Student PenzGTU, Kolyuchkin Anton",
					email = "antosha.kolychkin@gmail.com"
			)
	)
)

@SpringBootApplication
@EnableWebMvc
public class LabApplication {
	public static void main(String[] args) {
		SpringApplication.run(LabApplication.class, args);
	}
}
