package com.ilich.repository.interfaces;

import com.ilich.model.User;

public interface UserRepository {

    String addUser(User user);

    void removeUser(String userName);

    int getIdByUserName(String userName);

    String checkUser(String userName, String password);
}