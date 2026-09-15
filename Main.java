import model.Admin;
import model.Cart;
import model.Customer;
import model.FoodItem;
import model.InvalidOrderException;
import model.Order;
import model.OrderStatus;
import model.Payment;
import model.PaymentMethod;
import model.Restaurant;

import service.AdminService;
import service.OrderService;
import service.RestaurantService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("   ONLINE FOOD ORDERING SYSTEM");
        System.out.println("=================================");

        // Customer account
        Customer customer = new Customer(
                101,
                "Mani",
                "customer@gmail.com",
                "1234"
        );

        // Admin account
        Admin admin = new Admin(
                1,
                "System Admin",
                "admin@gmail.com",
                "admin123"
        );

        // Services
        RestaurantService restaurantService =
                new RestaurantService();

        AdminService adminService =
                new AdminService();

        OrderService orderService =
                new OrderService();

        // Login type
        System.out.println("\n========== LOGIN ==========");
        System.out.println("1. Customer Login");
        System.out.println("2. Admin Login");

        int loginChoice;

        try {

            System.out.print("Choose login type: ");
            loginChoice = scanner.nextInt();
            scanner.nextLine();

        } catch (InputMismatchException e) {

            System.out.println("Invalid input.");
            scanner.close();
            return;
        }

        if (loginChoice != 1 && loginChoice != 2) {

            System.out.println("Invalid login choice.");
            scanner.close();
            return;
        }

        boolean loggedIn = false;

        // Login attempts
        for (int attempt = 1; attempt <= 3; attempt++) {

            System.out.println("\n========== LOGIN ==========");

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (loginChoice == 1 &&
                    customer.login(email, password)) {

                loggedIn = true;

                System.out.println(
                        "\nCustomer login successful!"
                );

                System.out.println(
                        "Welcome, " + customer.getName() + "!"
                );

                break;

            } else if (loginChoice == 2 &&
                    adminService.login(
                            admin,
                            email,
                            password)) {

                loggedIn = true;

                System.out.println(
                        "\nAdmin login successful!"
                );

                System.out.println(
                        "Welcome, " + admin.getName() + "!"
                );

                break;

            } else {

                System.out.println(
                        "Invalid email or password."
                );

                if (attempt < 3) {

                    System.out.println(
                            "Attempts remaining: "
                            + (3 - attempt)
                    );
                }
            }
        }

        if (!loggedIn) {

            System.out.println(
                    "\nToo many failed attempts."
            );

            System.out.println(
                    "Exiting application."
            );

            scanner.close();
            return;
        }

        // Create restaurant
        Restaurant restaurant = new Restaurant(
                1,
                "Paradise Biryani",
                "Bhopal",
                4.5
        );

        // Create food items
        FoodItem biryani = new FoodItem(
                101,
                "Chicken Biryani",
                220,
                "Main Course",
                true
        );

        FoodItem paneer = new FoodItem(
                102,
                "Paneer Biryani",
                180,
                "Main Course",
                true
        );

        FoodItem naan = new FoodItem(
                103,
                "Butter Naan",
                50,
                "Bread",
                true
        );

        // Add food using service
        restaurantService.addFoodItem(
                restaurant,
                biryani
        );

        restaurantService.addFoodItem(
                restaurant,
                paneer
        );

        restaurantService.addFoodItem(
                restaurant,
                naan
        );

        // Admin section
        if (loginChoice == 2) {

            System.out.println(
                    "\n========== ADMIN DASHBOARD =========="
            );

            adminService.displayAdminDetails(admin);

            System.out.println(
                    "\n===== RESTAURANT ====="
            );

            System.out.println(
                    "Name: " + restaurant.getName()
            );

            System.out.println(
                    "Location: " + restaurant.getLocation()
            );

            System.out.println(
                    "Rating: " + restaurant.getRating()
            );

            restaurantService.displayRestaurantMenu(
                    restaurant
            );

            System.out.println(
                    "\nAdmin session completed."
            );

            scanner.close();
            return;
        }

        // Customer restaurant display
        System.out.println("\n===== RESTAURANT =====");

        System.out.println(
                "Name: " + restaurant.getName()
        );

        System.out.println(
                "Location: " + restaurant.getLocation()
        );

        System.out.println(
                "Rating: " + restaurant.getRating()
        );

        restaurantService.displayRestaurantMenu(
                restaurant
        );

        // Create cart
        Cart cart = new Cart();

        boolean ordering = true;

        while (ordering) {

            try {

                System.out.print(
                        "\nEnter Food ID (0 to finish): "
                );

                int foodId = scanner.nextInt();

                if (foodId == 0) {

                    ordering = false;
                    break;
                }

                System.out.print(
                        "Enter quantity: "
                );

                int quantity = scanner.nextInt();

                validateOrder(
                        foodId,
                        quantity
                );

                if (foodId == 101) {

                    cart.addItem(
                            biryani,
                            quantity
                    );

                    System.out.println(
                            "Chicken Biryani added to cart!"
                    );

                } else if (foodId == 102) {

                    cart.addItem(
                            paneer,
                            quantity
                    );

                    System.out.println(
                            "Paneer Biryani added to cart!"
                    );

                } else if (foodId == 103) {

                    cart.addItem(
                            naan,
                            quantity
                    );

                    System.out.println(
                            "Butter Naan added to cart!"
                    );
                }

            } catch (InvalidOrderException e) {

                System.out.println(
                        "Order Error: "
                        + e.getMessage()
                );

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid input! "
                        + "Please enter numbers only."
                );

                scanner.nextLine();
            }
        }

        // Empty cart check
        if (cart.isEmpty()) {

            System.out.println(
                    "\nYour cart is empty."
            );

            System.out.println(
                    "Order cancelled."
            );

            scanner.close();
            return;
        }

        // Display cart
        cart.displayCart();

        // Create order
        Order order = new Order(
                1001,
                customer.getName(),
                cart
        );

        order.displayOrder();

        // Process order
        System.out.println(
                "\n========== ORDER PROCESSING =========="
        );

        orderService.processOrder(order);

        // Payment
        System.out.println(
                "\n========== PAYMENT =========="
        );

        System.out.println(
                "1. Cash on Delivery"
        );

        System.out.println(
                "2. UPI"
        );

        System.out.println(
                "3. Card"
        );

        PaymentMethod paymentMethod;

        try {

            System.out.print(
                    "Choose payment method: "
            );

            int paymentChoice =
                    scanner.nextInt();

            if (paymentChoice == 1) {

                paymentMethod =
                        PaymentMethod.CASH_ON_DELIVERY;

            } else if (paymentChoice == 2) {

                paymentMethod =
                        PaymentMethod.UPI;

            } else if (paymentChoice == 3) {

                paymentMethod =
                        PaymentMethod.CARD;

            } else {

                System.out.println(
                        "Invalid payment method."
                );

                System.out.println(
                        "Using Cash on Delivery."
                );

                paymentMethod =
                        PaymentMethod.CASH_ON_DELIVERY;
            }

        } catch (InputMismatchException e) {

            System.out.println(
                    "Invalid input."
            );

            System.out.println(
                    "Using Cash on Delivery."
            );

            scanner.nextLine();

            paymentMethod =
                    PaymentMethod.CASH_ON_DELIVERY;
        }

        // Create payment
        Payment payment = new Payment(
                5001,
                cart.calculateTotal(),
                paymentMethod
        );

        payment.processPayment();

        payment.printReceipt();

        // Deliver order
        if (payment.isSuccessful()) {

            orderService.updateOrderStatus(
                    order,
                    OrderStatus.DELIVERED
            );
        }

        // Save order
        saveOrderToFile(
                order,
                payment
        );

        System.out.println(
                "\nThank you for using "
                + "Online Food Ordering System!"
        );

        scanner.close();
    }

    // Validate food order
    public static void validateOrder(
            int foodId,
            int quantity)
            throws InvalidOrderException {

        if (foodId != 101 &&
                foodId != 102 &&
                foodId != 103) {

            throw new InvalidOrderException(
                    "Food ID does not exist."
            );
        }

        if (quantity <= 0) {

            throw new InvalidOrderException(
                    "Quantity must be greater than zero."
            );
        }
    }

    // Save completed order
    public static void saveOrderToFile(
            Order order,
            Payment payment) {

        try {

            FileWriter writer =
                    new FileWriter(
                            "orders.txt",
                            true
                    );

            writer.write(
                    "Order ID: "
                    + order.getOrderId()
                    + "\n"
            );

            writer.write(
                    "Customer: "
                    + order.getCustomerName()
                    + "\n"
            );

            writer.write(
                    "Total: Rs."
                    + order.getCart().calculateTotal()
                    + "\n"
            );

            writer.write(
                    "Payment Method: "
                    + payment.isSuccessful()
                    + "\n"
            );

            writer.write(
                    "Status: "
                    + order.getStatus()
                    + "\n"
            );

            writer.write(
                    "Delivery Updates: "
            );

            for (OrderStatus status :
                    order.getStatusHistory()) {

                writer.write(
                        status.toString()
                );

                if (status !=
                        order.getStatusHistory()
                                .get(
                                        order.getStatusHistory()
                                                .size() - 1
                                )) {

                    writer.write(" -> ");
                }
            }

            writer.write("\n");

            writer.write(
                    "-----------------------------\n"
            );

            writer.close();

            System.out.println(
                    "\nOrder saved to orders.txt"
            );

        } catch (IOException e) {

            System.out.println(
                    "Unable to save order."
            );
        }
    }
}