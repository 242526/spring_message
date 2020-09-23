package com.nd.spring_message;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value="com.nd.spring_message.mapper")
@SpringBootApplication
public class SpringMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMessageApplication.class, args);
    }

}
