package com.airiot.fi;


import com.airiot.fi.model.entity.User;
import com.airiot.fi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        User root = new User();
        root.setUsername("root");
        root.setPassword(passwordEncoder.encode("root"));
        root.setRoles(Arrays.asList("ADMIN", "USER", "GUEST"));
        userRepository.save(root);

        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setRoles(Arrays.asList("USER", "GUEST"));
        userRepository.save(user);

        User guest = new User();
        guest.setUsername("guest");
        guest.setPassword(passwordEncoder.encode("guest"));
        guest.setRoles(Arrays.asList("GUEST"));
        userRepository.save(guest);
    }
}
