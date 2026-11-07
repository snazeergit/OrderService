package com.booking.service;

import com.booking.entity.Order;
import com.booking.exception.OrderNotFoundException;
import com.booking.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public String getHealthStatus() {
        return "Order Service is up and running!";
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public String createOrder(Order order) {
        orderRepository.save(order);
        return "Order created successfully!";
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    public String updateOrder(Long id, Order order) {
        Order existing = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        existing.setCustomerName(order.getCustomerName());
        existing.setProduct(order.getProduct());
        existing.setQuantity(order.getQuantity());
        orderRepository.save(existing);
        return "Order updated successfully!";
    }

    public String deleteOrder(Long id) {
        orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        orderRepository.deleteById(id);
        return "Order deleted successfully!";
    }

    public String updateOrderProperties(Long id, Order order) {
        Order existing = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        if (order.getCustomerName() != null)
            existing.setCustomerName(order.getCustomerName());
        if (order.getProduct() != null)
            existing.setProduct(order.getProduct());
        if (order.getQuantity() != null)
            existing.setQuantity(order.getQuantity());
        orderRepository.save(existing);
        return "Customer name updated successfully!";
    }
}
