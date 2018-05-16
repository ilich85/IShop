package com.ilich.controllers;

import javax.servlet.http.Cookie;

public class WorkWithCookies {

    public static String currentUser(Cookie[] cookies) {
        String userName = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("currentUser")) {
                userName = cookie.getValue();
            }
        }
        return userName;
    }

    public static String currentAdmin(Cookie[] cookies) {
        String adminName = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("currentAdmin")) {
                adminName = cookie.getValue();
            }
        }
        return adminName;
    }
}