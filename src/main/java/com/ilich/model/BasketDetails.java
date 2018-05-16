package com.ilich.model;

public class BasketDetails {

    private long idBasketDetails;
    private Product product;
    private int quantity;
    private int amount;
    private int userId;

    public BasketDetails() {
    }

    public long getIdBasketDetails() {
        return idBasketDetails;
    }

    public void setIdBasketDetails(long idBasketDetails) {
        this.idBasketDetails = idBasketDetails;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        BasketDetails that = (BasketDetails) object;

        if (idBasketDetails != that.idBasketDetails) return false;
        if (quantity != that.quantity) return false;
        if (amount != that.amount) return false;
        if (userId != that.userId) return false;
        return product.equals(that.product);
    }

    @Override
    public int hashCode() {
        int result = (int) (idBasketDetails ^ (idBasketDetails >>> 32));
        result = 31 * result + product.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + amount;
        result = 31 * result + (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "BasketDetails{" +
                "idBasketDetails=" + idBasketDetails +
                ", product=" + product +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", userId=" + userId +
                '}';
    }
}