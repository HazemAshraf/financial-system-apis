package com.bank.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
