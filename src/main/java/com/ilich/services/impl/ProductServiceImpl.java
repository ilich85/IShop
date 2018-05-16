package com.ilich.services.impl;

import com.ilich.model.Product;
import com.ilich.repository.interfaces.ProductRepository;
import com.ilich.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public int addProduct(Product product) {
        return this.productRepository.addProduct(product);
    }

    @Override
    public String updateProduct(Product product) {
        return this.productRepository.updateProduct(product);
    }

    @Override
    public String removeProduct(int idProduct) {
        return this.productRepository.removeProduct(idProduct);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return this.productRepository.getProductsByCategory(category);
    }

    @Override
    public List<Product> getProductsBySearch(String query) {
        return this.productRepository.getProductsBySearch(query);
    }

    @Override
    public List<Product> getProductsList() {
        return this.productRepository.getProductsList();
    }

    @Override
    public int getProductQuantity(int idProduct) {
        return this.productRepository.getProductQuantity(idProduct);
    }

    @Override
    public Product getProductNameAndPriceById(int id) {
        return this.productRepository.getProductNameAndPriceById(id);
    }

    @Override
    public Product getProductById(int idProduct) {
        return this.productRepository.getProductById(idProduct);
    }

    @Override
    public void updateProductQuantity(int idProduct, int quantity) {
        productRepository.updateProductQuantity(idProduct, quantity);
    }
}