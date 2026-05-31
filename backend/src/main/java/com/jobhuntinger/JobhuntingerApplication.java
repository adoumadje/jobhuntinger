package com.jobhuntinger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JobhuntingerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobhuntingerApplication.class, args);
	}

}
