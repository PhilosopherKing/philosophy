package com.example.philosophy;

import com.example.philosophy.models.data.wisdom.StorageProperties;
import com.example.philosophy.models.data.WisdomDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class PhilosophyApplication {

	public static void main(String[] args) {

		SpringApplication.run(PhilosophyApplication.class, args);
	}

	@Bean
	CommandLineRunner init(WisdomDao wisdomDao) {
		return (args) -> {
			wisdomDao.deleteAll();
			wisdomDao.init();
		};
	}
}
