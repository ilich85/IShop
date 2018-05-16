package com.ilich.services.interfaces;

import com.ilich.model.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment);

    void updateComment(Comment comment);

    void removeComment(long idComment);

    List<Comment> getCommentsListForProduct(int productId);
}