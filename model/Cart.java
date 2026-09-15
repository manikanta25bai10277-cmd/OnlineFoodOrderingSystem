package model;

import java.util.ArrayList;

public class Cart {

    private ArrayList<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(FoodItem foodItem, int quantity) {

        for (CartItem item : items) {

            if (item.getFoodItem().getFoodId()
                    == foodItem.getFoodId()) {

                item.increaseQuantity(quantity);
                return;
            }
        }

        items.add(new CartItem(foodItem, quantity));
    }

    public void removeItem(int foodId) {

        for (CartItem item : items) {

            if (item.getFoodItem().getFoodId() == foodId) {
                items.remove(item);
                System.out.println("Item removed from cart.");
                return;
            }
        }

        System.out.println("Item not found in cart.");
    }

    public double calculateTotal() {

        double total = 0;

        for (CartItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void displayCart() {

        System.out.println("\n========== YOUR CART ==========");

        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        for (CartItem item : items) {
            item.displayCartItem();
        }

        System.out.println("-------------------------------");
        System.out.println("Total: Rs." + calculateTotal());
    }
}