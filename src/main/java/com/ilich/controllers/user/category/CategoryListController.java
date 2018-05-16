package com.ilich.controllers.user.category;

import com.ilich.model.User;
import com.ilich.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentUser;

@Controller
@RequestMapping(value = "/list-categories")
public class CategoryListController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryListController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView categoriesList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (currentUser(request.getCookies()).equals("")) {
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        modelAndView.addObject("categoriesList",
                categoryService.getCategoriesList());
        modelAndView.setViewName("public/categories_of_products");
        return modelAndView;
    }
}