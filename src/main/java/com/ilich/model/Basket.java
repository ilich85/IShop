package com.ilich.model;

import java.util.List;

public class Basket {

    private int idBasket;
    private List<BasketDetails> details;
    private int total;
    private int userId;

    public Basket() {
    }

    public int getIdBasket() {
        return idBasket;
    }

    public void setIdBasket(int idBasket) {
        this.idBasket = idBasket;
    }

    public List<BasketDetails> getDetails() {
        return details;
    }

    public void setDetails(List<BasketDetails> details) {
        this.details = details;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Basket basket = (Basket) object;

        if (idBasket != basket.idBasket) return false;
        if (total != basket.total) return false;
        if (userId != basket.userId) return false;
        return details != null ? details.equals(basket.details) : basket.details == null;
    }

    @Override
    public int hashCode() {
        int result = idBasket;
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + total;
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "idBasket=" + idBasket +
                ", details=" + details +
                ", total=" + total +
                ", userId=" + userId +
                '}';
    }
}