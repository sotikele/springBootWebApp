package com.example.check;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories({"com.example.check.repositories", "com.eurodyn.qlack.fuse.lexicon.repository"})
@EntityScan({"com.example.check.models", "com.eurodyn.qlack.fuse.lexicon.model"})
@ComponentScan(basePackages = {"com.example.check.**", "com.eurodyn.qlack.fuse.lexicon"})
public class CheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckApplication.class, args);
    }



}
