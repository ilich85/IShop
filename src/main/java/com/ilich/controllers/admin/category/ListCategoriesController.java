package com.ilich.controllers.admin.category;

import com.ilich.model.User;
import com.ilich.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentAdmin;

@Controller
@RequestMapping(value = "/categories")
public class ListCategoriesController {

    private final CategoryService categoryService;

    @Autowired
    public ListCategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView categoriesList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (currentAdmin(request.getCookies()).equals("")) {
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        modelAndView.addObject("result", request.getParameter("result"));
        modelAndView.addObject("categoriesList",
                categoryService.getCategoriesList());
        modelAndView.setViewName("admin/categories");
        return modelAndView;
    }
}