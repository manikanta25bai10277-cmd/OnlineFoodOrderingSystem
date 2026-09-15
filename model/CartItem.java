package model;

public class CartItem {

    private FoodItem foodItem;
    private int quantity;

    public CartItem(FoodItem foodItem, int quantity) {
        this.foodItem = foodItem;
        this.quantity = quantity;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public double getTotalPrice() {
        return foodItem.getPrice() * quantity;
    }

    public void displayCartItem() {

        System.out.println(
                foodItem.getName()
                + " x " + quantity
                + " = Rs." + getTotalPrice()
        );
    }
}