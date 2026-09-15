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

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FoodOrderingGUI extends JFrame {

    private Customer customer;
    private Admin admin;
    private Restaurant restaurant;

    private RestaurantService restaurantService;
    private AdminService adminService;
    private OrderService orderService;

    private Cart cart;

    private ArrayList<FoodItem> foodItems;

    private JTextField emailField;
    private JPasswordField passwordField;

    private DefaultListModel<String> cartListModel;
    private JLabel totalLabel;

    public FoodOrderingGUI() {

        // Customer account
        customer = new Customer(
                101,
                "Mani",
                "customer@gmail.com",
                "1234"
        );

        // Admin account
        admin = new Admin(
                1,
                "System Admin",
                "admin@gmail.com",
                "admin123"
        );

        // Restaurant
        restaurant = new Restaurant(
                1,
                "Paradise Biryani",
                "Bhopal",
                4.5
        );

        // Services
        restaurantService =
                new RestaurantService();

        adminService =
                new AdminService();

        orderService =
                new OrderService();

        // Initial food items
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

        foodItems =
                restaurant.getFoodItems();

        cart = new Cart();

        setTitle(
                "Online Food Ordering System"
        );

        setSize(1000, 650);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        showLoginScreen();
    }

    // =====================================================
    // LOGIN SCREEN
    // =====================================================

    private void showLoginScreen() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        40, 80, 40, 80
                )
        );

        JLabel title =
                new JLabel(
                        "ONLINE FOOD ORDERING SYSTEM",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel loginPanel =
                new JPanel(
                        new GridBagLayout()
                );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JLabel emailLabel =
                new JLabel("Email:");

        emailField =
                new JTextField(20);

        JLabel passwordLabel =
                new JLabel("Password:");

        passwordField =
                new JPasswordField(20);

        JRadioButton customerButton =
                new JRadioButton(
                        "Customer Login",
                        true
                );

        JRadioButton adminButton =
                new JRadioButton(
                        "Admin Login"
                );

        ButtonGroup group =
                new ButtonGroup();

        group.add(customerButton);
        group.add(adminButton);

        JButton loginButton =
                new JButton("Login");

        gbc.gridx = 0;
        gbc.gridy = 0;

        loginPanel.add(
                emailLabel,
                gbc
        );

        gbc.gridx = 1;

        loginPanel.add(
                emailField,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy = 1;

        loginPanel.add(
                passwordLabel,
                gbc
        );

        gbc.gridx = 1;

        loginPanel.add(
                passwordField,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy = 2;

        loginPanel.add(
                customerButton,
                gbc
        );

        gbc.gridx = 1;

        loginPanel.add(
                adminButton,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridy = 3;

        loginPanel.add(
                loginButton,
                gbc
        );

        mainPanel.add(
                loginPanel,
                BorderLayout.CENTER
        );

        loginButton.addActionListener(e -> {

            String email =
                    emailField.getText();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            if (customerButton.isSelected()) {

                if (customer.login(
                        email,
                        password
                )) {

                    cart = new Cart();

                    showCustomerScreen();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid customer email or password.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } else {

                if (adminService.login(
                        admin,
                        email,
                        password
                )) {

                    showAdminScreen();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid admin email or password.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        setContentPane(mainPanel);

        revalidate();
        repaint();
    }

    // =====================================================
    // CUSTOMER SCREEN
    // =====================================================

    private void showCustomerScreen() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        JLabel heading =
                new JLabel(
                        "Paradise Biryani - Bhopal | Rating: 4.5"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        mainPanel.add(
                heading,
                BorderLayout.NORTH
        );

        // Food menu
        JPanel foodPanel =
                new JPanel();

        foodPanel.setLayout(
                new BoxLayout(
                        foodPanel,
                        BoxLayout.Y_AXIS
                )
        );

        for (FoodItem food : foodItems) {

            JPanel foodRow =
                    new JPanel(
                            new FlowLayout(
                                    FlowLayout.LEFT
                            )
                    );

            JLabel foodLabel =
                    new JLabel(
                            food.getFoodId()
                            + " - "
                            + food.getName()
                            + " | Rs."
                            + food.getPrice()
                            + " | "
                            + food.getCategory()
                    );

            JSpinner quantitySpinner =
                    new JSpinner(
                            new SpinnerNumberModel(
                                    1,
                                    1,
                                    20,
                                    1
                            )
                    );

            JButton addButton =
                    new JButton(
                            "Add to Cart"
                    );

            addButton.addActionListener(
                    e -> {

                        int quantity =
                                (Integer)
                                        quantitySpinner
                                                .getValue();

                        try {

                            validateOrder(
                                    food.getFoodId(),
                                    quantity
                            );

                            cart.addItem(
                                    food,
                                    quantity
                            );

                            updateCartDisplay();

                            JOptionPane.showMessageDialog(
                                    this,
                                    food.getName()
                                    + " added to cart."
                            );

                        } catch (
                                InvalidOrderException ex) {

                            JOptionPane.showMessageDialog(
                                    this,
                                    ex.getMessage(),
                                    "Order Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
            );

            foodRow.add(foodLabel);

            foodRow.add(
                    new JLabel("Quantity:")
            );

            foodRow.add(
                    quantitySpinner
            );

            foodRow.add(
                    addButton
            );

            foodPanel.add(foodRow);
        }

        mainPanel.add(
                new JScrollPane(foodPanel),
                BorderLayout.CENTER
        );

        // Cart panel
        JPanel rightPanel =
                new JPanel(
                        new BorderLayout()
                );

        JLabel cartTitle =
                new JLabel(
                        "YOUR CART",
                        SwingConstants.CENTER
                );

        cartTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        rightPanel.add(
                cartTitle,
                BorderLayout.NORTH
        );

        cartListModel =
                new DefaultListModel<>();

        JList<String> cartList =
                new JList<>(
                        cartListModel
                );

        rightPanel.add(
                new JScrollPane(cartList),
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                1,
                                5,
                                5
                        )
                );

        totalLabel =
                new JLabel(
                        "Total: Rs.0.0"
                );

        totalLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        JButton placeOrderButton =
                new JButton(
                        "Place Order"
                );

        JButton previousOrdersButton =
                new JButton(
                        "Previous Orders"
                );

        JButton logoutButton =
                new JButton(
                        "Logout"
                );

        bottomPanel.add(
                totalLabel
        );

        bottomPanel.add(
                placeOrderButton
        );

        bottomPanel.add(
                previousOrdersButton
        );

        bottomPanel.add(
                logoutButton
        );

        rightPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                rightPanel,
                BorderLayout.EAST
        );

        placeOrderButton.addActionListener(
                e -> placeOrder()
        );

        previousOrdersButton.addActionListener(
                e -> showPreviousOrders()
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        setContentPane(mainPanel);

        revalidate();
        repaint();
    }

    // =====================================================
    // CART
    // =====================================================

    private void updateCartDisplay() {

        cartListModel.clear();

        cartListModel.addElement(
                "Items added successfully."
        );

        cartListModel.addElement(
                "--------------------"
        );

        cartListModel.addElement(
                "Total: Rs."
                + cart.calculateTotal()
        );

        totalLabel.setText(
                "Total: Rs."
                + cart.calculateTotal()
        );
    }

    // =====================================================
    // PLACE ORDER
    // =====================================================

    private void placeOrder() {

        if (cart.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Your cart is empty.",
                    "Empty Cart",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Order order =
                new Order(
                        1001,
                        customer.getName(),
                        cart
                );

        orderService.processOrder(order);

        String[] paymentOptions = {
                "Cash on Delivery",
                "UPI",
                "Card"
        };

        int choice =
                JOptionPane.showOptionDialog(
                        this,
                        "Choose payment method:",
                        "Payment",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        paymentOptions,
                        paymentOptions[0]
                );

        if (choice == -1) {
            return;
        }

        PaymentMethod method;

        if (choice == 0) {

            method =
                    PaymentMethod.CASH_ON_DELIVERY;

        } else if (choice == 1) {

            method =
                    PaymentMethod.UPI;

        } else {

            method =
                    PaymentMethod.CARD;
        }

        Payment payment =
                new Payment(
                        5001,
                        cart.calculateTotal(),
                        method
                );

        payment.processPayment();

        if (payment.isSuccessful()) {

            orderService.updateOrderStatus(
                    order,
                    OrderStatus.DELIVERED
            );

            Main.saveOrderToFile(
                    order,
                    payment
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Order placed successfully!\n\n"
                    + "Order ID: "
                    + order.getOrderId()
                    + "\n"
                    + "Amount: Rs."
                    + cart.calculateTotal()
                    + "\n"
                    + "Payment: "
                    + method
                    + "\n"
                    + "Status: DELIVERED",
                    "Order Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            cart = new Cart();

            showCustomerScreen();
        }
    }

    // =====================================================
    // PREVIOUS ORDERS
    // =====================================================

    private void showPreviousOrders() {

        ArrayList<String> orders =
                new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "orders.txt"
                            )
                    );

            String line;

            StringBuilder currentOrder =
                    new StringBuilder();

            while ((line =
                    reader.readLine()) != null) {

                if (line.startsWith(
                        "Order ID:"
                )
                        && currentOrder.length() > 0) {

                    orders.add(
                            currentOrder.toString()
                    );

                    currentOrder.setLength(0);
                }

                currentOrder.append(line);
                currentOrder.append("\n");

                if (line.startsWith(
                        "----------------"
                )) {

                    orders.add(
                            currentOrder.toString()
                    );

                    currentOrder.setLength(0);
                }
            }

            if (currentOrder.length() > 0) {

                orders.add(
                        currentOrder.toString()
                );
            }

            reader.close();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "No previous orders found.",
                    "Order History",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        if (orders.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No previous orders found.",
                    "Order History",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        JDialog dialog =
                new JDialog(
                        this,
                        "Previous Orders",
                        true
                );

        dialog.setSize(700, 500);

        dialog.setLocationRelativeTo(this);

        JPanel panel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        JTextArea orderArea =
                new JTextArea();

        orderArea.setEditable(false);

        orderArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        for (String order : orders) {

            orderArea.append(order);

            orderArea.append("\n");
        }

        panel.add(
                new JScrollPane(orderArea),
                BorderLayout.CENTER
        );

        JButton trackButton =
                new JButton(
                        "Track Latest Order"
                );

        trackButton.addActionListener(
                e -> trackLatestOrder()
        );

        panel.add(
                trackButton,
                BorderLayout.SOUTH
        );

        dialog.add(panel);

        dialog.setVisible(true);
    }

    // =====================================================
    // DELIVERY TRACKING
    // =====================================================

    private void trackLatestOrder() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(
                                    "orders.txt"
                            )
                    );

            String line;

            String latestUpdates = null;

            while ((line =
                    reader.readLine()) != null) {

                if (line.startsWith(
                        "Delivery Updates:"
                )) {

                    latestUpdates = line;
                }
            }

            reader.close();

            if (latestUpdates == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No delivery information available.",
                        "Delivery Tracking",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            String updates =
                    latestUpdates.substring(
                            "Delivery Updates: ".length()
                    );

            String[] statuses =
                    updates.split(" -> ");

            StringBuilder message =
                    new StringBuilder();

            message.append(
                    "===== DELIVERY TRACKING =====\n\n"
            );

            for (int i = 0;
                 i < statuses.length;
                 i++) {

                message.append(
                        (i + 1)
                        + ". "
                        + formatStatus(
                                statuses[i]
                        )
                        + "\n"
                );
            }

            message.append(
                    "\nCurrent Status: "
                    + formatStatus(
                            statuses[
                                    statuses.length - 1
                            ]
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    message.toString(),
                    "Delivery Updates",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to read delivery information.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private String formatStatus(
            String status) {

        return status.replace(
                "_",
                " "
        );
    }

    // =====================================================
    // CUSTOMER LOGOUT
    // =====================================================

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            cart = new Cart();

            emailField.setText("");

            passwordField.setText("");

            showLoginScreen();
        }
    }

    // =====================================================
    // ADMIN DASHBOARD
    // =====================================================

    private void showAdminScreen() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JLabel title =
                new JLabel(
                        "ADMIN DASHBOARD",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        JTextArea details =
                new JTextArea();

        details.setEditable(false);

        details.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        15
                )
        );

        updateAdminDetails(details);

        panel.add(
                new JScrollPane(details),
                BorderLayout.CENTER
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout()
                );

        JButton addFoodButton =
                new JButton(
                        "Add Food Item"
                );

        JButton refreshButton =
                new JButton(
                        "Refresh Menu"
                );

        JButton logoutButton =
                new JButton(
                        "Logout"
                );

        buttonPanel.add(
                addFoodButton
        );

        buttonPanel.add(
                refreshButton
        );

        buttonPanel.add(
                logoutButton
        );

        panel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        addFoodButton.addActionListener(
                e -> addFoodItemFromAdmin(details)
        );

        refreshButton.addActionListener(
                e -> updateAdminDetails(details)
        );

        logoutButton.addActionListener(
                e -> {

                    emailField.setText("");

                    passwordField.setText("");

                    showLoginScreen();
                }
        );

        setContentPane(panel);

        revalidate();
        repaint();
    }

    // =====================================================
    // ADMIN MENU DISPLAY
    // =====================================================

    private void updateAdminDetails(
            JTextArea details) {

        details.setText("");

        details.append(
                "===== ADMIN DETAILS =====\n"
        );

        details.append(
                "Admin ID: "
                + admin.getUserId()
                + "\n"
        );

        details.append(
                "Name: "
                + admin.getName()
                + "\n"
        );

        details.append(
                "Email: "
                + admin.getEmail()
                + "\n\n"
        );

        details.append(
                "===== RESTAURANT =====\n"
        );

        details.append(
                "Name: "
                + restaurant.getName()
                + "\n"
        );

        details.append(
                "Location: "
                + restaurant.getLocation()
                + "\n"
        );

        details.append(
                "Rating: "
                + restaurant.getRating()
                + "\n\n"
        );

        details.append(
                "===== FOOD MENU =====\n"
        );

        for (FoodItem food : foodItems) {

            details.append(
                    food.getFoodId()
                    + " - "
                    + food.getName()
                    + " - Rs."
                    + food.getPrice()
                    + " - "
                    + food.getCategory()
                    + "\n"
            );
        }
    }

    // =====================================================
    // ADMIN ADD FOOD
    // =====================================================

    private void addFoodItemFromAdmin(
            JTextArea details) {

        try {

            String idInput =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Food ID:"
                    );

            if (idInput == null) {
                return;
            }

            int foodId =
                    Integer.parseInt(
                            idInput
                    );

            String name =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Food Name:"
                    );

            if (name == null ||
                    name.trim().isEmpty()) {

                return;
            }

            String priceInput =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Food Price:"
                    );

            if (priceInput == null) {
                return;
            }

            double price =
                    Double.parseDouble(
                            priceInput
                    );

            String category =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Food Category:"
                    );

            if (category == null ||
                    category.trim().isEmpty()) {

                return;
            }

            // Check duplicate ID
            for (FoodItem food : foodItems) {

                if (food.getFoodId() ==
                        foodId) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Food ID already exists.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }
            }

            if (price <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Price must be greater than zero.",
                        "Invalid Price",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            FoodItem newFood =
                    new FoodItem(
                            foodId,
                            name,
                            price,
                            category,
                            true
                    );

            restaurantService.addFoodItem(
                    restaurant,
                    newFood
            );

            foodItems =
                    restaurant.getFoodItems();

            updateAdminDetails(details);

            JOptionPane.showMessageDialog(
                    this,
                    name
                    + " was added to the menu successfully!",
                    "Food Added",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numbers for Food ID and Price.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // VALIDATION
    // =====================================================

    private void validateOrder(
            int foodId,
            int quantity)
            throws InvalidOrderException {

        boolean validFood = false;

        for (FoodItem food : foodItems) {

            if (food.getFoodId() ==
                    foodId) {

                validFood = true;

                break;
            }
        }

        if (!validFood) {

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

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    FoodOrderingGUI gui =
                            new FoodOrderingGUI();

                    gui.setVisible(true);
                }
        );
    }
}