package com.ilich.repository.interfaces;

import com.ilich.model.Category;

import java.util.List;

public interface CategoryRepository {

    String addCategory(String categoryName, int image);

    String removeCategory(String categoryName);

    String updateCategory(String newName, int categoryId);

    List<Category> getCategoriesList();

    Category getCategoryById(int id);
}