package com.ilich.controllers.admin.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/admin-exit")
public class AdminOutController {

    @RequestMapping(method = RequestMethod.POST)
    public String exit(HttpServletResponse response) {
        response.addCookie(new Cookie("currentAdmin", ""));
        return "redirect:/admin-auth";
    }
}