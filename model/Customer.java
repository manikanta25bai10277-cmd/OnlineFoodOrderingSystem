package model;

public class Customer extends User {

    public Customer(int userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    public void showCustomerDetails() {
        System.out.println("\n===== CUSTOMER DETAILS =====");
        System.out.println("Customer ID: " + getUserId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
    }

    public void browseFood() {
        System.out.println("\nBrowsing available food...");
    }

    public boolean login(String email, String password) {
        return getEmail().equals(email)
                && getPassword().equals(password);
    }
}