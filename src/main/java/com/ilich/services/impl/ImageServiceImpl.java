package com.ilich.services.impl;

import com.ilich.repository.interfaces.ImageRepository;
import com.ilich.services.interfaces.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("imageService")
@Transactional(readOnly = true)
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public int addImage(String path) {
        return this.imageRepository.addImage(path);
    }

    @Override
    public void addProductImage(int imageId, int productId) {
        this.imageRepository.addProductImage(imageId, productId);
    }

    @Override
    public void addCategoryImage(int imageId, int categoryId) {
        this.imageRepository.addCategoryImage(imageId, categoryId);
    }

    @Override
    public String getImageById(int idImage) {
        return this.imageRepository.getImageById(idImage);
    }
}