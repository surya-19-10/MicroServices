package com.online.retail.order.service;

import com.online.retail.order.model.Order;

public interface OrderService {
    Order saveOrder(Order order);

    Order getOrder(int id);

    Order updateOrder(int id, Order order);

    String deleteOrder(int id);
}
