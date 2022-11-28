package it.advancia.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Tutorials API", version = "1.0"))
//http://localhost:8080/swagger-ui/index.html
public class SpringSwaggerCrudApplication {

	public static void main(java.lang.String[] args) {
		SpringApplication.run(SpringSwaggerCrudApplication.class, args);
	}

}
