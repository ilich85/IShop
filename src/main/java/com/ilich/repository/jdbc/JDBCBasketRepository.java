package com.ilich.repository.jdbc;

import com.ilich.repository.interfaces.BasketRepository;
import com.ilich.model.Basket;
import com.ilich.model.BasketDetails;
import com.ilich.model.Product;
import com.ilich.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("basketRepository")
public class JDBCBasketRepository implements BasketRepository {

    private Connection conn;
    private final DataSource dataSource;
    @Autowired
    private ProductService productService;

    @Autowired
    public JDBCBasketRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private String getConnection() {
        String result;
        try {
            conn = dataSource.getConnection();
            result = "Good connection";
        } catch (SQLException e1) {
            result = "error";
        }
        return result;
    }

    @Override
    public Basket getBasket(String userName) {
        Basket basket = new Basket();
        List<BasketDetails> details = new ArrayList<>();
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT bd.id_basket_details, bd.quantity_reserved, bd.amount, " +
                                "p.id_product, p.product_name, p.price, p.quantity, p.image_id " +
                                "FROM basket_details bd INNER JOIN users u " +
                                "ON bd.user_id=u.id_user INNER JOIN products p " +
                                "WHERE bd.product_id=p.id_product " +
                                "AND u.user_name=?");
                ps.setString(1, userName);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                BasketDetails bd;
                Product product;
                int total = 0;
                while (rs.next()) {
                    bd = new BasketDetails();
                    product = new Product();
                    product.setIdProduct(rs.getInt("id_product"));
                    product.setProductName(rs.getString("product_name"));
                    product.setPrice(rs.getInt("price"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setImageId(rs.getInt("image_id"));
                    bd.setProduct(product);
                    bd.setIdBasketDetails(rs.getInt("id_basket_details"));
                    bd.setAmount(rs.getInt("amount"));
                    bd.setQuantity(rs.getInt("quantity_reserved"));
                    total += rs.getInt("amount");
                    details.add(bd);
                }
                basket.setDetails(details);
                basket.setTotal(total);
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
        return basket;
    }

    @Override
    public void addToBasket(BasketDetails basketDetails) {
        Product product = basketDetails.getProduct();
        int userId = basketDetails.getUserId();
        int quantity = basketDetails.getQuantity();
        BasketDetails bd = basketContainsProduct(product, userId);
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps;
                if (bd == null) {
                    ps = conn.prepareStatement(
                            "INSERT INTO basket_details (product_id, user_id, quantity_reserved, amount) " +
                                    "VALUES (?,?,?,?)");
                    ps.setLong(1, product.getIdProduct());
                    ps.setLong(2, userId);
                    ps.setInt(3, quantity);
                    ps.setLong(4, product.getPrice() * quantity);
                    ps.executeUpdate();
                    ps.close();
                } else {
                    ps = conn.prepareStatement(
                            "UPDATE basket_details SET quantity_reserved=?, amount=? " +
                                    "WHERE id_basket_details=?;");
                    ps.setInt(1, bd.getQuantity() + quantity);
                    ps.setInt(2, product.getPrice() * quantity + bd.getAmount());
                    ps.setLong(3, bd.getIdBasketDetails());
                    ps.executeUpdate();
                    ps.close();
                }
                int productQuantity = productService.getProductQuantity(product.getIdProduct());
                productService.updateProductQuantity(product.getIdProduct(),
                        productQuantity - quantity);
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
    public void removeFromBasket(int idProduct, int idUser) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM basket_details " +
                                "WHERE product_id=? AND user_id=?;");
                ps.setInt(1, idProduct);
                ps.setInt(2, idUser);
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
    public void updateQuantity(BasketDetails bd) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE basket_details SET quantity_reserved=?, amount=? " +
                                "WHERE id_basket_details=?;");
                ps.setInt(1, bd.getQuantity());
                ps.setInt(2, bd.getProduct().getPrice() * bd.getQuantity());
                ps.setLong(3, bd.getIdBasketDetails());
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
    public int getQuantityInBasketDetail(long idBasketDetail) {
        int quantity = 0;
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT quantity_reserved FROM basket_details WHERE id_basket_details=?");
                ps.setLong(1, idBasketDetail);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    quantity = rs.getInt("quantity_reserved");
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

    private BasketDetails basketContainsProduct(Product product, int userId) {
        BasketDetails basketDetails = null;
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT * FROM basket_details WHERE user_id=? " +
                                "AND product_id=?");
                ps.setLong(1, userId);
                ps.setLong(2, product.getIdProduct());
                ps.execute();
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    basketDetails = new BasketDetails();
                    basketDetails.setIdBasketDetails(rs.getLong("id_basket_details"));
                    basketDetails.setProduct(product);
                    basketDetails.setAmount(rs.getInt("amount"));
                    basketDetails.setQuantity(rs.getInt("quantity_reserved"));
                    basketDetails.setUserId(rs.getInt("user_id"));
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
        return basketDetails;
    }

    @Override
    public void clearBasket(int userId) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM basket_details WHERE user_id=?;");
                ps.setLong(1, userId);
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
}