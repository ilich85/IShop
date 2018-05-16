package com.ilich.services.impl;


import com.ilich.model.Comment;
import com.ilich.repository.interfaces.CommentRepository;
import com.ilich.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("commentService")
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void addComment(Comment comment) {
        this.commentRepository.addComment(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        this.commentRepository.updateComment(comment);
    }

    @Override
    public void removeComment(long idComment) {
        this.commentRepository.removeComment(idComment);
    }

    @Override
    public List<Comment> getCommentsListForProduct(int productId) {
        return this.commentRepository.getCommentsListForProduct(productId);
    }
}