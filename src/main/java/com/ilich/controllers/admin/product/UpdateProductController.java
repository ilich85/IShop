package com.ilich.controllers.admin.product;

import com.ilich.model.Product;
import com.ilich.services.interfaces.CategoryService;
import com.ilich.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.ilich.controllers.WorkWithCookies.currentAdmin;

@Controller
@RequestMapping(value = "/update-product")
public class UpdateProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public UpdateProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView updatePage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (currentAdmin(request.getCookies()).equals("")) {
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        modelAndView.addObject("product", productService
                .getProductById(Integer.parseInt(request.getParameter("idProduct"))));
        modelAndView.addObject("categories", categoryService.getCategoriesList());
        modelAndView.setViewName("admin/product_update");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateProduct(Product product, HttpServletRequest request,
                                final RedirectAttributes redirectAttributes) {
        if (currentAdmin(request.getCookies()).equals("")) {
            return "redirect:/index";
        }
        int idProduct = Integer.parseInt(request.getParameter("idProduct"));
        product.setProductName(request.getParameter("productName"));
        product.setCategoryName(request.getParameter("categoryName"));
        product.setDescription(request.getParameter("description"));
        product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        product.setPrice(Integer.parseInt(request.getParameter("price")));
        product.setIdProduct(idProduct);
        productService.updateProduct(product);
        redirectAttributes.addAttribute("idProduct", idProduct);
        return "redirect:/update-product";
    }
}