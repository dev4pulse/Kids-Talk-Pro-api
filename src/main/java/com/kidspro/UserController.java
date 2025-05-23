package com.kidspro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addUser")
    public ResponseEntity<String> addEntry(@RequestBody User user) {
        try {
            if (userRepository.save(user).getId() > 0) {
                return new ResponseEntity<>("added to users", HttpStatus.OK);
            }
            return new ResponseEntity<>("failed exception", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            System.out.println("e" + e);
            return new ResponseEntity<>("failed exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/validUser")
    public ResponseEntity<Boolean> userExists(@RequestBody User user) {
        try {
            Optional<User> byUserNameAndPassword = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if (byUserNameAndPassword.isPresent()) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else{
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println("e" + e);
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
