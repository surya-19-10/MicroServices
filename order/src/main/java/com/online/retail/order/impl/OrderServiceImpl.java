package com.online.retail.order.impl;

import com.online.retail.order.model.Order;
import com.online.retail.order.repo.OrderRepository;
import com.online.retail.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(int id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Order updateOrder(int id, Order order) {
        return orderRepository.save(order);
    }

    @Override
    public String deleteOrder(int id) {
        orderRepository.deleteById(id);
        return "Deleted";
    }
}
