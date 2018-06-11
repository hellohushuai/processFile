package com.iflytek.processfile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="com.iflytek.processfile")
public class ProcessfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessfileApplication.class, args);
    }
}
