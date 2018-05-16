package com.ilich.controllers.user.address;

import com.ilich.model.DeliveryAddress;
import com.ilich.services.interfaces.DeliveryAddressService;
import com.ilich.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentUser;

@Controller
public class UserDeliveryAddressController {

    private final DeliveryAddressService deliveryAddressService;
    private final UserService userService;

    @Autowired
    public UserDeliveryAddressController(DeliveryAddressService deliveryAddressService, UserService userService) {
        this.deliveryAddressService = deliveryAddressService;
        this.userService = userService;
    }

    @RequestMapping(value = "/user-address", method = RequestMethod.GET)
    public ModelAndView userAddress(HttpServletRequest request) {
        String currentUser = currentUser(request.getCookies());
        ModelAndView modelAndView = new ModelAndView();
        if (currentUser.equals("")) {
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        modelAndView.addObject("userAddress",
                deliveryAddressService.getUserAddress(userService.getIdByUserName(currentUser)));
        modelAndView.addObject("newAddress", new DeliveryAddress());
        modelAndView.setViewName("public/user_address");
        return modelAndView;
    }
}