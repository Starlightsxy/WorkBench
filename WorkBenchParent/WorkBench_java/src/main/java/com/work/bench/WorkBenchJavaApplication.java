package com.work.bench;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.work.bench.mapper")
@SpringBootApplication
public class WorkBenchJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkBenchJavaApplication.class, args);

    }

}
