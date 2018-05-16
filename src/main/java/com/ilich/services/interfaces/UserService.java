package com.ilich.services.interfaces;

import com.ilich.model.User;

public interface UserService {

    String addUser(User user);

    void removeUser(String userName);

    int getIdByUserName(String userName);

    String checkUser(String userName, String password);
}