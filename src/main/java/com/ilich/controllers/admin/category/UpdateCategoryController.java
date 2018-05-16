package com.ilich.controllers.admin.category;

import com.ilich.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentAdmin;

@Controller
@RequestMapping(value = "/update-category")
public class UpdateCategoryController {

    private final CategoryService categoryService;

    @Autowired
    public UpdateCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (currentAdmin(request.getCookies()).equals("")) {
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        modelAndView.addObject("category", categoryService.
                getCategoryById(Integer.parseInt(request.getParameter("idCategory"))));
        modelAndView.setViewName("admin/category_update");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateCategory(HttpServletRequest request,
                                 final RedirectAttributes redirectAttributes) {
        if (currentAdmin(request.getCookies()).equals("")) {
            return "redirect:/index";
        }
        redirectAttributes.addAttribute("result", categoryService.updateCategory(
                request.getParameter("newName"), Integer.parseInt(
                        request.getParameter("categoryId"))));
        return "redirect:/categories";
    }
}