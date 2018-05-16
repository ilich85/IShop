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
@RequestMapping(value = "/create-product")
public class CreateProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public CreateProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView productForm(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (currentAdmin(request.getCookies()).equals("")) {
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        modelAndView.addObject("newProduct", new Product());
        modelAndView.addObject("categories", categoryService.getCategoriesList());
        modelAndView.setViewName("admin/product_create");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createProduct(Product product, HttpServletRequest request,
                                final RedirectAttributes redirectAttributes) {
        if (currentAdmin(request.getCookies()).equals("")) {
            return "redirect:/index";
        }
        product.setCategoryName(request.getParameter("categoryName"));
        product.setImageId(1);
        int productId = productService.addProduct(product);
        if (productId == 0) {
            return "redirect:/create-product";
        }
        redirectAttributes.addAttribute("productId", productId);
        return "redirect:/add-product-image";
    }
}