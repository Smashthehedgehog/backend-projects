package com.example.user_registration_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        if (!(isValid(user.getPassword()))) {
            return ResponseEntity.badRequest().body("Invalid Password"); // The Password validation should be changed
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode the user's password before saving
        userRepository.save(user);  // Save the user to the database
        return ResponseEntity.ok("User registered successfully");
    }

    private boolean isValid(String password) {
        if (password == null || password.length() < 8) {
            return false; // Password must be at least 8 characters long
        }
        if (password.contains(" ")) {
            return false; // Password cannot contain spaces
        }

        // Checks for numbers
        int count = 0;
        // check digits from 0 to 9
        for (int i = 0; i <= 9; i++) {

            // to convert int to string
            String str1 = Integer.toString(i);

            if (password.contains(str1)) {
                count = 1;
                break;
            }
        }
        if (count == 0) {
            return false;
        }

        // Checks for special characters
        if (!(password.contains("@") || password.contains("#")
                || password.contains("!") || password.contains("~")
                || password.contains("$") || password.contains("%")
                || password.contains("^") || password.contains("&")
                || password.contains("*") || password.contains("(")
                || password.contains(")") || password.contains("-")
                || password.contains("+") || password.contains("/")
                || password.contains(":") || password.contains(".")
                || password.contains(", ") || password.contains("<")
                || password.contains(">") || password.contains("?")
                || password.contains("|"))) {
            return false;
        }

        int count1 = 0;

        // Checks for capital letters
        for (int i = 65; i <= 90; i++) {

            // type casting
            char c = (char)i;

            String str1 = Character.toString(c);
            if (password.contains(str1)) {
                count1 = 1;
                break;
            }
        }
        if (count1 == 0) {
            return false;
        }

        // Checks for lowercase letters
        int count2 = 0;

        // checking small letters
        for (int i = 97; i <= 122; i++) {

            // type casting
            char c = (char)i;
            String str1 = Character.toString(c);

            if (password.contains(str1)) {
                count2 = 1;
                break;
            }
        }
        if (count2 == 0) {
            return false;
        }


        return true;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll(); // Get all the users from the database
    }
}
