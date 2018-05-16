package com.ilich.repository.interfaces;

import com.ilich.model.DeliveryAddress;

public interface DeliveryAddressRepository {

    void setAddress(DeliveryAddress deliveryAddress);

    void updateAddress(DeliveryAddress deliveryAddress);

    DeliveryAddress getUserAddress(int userId);
}