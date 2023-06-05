package com.rasitesdmr.springbootreactive.service;

import com.rasitesdmr.springbootreactive.model.User;

import java.util.List;

public interface UserService {

    void createUser ();

    List<User> getAllUsers();
}
