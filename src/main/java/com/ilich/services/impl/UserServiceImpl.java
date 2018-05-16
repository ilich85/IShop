package com.ilich.services.impl;

import com.ilich.model.User;
import com.ilich.repository.interfaces.UserRepository;
import com.ilich.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String addUser(User user) {
        return this.userRepository.addUser(user);
    }

    @Override
    public void removeUser(String userName) {
        this.userRepository.removeUser(userName);
    }

    @Override
    public int getIdByUserName(String userName) {
        return this.userRepository.getIdByUserName(userName);
    }

    @Override
    public String checkUser(String userName, String password) {
        return this.userRepository.checkUser(userName, password);
    }
}