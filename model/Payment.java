package model;

public class Payment {

    private int paymentId;
    private double amount;
    private PaymentMethod method;
    private boolean successful;

    public Payment(int paymentId, double amount,
                   PaymentMethod method) {

        this.paymentId = paymentId;
        this.amount = amount;
        this.method = method;
        this.successful = false;
    }

    public void processPayment() {

        System.out.println("\n========== PAYMENT ==========");
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Amount: Rs." + amount);
        System.out.println("Method: " + method);

        successful = true;

        System.out.println("Payment successful!");
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void printReceipt() {

        System.out.println("\n========== PAYMENT RECEIPT ==========");
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Amount Paid: Rs." + amount);
        System.out.println("Payment Method: " + method);

        System.out.println(
                "Status: " +
                (successful ? "SUCCESSFUL" : "FAILED")
        );
    }
}