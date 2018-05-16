package com.ilich.controllers.user.basket;

import com.ilich.model.BasketDetails;
import com.ilich.model.Product;
import com.ilich.services.interfaces.BasketService;
import com.ilich.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/update-quantity")
public class UpdateProductQuantityInBasketController {

    private final BasketService basketService;
    private final ProductService productService;

    @Autowired
    public UpdateProductQuantityInBasketController(BasketService basketService, ProductService productService) {
        this.basketService = basketService;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateQuantity(HttpServletRequest request) {
        BasketDetails basketDetails = new BasketDetails();
        int productId = Integer.parseInt(request.getParameter("idProduct"));
        Product product = productService.getProductById(productId);
        long idBasketDetails = Long.parseLong(request.getParameter("basketDetails"));
        int productQuantity = productService.getProductQuantity(productId);
        int oldQuantity = basketService.getQuantityInBasketDetail(idBasketDetails);
        int newQuantity = Integer.parseInt(request.getParameter("quantity"));
        int availableQuantity = productQuantity + oldQuantity - newQuantity;
        if (availableQuantity >= 0) {
            productService.updateProductQuantity(productId, availableQuantity);
            basketDetails.setIdBasketDetails(idBasketDetails);
            basketDetails.setProduct(product);
            basketDetails.setQuantity(newQuantity);
            basketService.updateQuantity(basketDetails);
            return "redirect:/view-basket";
        } else {
            return "redirect:/view-basket";
        }
    }
}