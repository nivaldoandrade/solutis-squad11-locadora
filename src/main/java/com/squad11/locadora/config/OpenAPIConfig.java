package com.squad11.locadora.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Squad 11");
        contact.setUrl("https://github.com/nivaldoandrade/solutis-squad11-locadora");


        Info info = new Info()
                .title("Solutis Squad 11 - Locadora")
                .version("1.0")
                .contact(contact);

        return new OpenAPI().info(info);
    }
}
