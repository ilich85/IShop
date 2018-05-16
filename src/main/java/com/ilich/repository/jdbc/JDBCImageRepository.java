package com.ilich.repository.jdbc;

import com.ilich.repository.interfaces.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository("imageRepository")
public class JDBCImageRepository implements ImageRepository {

    private String result;
    private Connection conn;

    private final DataSource dataSource;

    @Autowired
    public JDBCImageRepository(DataSource dataSource) {
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
    public int addImage(String path) {
        int idImage = 0;
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO images (image_url) VALUES (?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, path);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    idImage = rs.getInt(1);
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
        return idImage;
    }

    @Override
    public void addProductImage(int imageId, int productId) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE products SET image_id=? WHERE id_product=?");
                ps.setInt(1, imageId);
                ps.setInt(2, productId);
                ps.executeUpdate();
                ps.close();
                result = "Product image added successfully";
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
    }

    @Override
    public void addCategoryImage(int imageId, int categoryId) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE categories SET image_id=? WHERE id_category=?");
                ps.setInt(1, imageId);
                ps.setInt(2, categoryId);
                ps.executeUpdate();
                ps.close();
                result = "Category image added succesfully";
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
    }

    @Override
    public String getImageById(int idImage) {
        String image = null;
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT image_url FROM images WHERE id_image=?");
                ps.setInt(1, idImage);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    image = rs.getString("image_url");
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
        return image;
    }
}