package com.ilich.controllers.admin.product;

import com.ilich.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentAdmin;

@Controller
@RequestMapping(value = "/products")
public class ListProductsController {

    private final ProductService productService;

    @Autowired
    public ListProductsController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView productsList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (currentAdmin(request.getCookies()).equals("")) {
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        modelAndView.addObject("products", productService.getProductsList());
        modelAndView.setViewName("admin/products");
        return modelAndView;
    }
}