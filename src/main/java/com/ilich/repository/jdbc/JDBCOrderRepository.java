package com.ilich.repository.jdbc;

import com.ilich.repository.interfaces.OrderRepository;
import com.ilich.repository.interfaces.ProductRepository;
import com.ilich.model.Order;
import com.ilich.model.OrderDetails;
import com.ilich.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("orderRepository")
public class JDBCOrderRepository implements OrderRepository {

    private Connection conn;
    private final DataSource dataSource;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    public JDBCOrderRepository(DataSource dataSource) {
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
    public long addOrder(Order order) {
        long idOrder = 0L;
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO orders (order_date, user_id, total) VALUES (?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, order.getOrderDate());
                ps.setInt(2, order.getUserId());
                ps.setInt(3, order.getTotal());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    idOrder = rs.getLong(1);
                }
                ps.close();
                System.out.println(idOrder);
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
        return idOrder;
    }

    @Override
    public void addOrderDetails(OrderDetails orderDetails) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO order_details (product_id, quantity, amount, order_id)" +
                                " VALUES (?,?,?,?)");
                ps.setLong(1, orderDetails.getProduct().getIdProduct());
                ps.setInt(2, orderDetails.getQuantity());
                ps.setInt(3, orderDetails.getAmount());
                ps.setLong(4, orderDetails.getOrderId());
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
    public List<Order> getUserOrders(int userId) {
        List<Order> orders = new ArrayList<>();
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT id_order, total, order_date FROM orders WHERE user_id=?");
                ps.setInt(1, userId);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                Order order;
                while (rs.next()) {
                    order = new Order();
                    order.setIdOrder(rs.getInt("id_order"));
                    order.setOrderDate(rs.getString("order_date"));
                    order.setTotal(rs.getInt("total"));
                    orders.add(order);
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
        return getOrdersDetails(orders);
    }

    private List<Order> getOrdersDetails(List<Order> orders) {
        for (Order order : orders) {
            if (getConnection().equals("Good connection")) {
                try {
                    PreparedStatement ps = conn.prepareStatement(
                            "SELECT amount, quantity, product_id FROM order_details WHERE order_id=?");
                    ps.setLong(1, order.getIdOrder());
                    ps.execute();
                    ResultSet rs = ps.getResultSet();
                    OrderDetails od;
                    List<OrderDetails> orderDetails = new ArrayList<>();
                    while (rs.next()) {
                        od = new OrderDetails();
                        Product product = productRepository.getProductById(
                                rs.getInt("product_id"));
                        od.setProduct(product);
                        od.setQuantity(rs.getInt("quantity"));
                        od.setAmount(rs.getInt("amount"));
                        orderDetails.add(od);
                        order.setDetails(orderDetails);
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
        }
        return orders;
    }
}