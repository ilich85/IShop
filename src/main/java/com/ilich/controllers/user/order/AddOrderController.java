package com.ilich.controllers.user.order;

import com.ilich.model.BasketDetails;
import com.ilich.model.Order;
import com.ilich.model.OrderDetails;
import com.ilich.services.interfaces.BasketService;
import com.ilich.services.interfaces.DeliveryAddressService;
import com.ilich.services.interfaces.OrderService;
import com.ilich.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.ilich.controllers.WorkWithCookies.currentUser;

@Controller
@RequestMapping(value = "/add-order")
public class AddOrderController {

    private final UserService userService;
    private final OrderService orderService;
    private final BasketService basketService;
    private final DeliveryAddressService deliveryAddressService;

    @Autowired
    public AddOrderController(UserService userService, OrderService orderService,
                              BasketService basketService, DeliveryAddressService deliveryAddressService) {
        this.userService = userService;
        this.orderService = orderService;
        this.basketService = basketService;
        this.deliveryAddressService = deliveryAddressService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addOrder(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        String userName = currentUser(request.getCookies());
        if (userName.equals("")) {
            index();
        }
        if (deliveryAddressService.getUserAddress(
                userService.getIdByUserName(userName)).getIdAddress() == 0) {
            redirectAttributes.addAttribute("result", "Чтобы сделать заказ, введите адрес доставки");
            return "redirect:/user-address";
        }
        List<BasketDetails> basketDetails = basketService.
                getBasket(userName).getDetails();
        Order order = new Order();
        order.setTotal(Integer.parseInt(request.getParameter("total")));
        order.setOrderDate(new SimpleDateFormat().format(new Date()));
        order.setUserId(userService.getIdByUserName(userName));
        long orderId = orderService.addOrder(order);
        OrderDetails orderDetails;
        for (BasketDetails bs : basketDetails) {
            orderDetails = new OrderDetails();
            orderDetails.setProduct(bs.getProduct());
            orderDetails.setQuantity(bs.getQuantity());
            orderDetails.setAmount(bs.getAmount());
            orderDetails.setOrderId(orderId);
            orderService.addOrderDetails(orderDetails);
        }
        basketService.clearBasket(userService.getIdByUserName(userName));
        return "redirect:/main-page";
    }
}