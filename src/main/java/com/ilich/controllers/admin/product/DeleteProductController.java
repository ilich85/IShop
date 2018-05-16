package com.ilich.controllers.admin.product;

import com.ilich.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentAdmin;

@Controller
@RequestMapping(value = "/delete-product")
public class DeleteProductController {

    private final ProductService productService;

    @Autowired
    public DeleteProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        if (currentAdmin(request.getCookies()).equals("")) {
            return "redirect:/index";
        }
        return "redirect:/products";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String deleteProduct(HttpServletRequest request,
                                final RedirectAttributes redirectAttributes) {
        if (currentAdmin(request.getCookies()).equals("")) {
            return "redirect:/index";
        }
        int idProduct = Integer.parseInt(request.getParameter("productId"));
        redirectAttributes.addAttribute("result", productService.removeProduct(idProduct));
        return "redirect:/products";
    }
}