package com.t3.visitoraccess;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.t3.visitoraccess.entity.User;
import com.t3.visitoraccess.repository.UserRepository;

@SpringBootApplication
public class VisitorAccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitorAccessApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder encoder){
		return args -> {
			if(userRepository.findByUsername("user").isEmpty()){
				userRepository.save(new User("user", encoder.encode("senha"), "ROLE_USER"));
			}

			if(userRepository.findByUsername("admin").isEmpty()){
				userRepository.save(new User("admin", encoder.encode("admin"), "ROLE_USER,ROLE_ADMIN"));
			}
		};
	}

	
}
