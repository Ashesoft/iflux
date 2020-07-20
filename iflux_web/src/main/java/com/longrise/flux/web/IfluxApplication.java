package com.longrise.flux.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.longrise.flux"})
public class IfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(IfluxApplication.class, args);
    }

}
