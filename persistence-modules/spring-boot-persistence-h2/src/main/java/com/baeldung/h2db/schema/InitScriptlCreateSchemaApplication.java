package com.baeldung.h2db.schema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-schema-script.yaml")
public class InitScriptlCreateSchemaApplication {

    public static void main(String... args) {
        SpringApplication.run(InitScriptlCreateSchemaApplication.class, args);
    }

}