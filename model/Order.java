package model;

import java.util.ArrayList;

public class Order {

    private int orderId;
    private String customerName;
    private Cart cart;
    private OrderStatus status;

    private ArrayList<OrderStatus> statusHistory;

    public Order(int orderId, String customerName, Cart cart) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.cart = cart;
        this.status = OrderStatus.PLACED;

        statusHistory = new ArrayList<>();
        statusHistory.add(OrderStatus.PLACED);
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Cart getCart() {
        return cart;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ArrayList<OrderStatus> getStatusHistory() {
        return statusHistory;
    }

    public void updateStatus(OrderStatus status) {

        this.status = status;

        statusHistory.add(status);
    }

    public void displayOrder() {

        System.out.println("\n========== ORDER DETAILS ==========");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Status: " + status);

        cart.displayCart();
    }
}