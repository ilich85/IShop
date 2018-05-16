package com.ilich.services.interfaces;

import com.ilich.model.Category;

import java.util.List;

public interface CategoryService {

    String addCategory(String categoryName, int image);

    String removeCategory(String categoryName);

    String updateCategory(String newName, int categoryId);

    List<Category> getCategoriesList();

    Category getCategoryById(int id);
}