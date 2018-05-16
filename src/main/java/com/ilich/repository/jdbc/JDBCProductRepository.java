package com.ilich.repository.jdbc;

import com.ilich.repository.interfaces.CommentRepository;
import com.ilich.repository.interfaces.ProductRepository;
import com.ilich.model.Product;
import com.ilich.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("productRepository")
public class JDBCProductRepository implements ProductRepository {

    private String result;
    private Connection conn;

    private final DataSource dataSource;
    @Autowired
    private CommentService commentService;

    @Autowired
    public JDBCProductRepository(DataSource dataSource) {
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
    public int addProduct(Product product) {
        int productId = 0;
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO products (product_name, description, price, " +
                                "category, image_id, quantity) VALUES (?,?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, product.getProductName());
                ps.setString(2, product.getDescription());
                ps.setInt(3, product.getPrice());
                ps.setString(4, product.getCategoryName());
                ps.setInt(5, product.getImageId());
                ps.setInt(6, product.getQuantity());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    productId = rs.getInt(1);
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
        return productId;
    }


    @Override
    public String updateProduct(Product product) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE products SET product_name=?, category=?, description=?, " +
                                "price=?, quantity=? WHERE id_product=?");
                ps.setString(1, product.getProductName());
                ps.setString(2, product.getCategoryName());
                ps.setString(3, product.getDescription());
                ps.setInt(4, product.getPrice());
                ps.setInt(5, product.getQuantity());
                ps.setInt(6, product.getIdProduct());
                ps.executeUpdate();
                ps.close();
                result = "Product updated successfully";
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
    public String removeProduct(int idProduct) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM products WHERE id_product=?");
                ps.setInt(1, idProduct);
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
    public int getProductQuantity(int idProduct) {
        int quantity = 0;
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT quantity FROM products WHERE id_product=?");
                ps.setInt(1, idProduct);
                ps.execute();
                ResultSet rs = ps.getResultSet();

                while (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }

    @Override
    public void updateProductQuantity(int idProduct, int quantity) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE products SET quantity=? WHERE id_product=?");
                ps.setInt(1, quantity);
                ps.setInt(2, idProduct);
                ps.executeUpdate();
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
    }


    @Override
    public List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT id_product, product_name, price, image_id " +
                                "FROM products WHERE category=?");
                ps.setString(1, category);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                Product product;
                while (rs.next()) {
                    product = new Product();
                    product.setIdProduct(rs.getInt("id_product"));
                    product.setProductName(rs.getString("product_name"));
                    product.setPrice(rs.getInt("price"));
                    product.setImageId(rs.getInt("image_id"));
                    products.add(product);
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
        return products;
    }

    @Override
    public List<Product> getProductsBySearch(String query) {
        List<Product> products = new ArrayList<>();
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT id_product, product_name, price, image_id " +
                                "FROM products WHERE product_name LIKE ?");
                ps.setString(1, "%" + query + "%");
                ps.execute();
                ResultSet rs = ps.getResultSet();
                Product product;
                while (rs.next()) {
                    product = new Product();
                    product.setPrice(rs.getInt("price"));
                    product.setProductName(rs.getString("product_name"));
                    product.setIdProduct(rs.getInt("id_product"));
                    product.setImageId(rs.getInt("image_id"));
                    products.add(product);
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
        return products;
    }

    @Override
    public List<Product> getProductsList() {
        List<Product> products = new ArrayList<>();
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT id_product, product_name, price, image_id, quantity " +
                                "FROM products ORDER BY product_name");
                ps.execute();
                ResultSet rs = ps.getResultSet();
                Product product;
                while (rs.next()) {
                    product = new Product();
                    product.setIdProduct(rs.getInt("id_product"));
                    product.setProductName(rs.getString("product_name"));
                    product.setImageId(rs.getInt("image_id"));
                    product.setPrice(rs.getInt("price"));
                    product.setQuantity(rs.getInt("quantity"));
                    products.add(product);
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
        return products;
    }

    @Override
    public Product getProductNameAndPriceById(int id) {
        Product product = null;
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT product_name, price, image_id " +
                                "FROM products WHERE id_product=?");
                ps.setInt(1, id);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    product = new Product();
                    product.setIdProduct(id);
                    product.setProductName(rs.getString("product_name"));
                    product.setPrice(rs.getInt("price"));
                    product.setImageId(rs.getInt("image_id"));
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
        return product;
    }

    @Override
    public Product getProductById(int idProduct) {
        Product product = new Product();
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT product_name, price, description, image_id, quantity, category " +
                                "FROM products  WHERE id_product=?");
                ps.setInt(1, idProduct);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    product.setIdProduct(idProduct);
                    product.setProductName(rs.getString("product_name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getInt("price"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setImageId(rs.getInt("image_id"));
                    product.setCategoryName(rs.getString("category"));
                }
                ps.close();
                product.setComments(commentService.getCommentsListForProduct(idProduct));
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
        return product;
    }
}