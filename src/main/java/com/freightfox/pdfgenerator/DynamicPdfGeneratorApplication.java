package com.freightfox.pdfgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.freightfox.pdfgenerator") // Ensure Spring scans the correct package
public class DynamicPdfGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicPdfGeneratorApplication.class, args);
    }
}
