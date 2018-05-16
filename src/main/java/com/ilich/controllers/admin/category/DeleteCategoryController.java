package com.ilich.controllers.admin.category;

import com.ilich.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentAdmin;

@Controller
@RequestMapping(value = "/delete-category")
public class DeleteCategoryController {

    private final CategoryService categoryService;

    @Autowired
    public DeleteCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        if (currentAdmin(request.getCookies()).equals("")) {
            return "redirect:/index";
        }
        return "redirect:/categories";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String deleteCategory(HttpServletRequest request,
                                 final RedirectAttributes redirectAttributes) {
        if (currentAdmin(request.getCookies()).equals("")) {
            return "redirect:/index";
        }
        redirectAttributes.addAttribute("result", categoryService.removeCategory(
                request.getParameter("categoryName")));
        return "redirect:/categories";
    }
}