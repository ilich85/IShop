package com.ilich.repository.interfaces;

import com.ilich.model.Basket;
import com.ilich.model.BasketDetails;

public interface BasketRepository {

    Basket getBasket(String userName);

    void addToBasket(BasketDetails basketDetails);

    void removeFromBasket(int idProduct, int idUser);

    void updateQuantity(BasketDetails basketDetails);

    void clearBasket(int userId);

    int getQuantityInBasketDetail(long idBasketDetail);
}