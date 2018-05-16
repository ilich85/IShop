package com.ilich.controllers.user.address;

import com.ilich.model.DeliveryAddress;
import com.ilich.services.interfaces.DeliveryAddressService;
import com.ilich.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentUser;

@Controller
@RequestMapping(value = "/set-delivery-address")
public class AddDeliveryAddressController {

    private final DeliveryAddressService deliveryAddressService;
    private final UserService userService;

    @Autowired
    public AddDeliveryAddressController(DeliveryAddressService deliveryAddressService, UserService userService) {
        this.deliveryAddressService = deliveryAddressService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String setAddress(DeliveryAddress deliveryAddress, HttpServletRequest request) {
        String currentUser = currentUser(request.getCookies());
        if (currentUser.equals("")) {
            index();
        }
        deliveryAddress.setUserId(userService.getIdByUserName(currentUser));
        deliveryAddressService.setAddress(deliveryAddress);
        return "redirect:/user-address";
    }
}