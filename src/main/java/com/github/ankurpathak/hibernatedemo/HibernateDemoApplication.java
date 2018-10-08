package com.github.ankurpathak.hibernatedemo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class HibernateDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateDemoApplication.class, args);
    }
}


@Component
class Main implements ApplicationRunner {


    private final IUserRepository userRepository;

    Main(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User();
        user.setEmail("ankurpathak@live.in");
        user.setFirstName("ankur");
        user.setLastName("pathak");
        try {
            userRepository.save(user);
        } catch (DuplicateKeyException ex) {
            ex.printStackTrace();
        }
    }
}
