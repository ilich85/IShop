package com.ilich.controllers.user.account;

import com.ilich.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/user-sign-in")
public class SignInUserController {

    private final UserService userService;

    @Autowired
    public SignInUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String signIn(HttpServletRequest request, HttpServletResponse response,
                         final RedirectAttributes redirectAttributes) {
        String result = "redirect:/index";
        String userName = request.getParameter("userName");
        String checkResult = userService.checkUser(userName, request.getParameter("password"));
        switch (checkResult) {
            case "complete":
                response.addCookie(new Cookie("currentUser", userName));
                result = "redirect:/main-page";
                break;
            case "wrong":
                redirectAttributes.addAttribute("checkResult", "Неправильный пароль");
                break;
            case "not_exist":
                redirectAttributes.addAttribute("checkResult", "Такого пользователя не существует");
                break;
            default:
                redirectAttributes.addAttribute("checkResult", "Попробуйте позже");
                break;
        }
        return result;
    }
}