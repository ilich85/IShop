package com.ilich.controllers.user.basket;

import com.ilich.model.BasketDetails;
import com.ilich.services.interfaces.BasketService;
import com.ilich.services.interfaces.ProductService;
import com.ilich.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentUser;

@Controller
@RequestMapping(value = "/add-to-basket")
public class AddToBasketController {

    private final ProductService productService;
    private final UserService userService;
    private final BasketService basketService;

    @Autowired
    public AddToBasketController(ProductService productService, UserService userService, BasketService basketService) {
        this.productService = productService;
        this.userService = userService;
        this.basketService = basketService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToBasket(HttpServletRequest request,
                              final RedirectAttributes redirectAttributes) {
        BasketDetails bd = new BasketDetails();
        int productId = Integer.parseInt(request.getParameter("idProduct"));
        bd.setProduct(productService.getProductNameAndPriceById(productId));
        bd.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        bd.setUserId(userService.getIdByUserName(currentUser(request.getCookies())));
         basketService.addToBasket(bd);
        redirectAttributes.addAttribute("idProduct", productId);
        return "redirect:/product-in-detail";
    }
}