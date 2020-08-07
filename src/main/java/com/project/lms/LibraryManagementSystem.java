package com.project.lms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Slf4j
@SpringBootApplication(scanBasePackages = {"com.project"})
@ComponentScan(basePackages = {"com.project"})
@EntityScan({"com.project"})
@EnableJpaRepositories({"com.project"})
@EnableCaching
public class LibraryManagementSystem {
    public static void main(String[] args) {

        SpringApplication.run(LibraryManagementSystem.class, args);

    }
}
