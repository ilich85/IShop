package com.ilich.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentUser;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("checkResult", request.getParameter("checkResult"));
        modelAndView.addObject("createUserResult",
                request.getParameter("createUserResult"));
        modelAndView.setViewName("public/login");
        return modelAndView;
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contacts(HttpServletRequest request) {
        if (currentUser(request.getCookies()).equals("")) {
            return "public/login";
        }
        return "public/contacts";
    }

    @RequestMapping(value = "/user-profile", method = RequestMethod.GET)
    public String userProfile() {
        return "public/user_profile";
    }


    @RequestMapping(value = "/main-page", method = RequestMethod.GET)
    public String mainPage(HttpServletRequest request) {
        if (currentUser(request.getCookies()).equals("")) {
            return "public/login";
        }
        return "public/main_page";
    }
}