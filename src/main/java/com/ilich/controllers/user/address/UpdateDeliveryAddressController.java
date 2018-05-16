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
@RequestMapping(value = "/update-delivery-address")
public class UpdateDeliveryAddressController {

    private final DeliveryAddressService deliveryAddressService;
    private final UserService userService;

    @Autowired
    public UpdateDeliveryAddressController(DeliveryAddressService deliveryAddressService, UserService userService) {
        this.deliveryAddressService = deliveryAddressService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateAddress(HttpServletRequest request) {
        String currentUser = currentUser(request.getCookies());
        if (currentUser.equals("")) {
            index();
        }
        DeliveryAddress da = new DeliveryAddress();
        da.setUserId(userService.getIdByUserName(currentUser));
        da.setFirstName(request.getParameter("firstName"));
        da.setLastName(request.getParameter("lastName"));
        da.setCountry(request.getParameter("country"));
        da.setCity(request.getParameter("city"));
        da.setStreet(request.getParameter("street"));
        da.setHouse(Integer.parseInt(request.getParameter("house")));
        da.setFlat(Integer.parseInt(request.getParameter("flat")));
        deliveryAddressService.updateAddress(da);
        return "redirect:/user-address";
    }
}