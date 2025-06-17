package sn.zone.bakcup_api.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import sn.zone.bakcup_api.data.entities.User;
import sn.zone.bakcup_api.data.enums.Role;
import sn.zone.bakcup_api.data.repositories.UserRepository;

@Configuration
public class StartupRunner {
    private final PasswordEncoder passwordEncoder;

    public StartupRunner(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            // Only save if collection is empty
            if (userRepository.count() == 0) {
                User me = new User("cherif", "cherifmbaye02@gmail.com", passwordEncoder.encode("123456"), Role.ADMIN);
                userRepository.save(me);
                System.out.println("------------>  Saved default user: " + me.getEmail());
            }

            if (userRepository.count() == 1) {
                User user = new User("Zayd ibn Harithah", "zayd@gmail.com", passwordEncoder.encode("password123"),
                        Role.USER);
                userRepository.save(user);
                System.out.println("------------>  Saved default user: " + user.getEmail());
            }
        };
    }
}
