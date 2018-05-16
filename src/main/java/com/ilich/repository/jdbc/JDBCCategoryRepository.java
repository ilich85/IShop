package com.ilich.repository.jdbc;

import com.ilich.repository.interfaces.CategoryRepository;
import com.ilich.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("categoryRepository")
public class JDBCCategoryRepository implements CategoryRepository {

    private String result;
    private Connection conn;
    private final DataSource dataSource;

    @Autowired
    public JDBCCategoryRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private String getConnection() {
        try {
            conn = dataSource.getConnection();
            result = "Good connection";
        } catch (SQLException e) {
            result = "error";
        }
        return result;
    }

    @Override
    public List<Category> getCategoriesList() {
        List<Category> categories = new ArrayList<>();
        Category category;
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT id_category, category_name, image_id FROM categories");
                ps.execute();
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    category = new Category();
                    String categoryName = rs.getString("category_name");
                    category.setIdCategory(rs.getInt("id_category"));
                    category.setNameCategory(categoryName);
                    category.setImageId(rs.getInt("image_id"));
                    category.setCountOfProducts(getCountProductsByCategory(categoryName));
                    categories.add(category);
                }
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return categories;
    }


    private int getCountProductsByCategory(String nameCategory) {
        int count = 0;
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT product_name FROM products WHERE category=?");
                ps.setString(1, nameCategory);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    count++;
                }
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return count;
    }

    @Override
    public Category getCategoryById(int id) {
        Category category = new Category();
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT category_name, image_id " +
                                "FROM categories  WHERE id_category=?");
                ps.setInt(1, id);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    String categoryName = rs.getString("category_name");
                    category.setIdCategory(id);
                    category.setNameCategory(categoryName);
                    category.setImageId(rs.getInt("image_id"));
                    category.setCountOfProducts(getCountProductsByCategory(categoryName));
                }
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return category;
    }

    @Override
    public String addCategory(String categoryName, int image) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO categories (category_name, image_id) VALUES (?,?)");
                ps.setString(1, categoryName);
                ps.setInt(2, image);
                ps.executeUpdate();
                ps.close();
                result = "complete";
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String updateCategory(String newName, int categoryId) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE categories SET category_name=? WHERE id_category=?");
                ps.setString(1, newName);
                ps.setInt(2, categoryId);
                ps.executeUpdate();
                ps.close();
                result = "complete";
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String removeCategory(String categoryName) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM categories WHERE category_name=?");
                ps.setString(1, categoryName);
                ps.executeUpdate();
                ps.close();
                result = "complete";
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }
}