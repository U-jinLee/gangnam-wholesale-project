package com.gangnam.wholesale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GangnamWholesaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(GangnamWholesaleApplication.class, args);
    }

}
