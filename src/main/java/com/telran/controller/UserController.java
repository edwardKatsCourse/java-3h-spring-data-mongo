package com.telran.controller;

import com.telran.entity.User;
import com.telran.entity.UserPhoneNumber;
import com.telran.repository.UserPhoneNumberRepository;
import com.telran.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPhoneNumberRepository userPhoneNumberRepository;

    @PostMapping("/users")
    public User save(@RequestBody User user) {
        //1. check if exists!! and then save if needed!
        //2. upsert (update + insert)

        User dbUser = userRepository.findByEmail(user.getEmail());
        if (dbUser == null) {
            if (user.getUserPhoneNumber() != null) {
                UserPhoneNumber userPhoneNumber = userPhoneNumberRepository.save(user.getUserPhoneNumber());
                user.setUserPhoneNumber(userPhoneNumber);

            }
            return userRepository.save(user);
        }

        throw new ResponseStatusException(HttpStatus.CONFLICT, "User with such email already exists");
    }

    @GetMapping("/users")
    public User findByEmail(@RequestParam("email") String email) {
        return userRepository.findByEmail(email);
    }

    @GetMapping("/users/{name}")
    public List<User> findAllByName(@PathVariable("name") String name) {
        return userRepository.findAllByName(name);
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable("id") String id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/users/search")
    public List<User> searchUsers(@RequestBody SearchDto searchDto) {
        return userRepository.searchUsers(searchDto);
    }
}
