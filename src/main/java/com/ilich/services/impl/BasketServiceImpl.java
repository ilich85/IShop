package com.ilich.services.impl;

import com.ilich.services.interfaces.BasketService;
import com.ilich.model.Basket;
import com.ilich.model.BasketDetails;
import com.ilich.repository.interfaces.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("basketService")
@Transactional(readOnly = true)
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public Basket getBasket(String userName) {
        return this.basketRepository.getBasket(userName);
    }

    @Override
    public void addToBasket(BasketDetails basketDetails) {
        this.basketRepository.addToBasket(basketDetails);
    }

    @Override
    public void removeFromBasket(int idProduct, int idUser) {
        this.basketRepository.removeFromBasket(idProduct, idUser);
    }

    @Override
    public void updateQuantity(BasketDetails bd) {
        this.basketRepository.updateQuantity(bd);
    }

    @Override
    public void clearBasket(int userId) {
        this.basketRepository.clearBasket(userId);
    }

    @Override
    public int getQuantityInBasketDetail(long idBasketDetail) {
        return this.basketRepository.getQuantityInBasketDetail(idBasketDetail);
    }
}