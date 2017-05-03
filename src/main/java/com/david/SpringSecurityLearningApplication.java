package com.david;

import com.david.model.Role;
import com.david.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.transaction.Transactional;

@SpringBootApplication
//@EntityScan(basePackages = {"com.david.model"})
//@EnableJpaRepositories(basePackages = {"com.david.repository"})
@ComponentScan(basePackages = "com.david")
public class SpringSecurityLearningApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityLearningApplication.class, args);
	}

	@Autowired
    private RoleRepository roleRepository;

	@Transactional
	@Override
	public void run(String... strings) throws Exception {
//        roleRepository.saveAndFlush(new Role("ROLE_ADMIN"));
        roleRepository.saveAndFlush(new Role("USER"));
    }
}
