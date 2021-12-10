package com.t09.jibao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.t09.jibao.filters")
public class JibaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JibaoApplication.class, args);
    }

}
