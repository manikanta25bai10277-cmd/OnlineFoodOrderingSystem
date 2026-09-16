# Online Food Ordering System

A Java-based Online Food Ordering System developed using Object-Oriented Programming concepts. The project provides both a Command-Line Interface (CLI) and a Graphical User Interface (GUI) using Java Swing.

## Features

### 1. User Login
- Customer login
- Admin login
- Email and password authentication
- Maximum of 3 login attempts
- Logout functionality

### 2. Customer Features
- View restaurant details
- Browse available food items
- Select food items and quantities
- Add items to cart
- View cart and total amount
- Place orders
- Select payment method
- View previous orders
- Track order delivery status
- Logout

### 3. Admin Features
- Admin authentication
- View admin details
- View restaurant details
- View restaurant menu
- Add new food items
- Refresh the restaurant menu
- Logout

## Demo Login Credentials

The application provides separate login access for customers and administrators.

### Customer Login

- Email: `customer@gmail.com`
- Password: `1234`

### Admin Login

- Email: `admin@gmail.com`
- Password: `admin123`

These credentials are demo credentials provided for testing the application.

### 4. Order Management
Orders follow a delivery workflow:

PLACED → CONFIRMED → PREPARING → OUT_FOR_DELIVERY → DELIVERED

The order status history is maintained to provide delivery updates.

### 5. Payment
The system supports:
- Cash on Delivery
- UPI
- Card

A payment receipt is generated after payment processing.

### 6. Previous Orders
Completed orders are stored in a text file named:

orders.txt

Customers can view previously placed orders through the GUI.

### 7. Delivery Tracking
The system stores the order status history and displays delivery updates such as:

PLACED → CONFIRMED → PREPARING → OUT_FOR_DELIVERY → DELIVERED

### 8. Exception Handling
The project demonstrates Java exception handling using:
- InputMismatchException
- IOException
- Custom InvalidOrderException

Invalid food IDs and invalid quantities are handled without terminating the application.

### 9. File Handling
Order information is stored using Java File I/O.

The project uses:

FileWriter

The generated `orders.txt` file is ignored by Git using `.gitignore`.

### 10. Graphical User Interface
The project includes a Java Swing GUI with:

- Login screen
- Customer dashboard
- Admin dashboard
- Food menu
- Cart
- Place order option
- Payment selection
- Previous orders
- Delivery tracking
- Add food item functionality
- Logout

## Technologies Used

- Java
- Java Swing
- Object-Oriented Programming
- Collections
- Exception Handling
- File I/O
- Git
- GitHub

## OOP Concepts Demonstrated

The project demonstrates several core Java OOP concepts.

### Encapsulation
Classes use private data members with public methods for controlled access.

Examples:
- User
- Customer
- Admin
- FoodItem
- Restaurant
- Cart
- Order
- Payment

### Inheritance
Customer and Admin inherit from the User class.

```text
             User
            /    \
      Customer    Admin
