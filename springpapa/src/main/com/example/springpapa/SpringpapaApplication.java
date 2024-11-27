package com.example.springpapa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springpapa.mapper")
public class SpringpapaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringpapaApplication.class, args);

    }

}
