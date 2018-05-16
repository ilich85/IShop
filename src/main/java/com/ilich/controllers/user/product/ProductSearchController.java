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
@RequestMapping(value = "/search")
public class ProductSearchController {

    private final ProductService productService;

    @Autowired
    public ProductSearchController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView productSearch(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (currentUser(request.getCookies()).equals("")) {
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        modelAndView.addObject("searchResult",
                productService.getProductsBySearch(request.getParameter("product-search")));
        modelAndView.setViewName("public/search_page");
        return modelAndView;
    }
}