package com.ilich.controllers.admin.image;

import com.ilich.services.interfaces.CategoryService;
import com.ilich.services.interfaces.ImageService;
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
@RequestMapping(value = "/add-category-image")
public class CategoryImageController {

    private final ImageService imageService;
    private final CategoryService categoryService;

    @Autowired
    public CategoryImageController(ImageService imageService, CategoryService categoryService) {
        this.imageService = imageService;
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView imagePage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (currentAdmin(request.getCookies()).equals("")) {
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
        modelAndView.addObject("size", request.getParameter("size"));
        modelAndView.addObject("category",
                categoryService.getCategoryById(Integer.valueOf(request.getParameter("categoryId"))));
        modelAndView.setViewName("admin/category_image");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addCategoryImage(@RequestParam("file") MultipartFile file,
                           HttpServletRequest request, final RedirectAttributes redirectAttributes) {
        Integer categoryId = Integer.valueOf(request.getParameter("categoryId"));
        if (currentAdmin(request.getCookies()).equals("")) {
            return "redirect:/index";
        }
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File dir = new File("D:/pictures/category");
                String pathName = dir.getAbsolutePath() + File.separator + file.getOriginalFilename();
                File uploadedFile = new File(pathName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                imageService.addCategoryImage(imageService.addImage(pathName), categoryId);
            } catch (Exception e) {
             e.printStackTrace();
            }
        } else {
            redirectAttributes.addAttribute("size", "Empty file");
            redirectAttributes.addAttribute("categoryId", categoryId);
            return "redirect:/add-category-image";
        }
        redirectAttributes.addAttribute("idCategory", categoryId);
        return "redirect:/update-category";
    }
}