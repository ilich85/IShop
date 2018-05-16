package com.ilich.services.interfaces;

public interface ImageService {

    void addProductImage(int imageId, int productId);

    int addImage(String path);

    void addCategoryImage(int imageId, int categoryId);

    String getImageById(int id);
}