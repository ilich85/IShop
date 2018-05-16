package com.ilich.repository.interfaces;

import com.ilich.model.Order;
import com.ilich.model.OrderDetails;

import java.util.List;

public interface OrderRepository {

    long addOrder(Order order);

    void addOrderDetails(OrderDetails orderDetails);

    List<Order> getUserOrders(int userId);
}