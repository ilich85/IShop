package com.ilich.repository.jdbc;

import com.ilich.repository.interfaces.CommentRepository;
import com.ilich.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("commentRepository")
public class JDBCCommentRepository implements CommentRepository {

    private Connection conn;
    private final DataSource dataSource;

    @Autowired
    public JDBCCommentRepository(DataSource dataSource) {
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
    public void addComment(Comment comment) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO comments (comment_text, comment_date, " +
                                "username, product_id) VALUES (?,?,?,?)");
                ps.setString(1, comment.getCommentText());
                ps.setString(2, comment.getCommentDate());
                ps.setString(3, comment.getUserName());
                ps.setInt(4, comment.getProductId());
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
    public void updateComment(Comment comment) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE comments SET comment_text=?, comment_date=? " +
                                "WHERE id_comment=?");
                ps.setString(1, comment.getCommentText());
                ps.setString(2, comment.getCommentDate());
                ps.setLong(3, comment.getIdComment());
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
    public void removeComment(long idComment) {
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM comments WHERE id_comment=?");
                ps.setLong(1, idComment);
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
    public List<Comment> getCommentsListForProduct(int productId) {
        List<Comment> comments = new ArrayList<>();
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT id_comment, comment_text, comment_date, username " +
                                "FROM comments WHERE product_id=?");
                ps.setInt(1, productId);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                Comment comment;
                while (rs.next()) {
                    comment = new Comment();
                    comment.setIdComment(rs.getLong("id_comment"));
                    comment.setCommentText(rs.getString("comment_text"));
                    comment.setCommentDate(rs.getString("comment_date"));
                    comment.setUserName(rs.getString("username"));
                    comments.add(comment);
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
        return comments;
    }

    @Override
    public List<Comment> getCommentsByUserName(String userName) {
        List<Comment> comments = new ArrayList<>();
        if (getConnection().equals("Good connection")) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                        "SELECT id_comment, comment_text, comment_date, product_id " +
                                "FROM comments WHERE username=?");
                ps.setString(1, userName);
                ps.execute();
                ResultSet rs = ps.getResultSet();
                Comment comment;
                while (rs.next()) {
                    comment = new Comment();
                    comment.setIdComment(rs.getLong("id_comment"));
                    comment.setCommentText(rs.getString("comment_text"));
                    comment.setCommentDate(rs.getString("comment_date"));
                    comment.setIdComment(rs.getInt("product_id"));
                    comments.add(comment);
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
        return comments;
    }
}