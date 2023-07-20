package com.ivanvolkov.imagerotatorapi;

import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCosmosRepositories
public class ImagerotatorapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImagerotatorapiApplication.class, args);
    }

}
