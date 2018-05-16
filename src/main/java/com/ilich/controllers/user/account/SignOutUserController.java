package com.ilich.controllers.user.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/user-exit")
public class SignOutUserController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String exit(HttpServletResponse response) {
        response.addCookie(new Cookie("currentUser", ""));
        return "redirect:/index";
    }
}