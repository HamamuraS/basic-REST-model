package com.example.demo;

import com.example.demo.model.League;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class DemoConfig {

  @Bean
  public League league() {
    return new League();
  }

  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Demo API")
            .description("docs")
            .version("v0.0.1"));
  }

}
