package com.ilich.repository.jdbc;

import com.ilich.repository.interfaces.UserRepository;
import com.ilich.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("userRepository")
public class JDBCUserRepository implements UserRepository {

    private String result;
    private Connection conn;
    private final DataSource dataSource;

    @Autowired
    public JDBCUserRepository(DataSource dataSource) {
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
    public String addUser(User user) {
        String userExists = userExists(user.getUserName(), user.getEmail());
        switch (userExists) {
            case "userExists":
                result = "userExists";
                break;
            case "emailExists":
                result = "emailExists";
                break;
            case "error":
                result = "error";
                break;
            default:
                if (getConnection().equals("Good connection")) {
                    try {
                        PreparedStatement ps = conn.prepareStatement(
                                "INSERT INTO users (user_name, password, email) VALUES (?,?,?)");
                        ps.setString(1, user.getUserName());
                        ps.setString(2, user.getPassword());
                        ps.setString(3, user.getEmail());
                        ps.executeUpdate();
                        ps.close();
                        result = "User created successfully";
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
                break;
        }
        return result;
    }

    @Override
    public void removeUser(String userName) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE user_name=?");
                ps.setString(1, userName);
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
    public int getIdByUserName(String userName) {
        int idUser = 0;
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT id_user FROM users WHERE user_name=?");
                ps.setString(1, userName);

                ps.execute();
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    idUser = rs.getInt("id_user");
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
        return idUser;
    }

    @Override
    public String checkUser(String userName, String password) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT password FROM users WHERE user_name=?");
                ps.setString(1, userName);
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
                result = "error";
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        result = null;
                    }
                }
            }
        }
        return result;
    }

    private String userExists(String userName, String email) {
        String exists = "";
        List<String> usersName = new ArrayList<>();
        List<String> emails = new ArrayList<>();
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT user_name, email FROM users");
                ps.execute();
                ResultSet rs = ps.getResultSet();
                String name;
                String mail;
                while (rs.next()) {
                    name = rs.getString("user_name");
                    mail = rs.getString("email");
                    usersName.add(name);
                    emails.add(mail);
                }
                ps.close();
                for (String n : usersName) {
                    if (n.equals(userName)) {
                        return "userExists";
                    }
                }
                for (String m : emails) {
                    if (m.equals(email)) {
                        exists = "emailExists";
                    }
                }
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
        return exists;
    }
}