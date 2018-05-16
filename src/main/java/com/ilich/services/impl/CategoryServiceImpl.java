package com.ilich.services.impl;

import com.ilich.model.Category;
import com.ilich.repository.interfaces.CategoryRepository;
import com.ilich.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryService")
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategoriesList() {
        return this.categoryRepository.getCategoriesList();
    }

    @Override
    public Category getCategoryById(int id) {
        return this.categoryRepository.getCategoryById(id);
    }


    @Override
    public String addCategory(String categoryName, int image) {
        return this.categoryRepository.addCategory(categoryName, image);
    }

    @Override
    public String updateCategory(String newName, int categoryId) {
        return this.categoryRepository.updateCategory(newName, categoryId);
    }

    @Override
    public String removeCategory(String categoryName) {
        return this.categoryRepository.removeCategory(categoryName);
    }
}