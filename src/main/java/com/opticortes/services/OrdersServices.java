package com.opticortes.services;

import com.mongodb.client.MongoDatabase;
import com.opticortes.dao.OrderDAO;
import com.opticortes.entities.Order;

import java.util.List;

public class OrdersServices {
    MongoDatabase db = null;

    public List<Order> getOrders() {
        List<Order> orders;
        orders = new OrderDAO().selectAll();
        return orders;
    }

    public Order getOrder(Order order) {
        Order orderSelected;
        orderSelected = new OrderDAO().select(order);
        return orderSelected;
    }

    public int addNewOrder(Order order) {
        int rows;
        rows = new OrderDAO().insert(order);
        return rows;
    }

    public int updateOrder(Order order) {
        int rows;
        rows = new OrderDAO().update(order);
        return rows;
    }

    public int deleteOrder(Order order) {
        int rows;
        rows = new OrderDAO().delete(order);
        return rows;
    }

    public int deleteOrders() {
        int rows;
        rows = new OrderDAO().deleteAll();
        return rows;
    }

    public int chageStatus(Order order) {
        int rows;
        rows = new OrderDAO().update(order, "status", order.getStatus());
        return rows;
    }
}