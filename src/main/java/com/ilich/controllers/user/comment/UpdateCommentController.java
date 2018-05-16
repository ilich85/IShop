package com.ilich.controllers.user.comment;

import com.ilich.model.Comment;
import com.ilich.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/update-comment")
public class UpdateCommentController {

    private final CommentService commentService;

    @Autowired
    public UpdateCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String updateComment(HttpServletRequest request,
                                final RedirectAttributes redirectAttributes) {
        Comment comment = new Comment();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        comment.setProductId(Integer.valueOf(request.getParameter("productId")));
        comment.setCommentText(request.getParameter("commentText"));
        comment.setCommentDate(new SimpleDateFormat().format(new Date()));
        comment.setIdComment(Long.parseLong(request.getParameter("idComment")));
        commentService.updateComment(comment);
        redirectAttributes.addAttribute("idProduct", comment.getProductId());
        return "redirect:/product-in-detail";
    }
}