package model;

import java.util.ArrayList;

public class Restaurant {

    private int restaurantId;
    private String name;
    private String location;
    private double rating;

    private ArrayList<FoodItem> foodItems;

    public Restaurant(int restaurantId, String name,
                      String location, double rating) {

        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.foodItems = new ArrayList<>();
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void addFoodItem(FoodItem foodItem) {
        foodItems.add(foodItem);
    }

    public void showMenu() {

        System.out.println("\n===== " + name + " MENU =====");

        if (foodItems.isEmpty()) {
            System.out.println("No food items available.");
            return;
        }

        for (FoodItem food : foodItems) {
            food.displayFoodItem();
        }
    }
}