package com.ilich.model;

import java.util.List;

public class Order {

    private long idOrder;
    private int userId;
    private int total;
    private String orderDate;
    private List<OrderDetails> details;

    public Order() {
    }

    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderDetails> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetails> details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Order order = (Order) object;

        if (idOrder != order.idOrder) return false;
        if (userId != order.userId) return false;
        if (total != order.total) return false;
        if (!orderDate.equals(order.orderDate)) return false;
        return details.equals(order.details);
    }

    @Override
    public int hashCode() {
        int result = (int) (idOrder ^ (idOrder >>> 32));
        result = 31 * result + userId;
        result = 31 * result + total;
        result = 31 * result + orderDate.hashCode();
        result = 31 * result + details.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", userId=" + userId +
                ", total=" + total +
                ", orderDate='" + orderDate + '\'' +
                ", details=" + details +
                '}';
    }
}