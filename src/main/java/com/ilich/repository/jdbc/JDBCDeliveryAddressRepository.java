package com.ilich.repository.jdbc;

import com.ilich.repository.interfaces.DeliveryAddressRepository;
import com.ilich.model.DeliveryAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository("deliveryAddressRepository")
public class JDBCDeliveryAddressRepository implements DeliveryAddressRepository {

    private String result;
    private Connection conn;

    private final DataSource dataSource;

    @Autowired
    public JDBCDeliveryAddressRepository(DataSource dataSource) {
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
    public void setAddress(DeliveryAddress deliveryAddress) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO addresses (first_name, last_name, country,city, " +
                                "street, house, flat, user_id) VALUES (?,?,?,?,?,?,?,?)");
                ps.setString(1, deliveryAddress.getFirstName());
                ps.setString(2, deliveryAddress.getLastName());
                ps.setString(3, deliveryAddress.getCountry());
                ps.setString(4, deliveryAddress.getCity());
                ps.setString(5, deliveryAddress.getStreet());
                ps.setInt(6, deliveryAddress.getHouse());
                ps.setInt(7, deliveryAddress.getFlat());
                ps.setInt(8, deliveryAddress.getUserId());
                ps.executeUpdate();
                ps.close();
                result = "Delivery address added successfully";
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


    public void updateAddress(DeliveryAddress deliveryAddress) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE addresses SET first_name=?, last_name=?, " +
                                "country=?, city=?, street=?, house=?, " +
                                "flat=? WHERE user_id=?");
                ps.setString(1, deliveryAddress.getFirstName());
                ps.setString(2, deliveryAddress.getLastName());
                ps.setString(3, deliveryAddress.getCountry());
                ps.setString(4, deliveryAddress.getCity());
                ps.setString(5, deliveryAddress.getStreet());
                ps.setInt(6, deliveryAddress.getHouse());
                ps.setInt(7, deliveryAddress.getFlat());
                ps.setInt(8, deliveryAddress.getUserId());
                ps.executeUpdate();
                ps.close();
                result = "Delivery address updated successfully";
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
    public DeliveryAddress getUserAddress(int userId) {
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT * FROM addresses WHERE user_id=?");
                ps.setInt(1, userId);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    deliveryAddress.setIdAddress(rs.getInt("id_address"));
                    deliveryAddress.setFirstName(rs.getString("first_name"));
                    deliveryAddress.setLastName(rs.getString("last_name"));
                    deliveryAddress.setCountry(rs.getString("country"));
                    deliveryAddress.setCity(rs.getString("city"));
                    deliveryAddress.setStreet(rs.getString("street"));
                    deliveryAddress.setHouse(rs.getInt("house"));
                    deliveryAddress.setFlat(rs.getInt("flat"));
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
        return deliveryAddress;
    }
}