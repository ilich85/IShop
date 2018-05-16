package com.ilich.repository.jdbc;

import com.ilich.repository.interfaces.AdminRepository;
import com.ilich.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("adminRepository")
public class JDBCAdminRepository implements AdminRepository {

    private String result;
    private Connection conn;
    private final DataSource dataSource;

    @Autowired
    public JDBCAdminRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private String getConnection() {
        try {
            conn = dataSource.getConnection();
            result = "Good connection";
        } catch (SQLException e1) {
            result = "error";
        }
        return result;
    }

    @Override
    public String addAdmin(Admin admin) {
        if (admin.getAdminName().length() < 6) {
            return "Логин должен быть минимум 6 символов";
        } else if (admin.getPassword().length() < 6) {
            return "Пароль должен быть минимум 6 символов";
        }
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO admins (admin_name, password) VALUES (?,?)");
                ps.setString(1, admin.getAdminName());
                ps.setString(2, admin.getPassword());
                ps.executeUpdate();
                ps.close();
                result = "Администратор успешно зарегистрирован";
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
    public String removeAdmin(String adminName) {
        if (adminName.equals("superadmin")) {
            return "error";
        }
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM admins WHERE admin_name=?");
                ps.setString(1, adminName);
                ps.executeUpdate();
                ps.close();
                result = "Delete admin is complete";
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
    public String checkAdmin(String adminName, String password) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT password FROM admins WHERE admin_name=?");
                ps.setString(1, adminName);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                String pass = null;
                while (rs.next()) {
                    pass = rs.getString("password");
                }
                if (pass != null) {
                    if (pass.equals(password)) {
                        result = "complete";
                    } else {
                        result = "wrong";
                    }
                } else {
                    result = "not_exist";
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
        return result;
    }

    @Override
    public List<Admin> getAdminsList() {
        List<Admin> admins = new ArrayList<>();
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT admin_name, password FROM admins");
                ps.execute();
                ResultSet rs = ps.getResultSet();
                Admin admin;
                while (rs.next()) {
                    admin = new Admin();
                    admin.setAdminName(rs.getString("admin_name"));
                    admins.add(admin);
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
        return admins;
    }
}