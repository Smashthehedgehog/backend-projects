package com.example.user_registration_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override // override loadUserByUsername function from UserDetailsService
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user by username from the database
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("The username is not in the database");
        }
        // Build UserDetails object
        UserBuilder builder = withUsername(username);
        builder.password(user.getPassword());
        builder.roles("USER");
        return builder.build();

    }
}
