package com.ilich.controllers.user.product;

import com.ilich.model.Product;
import com.ilich.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentUser;

@Controller
@RequestMapping(value = "/product-in-detail")
public class ProductInDetailController {

    private final ProductService productService;

    @Autowired
    public ProductInDetailController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView productInDetail(HttpServletRequest request) {
        Product product = productService.getProductById(
                Integer.valueOf(request.getParameter("idProduct")));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productInfo", product);
        modelAndView.addObject("currentUser", currentUser(request.getCookies()));
        modelAndView.setViewName("public/product_in_detail");
        return modelAndView;
    }
}