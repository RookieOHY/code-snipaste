package com.rookieohy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author 20413
 */
@SpringBootApplication
public class CodeApp {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(CodeApp.class);
        springApplication.run(args);
    }
}
