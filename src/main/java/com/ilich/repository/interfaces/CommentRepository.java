package com.ilich.repository.interfaces;

import com.ilich.model.Comment;

import java.util.List;

public interface CommentRepository {

    void addComment(Comment comment);

    void updateComment(Comment comment);

    void removeComment(long idComment);

    List<Comment> getCommentsListForProduct(int productId);

    List<Comment> getCommentsByUserName(String userName);
}