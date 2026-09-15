package model;

public class Admin extends User {

    public Admin(int userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    public void showAdminDetails() {
        System.out.println("\n===== ADMIN DETAILS =====");
        System.out.println("Admin ID: " + getUserId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
    }

    public void manageRestaurant() {
        System.out.println("\nAdmin is managing restaurants...");
    }
}