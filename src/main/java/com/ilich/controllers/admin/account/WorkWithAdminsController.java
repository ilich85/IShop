package com.ilich.controllers.admin.account;

import com.ilich.model.Admin;
import com.ilich.services.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentAdmin;

@Controller
@RequestMapping(value = "/work-with-admins")
public class WorkWithAdminsController {

    private final AdminService adminService;

    @Autowired
    public WorkWithAdminsController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView workWithAdmins(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (!currentAdmin(request.getCookies()).equals("superadmin")) {
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        modelAndView.addObject("adminsList", adminService.getAdminsList());
        modelAndView.addObject("newAdmin", new Admin());
        modelAndView.addObject("result", request.getParameter("result"));
        modelAndView.setViewName("admin/admins");
        return modelAndView;
    }
}