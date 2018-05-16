package com.ilich.services.impl;

import com.ilich.model.Order;
import com.ilich.model.OrderDetails;
import com.ilich.repository.interfaces.OrderRepository;
import com.ilich.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public long addOrder(Order order) {
        return this.orderRepository.addOrder(order);
    }

    @Override
    public void addOrderDetails(OrderDetails orderDetails) {
        this.orderRepository.addOrderDetails(orderDetails);
    }

    @Override
    public List<Order> getUserOrders(int userId) {
        return this.orderRepository.getUserOrders(userId);
    }
}