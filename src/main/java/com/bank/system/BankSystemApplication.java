package com.bank.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class BankSystemApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(BankSystemApplication.class, args);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
