package com.ilich.controllers.user.comment;

import com.ilich.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/delete-comment")
public class DeleteCommentController {

    private final CommentService commentService;

    @Autowired
    public DeleteCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String deleteComment(HttpServletRequest request,
                                final RedirectAttributes redirectAttributes) {
        commentService.removeComment(Long.parseLong(request.getParameter("idComment")));
        redirectAttributes.addAttribute("idProduct",
                Integer.parseInt(request.getParameter("productId")));
        return "redirect:/product-in-detail";
    }
}