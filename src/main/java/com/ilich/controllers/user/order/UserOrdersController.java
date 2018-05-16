package com.ilich.controllers.user.order;

import com.ilich.services.interfaces.OrderService;
import com.ilich.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentUser;

@Controller
@RequestMapping(value = "/user-orders")
public class UserOrdersController {

    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public UserOrdersController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView userOrders(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String userName = currentUser(request.getCookies());
        modelAndView.addObject("orders", orderService
                .getUserOrders(userService.getIdByUserName(userName)));
        modelAndView.setViewName("public/user_orders");
        return modelAndView;
    }
}