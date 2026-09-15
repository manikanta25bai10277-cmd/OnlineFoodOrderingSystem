package model;

public class FoodItem {

    private int foodId;
    private String name;
    private double price;
    private String category;
    private boolean available;

    public FoodItem(int foodId, String name, double price,
                    String category, boolean available) {

        this.foodId = foodId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void displayFoodItem() {

        System.out.println(
                foodId + ". " +
                name + " - Rs." +
                price + " [" +
                category + "]"
        );
    }
}