package com.klweb.farmservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.klweb.farmservice.model.Coordinate;
import com.klweb.farmservice.model.Farm;
import com.klweb.farmservice.model.User;
import com.klweb.farmservice.repo.FarmRepository;
import com.klweb.farmservice.repo.UserRepository;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
    public CommandLineRunner mappingDemo(UserRepository userRepo,
                                         FarmRepository farmRepo) {
        return args -> {

        	// create a farm
        	User user = new User("sam", "active", "description");

            // save the farm
        	userRepo.save(user);

        	
            // create three farms
        	Coordinate area = new Coordinate("123", "456");
        	Farm farm1 = new Farm("plaze", "12h", "my farm", "active", area);

            // save farm
            farmRepo.save(farm1);

            // add farm to the user
            user.getFarms().add(farm1);

            // update the user
            userRepo.save(user);
            
        };
    }
}
