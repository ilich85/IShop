package com.ilich.repository.interfaces;

import com.ilich.model.Product;

import java.util.List;

public interface ProductRepository {

    int addProduct(Product product);

    String updateProduct(Product product);

    String removeProduct(int idProduct);

    List<Product> getProductsByCategory(String category);

    Product getProductNameAndPriceById(int id);

    Product getProductById(int id);

    List<Product> getProductsBySearch(String query);

    List<Product> getProductsList();

    int getProductQuantity(int idProduct);

    void updateProductQuantity(int idProduct, int quantity);
}