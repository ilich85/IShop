package com.ilich.controllers.admin.account;

import com.ilich.model.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin-auth")
public class AdminAuthController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView adminAuth(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("admin", new Admin());
        modelAndView.addObject("checkResult", request.getParameter("checkResult"));
        modelAndView.setViewName("admin/admin_auth");
        return modelAndView;
    }
}