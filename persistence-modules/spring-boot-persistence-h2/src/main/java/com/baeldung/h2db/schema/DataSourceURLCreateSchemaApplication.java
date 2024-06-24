package com.baeldung.h2db.schema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-schema-url.yaml")
public class DataSourceURLCreateSchemaApplication {

    public static void main(String... args) {
        SpringApplication.run(DataSourceURLCreateSchemaApplication.class, args);
    }

}