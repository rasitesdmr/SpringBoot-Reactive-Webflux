package com.rasitesdmr.springbootreactive.service;

import com.rasitesdmr.springbootreactive.model.User;
import com.rasitesdmr.springbootreactive.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser() {
        for (int i = 0; i <10000 ; i++) {
            String username = "rasit" + i;
            User newUser = new User(username);
            userRepository.save(newUser);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
