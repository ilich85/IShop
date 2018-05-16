package com.ilich.controllers.user.product;

import com.ilich.model.User;
import com.ilich.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentUser;

@Controller
@RequestMapping(value = "/list-products")
public class ProductsListController {

    private final ProductService productService;

    @Autowired
    public ProductsListController(ProductService productService) {
        this.productService = productService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView productsList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (currentUser(request.getCookies()).equals("")) {
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        modelAndView.addObject("result", request.getParameter("result"));
        modelAndView.addObject("productsList",
                productService.getProductsList());
        modelAndView.setViewName("public/products_list");
        return modelAndView;
    }
}