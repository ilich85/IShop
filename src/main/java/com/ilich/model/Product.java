package com.ilich.model;

import java.util.List;

public class Product {

    private int idProduct;
    private String productName;
    private int price;
    private String description;
    private String categoryName;
    private int imageId;
    private int quantity;
    private List<Comment> comments;

    public Product() {
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Product product = (Product) object;

        if (idProduct != product.idProduct) return false;
        if (price != product.price) return false;
        if (!productName.equals(product.productName)) return false;
        if (!description.equals(product.description)) return false;
        return comments != null ? comments.equals(product.comments) : product.comments == null;
    }

    @Override
    public int hashCode() {
        int result = idProduct ^ (idProduct >>> 32);
        result = 31 * result + productName.hashCode();
        result = 31 * result + price;
        result = 31 * result + description.hashCode();
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", categoryName=" + categoryName +
                ", imageId='" + imageId + '\'' +
                ", comments=" + comments +
                '}';
    }
}