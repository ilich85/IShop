package com.ilich.controllers;

import com.ilich.services.interfaces.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Controller
@RequestMapping(value = "/load")
public class LoadImageController {

    private final ImageService imageService;

    @Autowired
    public LoadImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public void load(HttpServletRequest request, HttpServletResponse response) {
        String path = imageService.getImageById(
                Integer.parseInt(request.getParameter("id")));
        byte[] buffer = new byte[0];
        try (FileInputStream fin = new FileInputStream(path)) {
            buffer = new byte[fin.available()];
            fin.read(buffer, 0, fin.available());
        } catch (IOException e) {
           e.printStackTrace();
        }
        String contentType = request.getServletContext()
                .getMimeType(new File(path).getName());
        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Length", String.valueOf(buffer.length));
        try {
            response.getOutputStream().write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}