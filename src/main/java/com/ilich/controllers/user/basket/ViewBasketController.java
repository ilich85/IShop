package com.ilich.controllers.user.basket;

import com.ilich.services.interfaces.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentUser;

@Controller
@RequestMapping(value = "/view-basket")
public class ViewBasketController {
    private final BasketService basketService;

    @Autowired
    public ViewBasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewBasket(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("basket",
                basketService.getBasket(currentUser(request.getCookies())));
        modelAndView.setViewName("public/user_basket");
        return modelAndView;
    }
}