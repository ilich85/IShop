package com.ilich.controllers.admin.image;

import com.ilich.services.interfaces.ImageService;
import com.ilich.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static com.ilich.controllers.WorkWithCookies.currentAdmin;


@Controller
@RequestMapping(value = "/add-product-image")
public class ProductImageController {

    private final ImageService imageService;
    private final ProductService productService;

    @Autowired
    public ProductImageController(ImageService imageService, ProductService productService) {
        this.imageService = imageService;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView imagePage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (currentAdmin(request.getCookies()).equals("")) {
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        modelAndView.addObject("size", request.getParameter("size"));
        modelAndView.addObject("product",
                productService.getProductById(Integer.valueOf(request.getParameter("productId"))));
        modelAndView.setViewName("admin/product_image");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addProductImage(@RequestParam("file") MultipartFile file,
                           HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        if (currentAdmin(request.getCookies()).equals("")) {
            return "redirect:/index";
        }
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File dir = new File("D:/pictures/product");
                String pathName = dir.getAbsolutePath() + File.separator + file.getOriginalFilename();
                File uploadedFile = new File(pathName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                imageService.addProductImage(imageService.addImage(pathName), productId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            redirectAttributes.addAttribute("size", "Empty file");
            redirectAttributes.addAttribute("productId", productId);
            return "redirect:/add-product-image";
        }
        redirectAttributes.addAttribute("idProduct", productId);
        return "redirect:/update-product";
    }
}