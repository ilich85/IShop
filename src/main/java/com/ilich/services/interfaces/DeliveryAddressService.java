package com.ilich.services.interfaces;

import com.ilich.model.DeliveryAddress;

public interface DeliveryAddressService {

    void setAddress(DeliveryAddress deliveryAddress);

    void updateAddress(DeliveryAddress deliveryAddress);

    DeliveryAddress getUserAddress(int userId);
}