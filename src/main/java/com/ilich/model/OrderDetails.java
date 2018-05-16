package com.ilich.model;

public class OrderDetails {

    private long idOrderDetails;
    private Product product;
    private int quantity;
    private int amount;
    private long orderId;

    public OrderDetails() {
    }

    public long getIdOrderDetails() {
        return idOrderDetails;
    }

    public void setIdOrderDetails(long idOrderDetails) {
        this.idOrderDetails = idOrderDetails;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        OrderDetails that = (OrderDetails) object;

        if (idOrderDetails != that.idOrderDetails) return false;
        if (quantity != that.quantity) return false;
        if (amount != that.amount) return false;
        if (orderId != that.orderId) return false;
        return product.equals(that.product);
    }

    @Override
    public int hashCode() {
        int result = (int) (idOrderDetails ^ (idOrderDetails >>> 32));
        result = 31 * result + product.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + amount;
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "idOrderDetails=" + idOrderDetails +
                ", product=" + product +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", orderId=" + orderId +
                '}';
    }
}