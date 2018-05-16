package com.ilich.services.impl;

import com.ilich.model.DeliveryAddress;
import com.ilich.repository.interfaces.DeliveryAddressRepository;
import com.ilich.services.interfaces.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("deliveryAddressService")
@Transactional(readOnly = true)
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    private final DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    public DeliveryAddressServiceImpl(DeliveryAddressRepository deliveryAddressRepository) {
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    @Override
    public void setAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddressRepository.setAddress(deliveryAddress);
    }

    public void updateAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddressRepository.updateAddress(deliveryAddress);
    }

    @Override
    public DeliveryAddress getUserAddress(int userId) {
        return this.deliveryAddressRepository.getUserAddress(userId);
    }
}