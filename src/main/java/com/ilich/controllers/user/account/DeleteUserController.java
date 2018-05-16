package com.ilich.controllers.user.account;

import com.ilich.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentUser;

@Controller
@RequestMapping(value = "/user-delete")
public class DeleteUserController {

    private final UserService userService;

    @Autowired
    public DeleteUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String deleteUser(HttpServletRequest request) {
        userService.removeUser(currentUser(request.getCookies()));
        return "redirect:/index";
    }
}