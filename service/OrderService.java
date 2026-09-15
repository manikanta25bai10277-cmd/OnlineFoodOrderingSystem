package service;

import model.Order;
import model.OrderStatus;

public class OrderService {

    public void updateOrderStatus(Order order,
                                  OrderStatus status) {

        order.updateStatus(status);

        System.out.println(
                "Order Status: " + order.getStatus()
        );
    }

    public void processOrder(Order order) {

        updateOrderStatus(order, OrderStatus.CONFIRMED);

        updateOrderStatus(order, OrderStatus.PREPARING);

        updateOrderStatus(
                order,
                OrderStatus.OUT_FOR_DELIVERY
        );
    }
}
