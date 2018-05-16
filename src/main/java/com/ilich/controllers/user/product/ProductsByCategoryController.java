package com.ilich.controllers.user.product;

import com.ilich.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentUser;

@Controller
@RequestMapping(value = "/products-by-category")
public class ProductsByCategoryController {

    private final ProductService productService;

    @Autowired
    public ProductsByCategoryController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView productsByCategory(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (currentUser(request.getCookies()).equals("")) {
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        modelAndView.addObject("productList", productService.getProductsByCategory(
                request.getParameter("categoryName")));
        modelAndView.setViewName("public/products_by_category");
        return modelAndView;
    }
}