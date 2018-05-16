package com.ilich.services.interfaces;

import com.ilich.model.Order;
import com.ilich.model.OrderDetails;

import java.util.List;

public interface OrderService {

    long addOrder(Order order);

    void addOrderDetails(OrderDetails orderDetails);

    List<Order> getUserOrders(int userId);
}