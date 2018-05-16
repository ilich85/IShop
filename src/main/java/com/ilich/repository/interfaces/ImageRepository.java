package com.ilich.repository.interfaces;

public interface ImageRepository {

    void addProductImage(int imageId, int productId);

    int addImage(String path);

    void addCategoryImage(int imageId, int categoryId);

    String getImageById(int id);
}