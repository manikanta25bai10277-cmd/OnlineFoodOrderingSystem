package service;

import model.FoodItem;
import model.Restaurant;

public class RestaurantService {

    public void addFoodItem(Restaurant restaurant,
                            FoodItem foodItem) {

        restaurant.addFoodItem(foodItem);

        System.out.println(
                foodItem.getName()
                + " added to restaurant menu."
        );
    }

    public void displayRestaurantMenu(Restaurant restaurant) {

        restaurant.showMenu();
    }
}