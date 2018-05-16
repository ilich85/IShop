package com.ilich.controllers.user.account;

import com.ilich.model.User;

public class ValidateNewUserController {

    public static String validate(User user) {
        if (user.getUserName().length() < 5 || user.getUserName().length() > 25) {
            return "username";
        } else if (user.getPassword().length() < 5 || user.getPassword().length() > 25) {
            return "password";
        } else if (!user.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            return "mail";
        }
        return "";
    }
}