package com.ilich.model;

public class Comment {

    private long idComment;
    private String commentText;
    private String commentDate;
    private String userName;
    private int productId;

    public Comment() {
    }

    public long getIdComment() {
        return idComment;
    }

    public void setIdComment(long idComment) {
        this.idComment = idComment;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Comment comment = (Comment) object;

        if (idComment != comment.idComment) return false;
        if (productId != comment.productId) return false;
        if (!commentText.equals(comment.commentText)) return false;
        if (!commentDate.equals(comment.commentDate)) return false;
        return userName.equals(comment.userName);
    }

    @Override
    public int hashCode() {
        int result = (int) (idComment ^ (idComment >>> 32));
        result = 31 * result + commentText.hashCode();
        result = 31 * result + commentDate.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + productId;
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idComment=" + idComment +
                ", commentText='" + commentText + '\'' +
                ", commentDate='" + commentDate + '\'' +
                ", userName='" + userName + '\'' +
                ", productId=" + productId +
                '}';
    }
}