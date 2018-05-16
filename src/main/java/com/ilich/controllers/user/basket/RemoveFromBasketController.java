package com.ilich.controllers.user.basket;

import com.ilich.services.interfaces.BasketService;
import com.ilich.services.interfaces.ProductService;
import com.ilich.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentUser;

@Controller
@RequestMapping(value = "/remove-from-basket")
public class RemoveFromBasketController {
    private final BasketService basketService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public RemoveFromBasketController(BasketService basketService,
                                      UserService userService, ProductService productService) {
        this.basketService = basketService;
        this.userService = userService;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String removeFromBasket(HttpServletRequest request) {
        int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        productService.updateProductQuantity(Integer.parseInt(request.getParameter("idProduct")),
                productService.getProductQuantity(idProduct)
                        + Integer.parseInt(request.getParameter("quantity")));
        basketService.removeFromBasket(idProduct,
                userService.getIdByUserName(currentUser(request.getCookies())));
        return "redirect:/view-basket";
    }
}