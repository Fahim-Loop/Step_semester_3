class Payment {

    void pay(double amount) {
        System.out.println("Paid (cash): Rs " + amount);
    }
}

class CardPayment extends Payment {

    void payWithProcessingFee(double amount) {

        double total = amount + (amount * 0.02);

        System.out.println(
            "Charged (card, incl. fee): Rs " + total
        );
    }
}

public class CanteenPaymentDispatch {

    double totalCollected = 0;

    void processTransaction(Payment payment, double amount) {

        if (payment instanceof CardPayment) {

            CardPayment cardPayment =
                (CardPayment) payment;

            cardPayment.payWithProcessingFee(amount);

            totalCollected =
                totalCollected + (amount + amount * 0.02);

        } else {

            payment.pay(amount);

            totalCollected =
                totalCollected + amount;
        }
    }

    public static void main(String[] args) {

        CanteenPaymentDispatch processor =
            new CanteenPaymentDispatch();

        // 3 CardPayment and 2 Payment objects
        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = {
            100,
            50,
            200,
            75,
            120
        };

        // Process every transaction
        for (int i = 0; i < payments.length; i++) {

            processor.processTransaction(
                payments[i],
                amounts[i]
            );
        }

        System.out.println(
            "Total Collected: Rs "
            + processor.totalCollected
        );
    }
}