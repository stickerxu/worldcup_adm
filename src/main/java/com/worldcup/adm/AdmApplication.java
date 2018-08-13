package com.worldcup.adm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AdmApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdmApplication.class, args);
    }
}
