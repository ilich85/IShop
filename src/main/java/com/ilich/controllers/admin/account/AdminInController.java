package com.ilich.controllers.admin.account;

import com.ilich.model.Admin;
import com.ilich.services.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ilich.controllers.WorkWithCookies.currentAdmin;


@Controller
@RequestMapping(value = "/admin-in")
public class AdminInController {

    private final AdminService adminService;

    @Autowired
    public AdminInController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        if (currentAdmin(request.getCookies()).equals("")) {
            return "redirect:/index";
        }
        return "redirect:/admin-main";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String adminIn(HttpServletResponse response, Admin admin,
                          final RedirectAttributes redirectAttributes) {
        String result = "redirect:/admin-auth";
        String adminName = admin.getAdminName();
        String checkResult = adminService.checkAdmin(adminName, admin.getPassword());
        switch (checkResult) {
            case "complete":
                response.addCookie(new Cookie("currentAdmin", adminName));
                redirectAttributes.addAttribute("currentAdmin", adminName);
                result = "redirect:/admin-main";
                break;
            case "wrong":
                redirectAttributes.addAttribute("checkResult", "Неправильный пароль");
                break;
            case "not_exist":
                redirectAttributes.addAttribute("checkResult", "Такого админа не существует");
                break;
            default:
                redirectAttributes.addAttribute("checkResult", "Попробуйте позже");
                break;
        }
        return result;
    }
}