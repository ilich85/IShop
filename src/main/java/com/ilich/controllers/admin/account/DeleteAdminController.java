package com.ilich.controllers.admin.account;

import com.ilich.services.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentAdmin;

@Controller
@RequestMapping(value = "/admin-remove")
public class DeleteAdminController {

    private final AdminService adminService;

    @Autowired
    public DeleteAdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        if (currentAdmin(request.getCookies()).equals("")) {
            return "redirect:/index";
        } else if (!currentAdmin(request.getCookies()).equals("superadmin")) {
            return "redirect:/admin-main";
        }
        return "redirect:/admin-main";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String adminRemove(HttpServletRequest request,
                              final RedirectAttributes redirectAttributes) {
        if (!currentAdmin(request.getCookies()).equals("superadmin")) {
            return "public/login";
        }
        redirectAttributes.addAttribute("result",
                adminService.removeAdmin(request.getParameter("adminName")));
        return "redirect:/work-with-admins";
    }
}