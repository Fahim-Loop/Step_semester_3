class FeeAccount {

    void pay(double amount) {

        System.out.println(
            "Paid in one go (day-scholar account)"
        );
    }
}

class HostelFeeAccount extends FeeAccount {

    @Override
    void pay(double amount) {

        System.out.println(
            "Paid in two installments (hostel account)"
        );
    }
}

public class AccountBatchPayments {

    int hostelCount = 0;
    int dayScholarCount = 0;

    void processPayment(
            FeeAccount account,
            double amount) {

        if (account instanceof HostelFeeAccount) {

            HostelFeeAccount hostel =
                (HostelFeeAccount) account;

            hostel.pay(amount);

            hostelCount++;

        } else {

            account.pay(amount);

            dayScholarCount++;
        }
    }

    public static void main(String[] args) {

        AccountBatchPayments processor =
            new AccountBatchPayments();

        FeeAccount[] accounts = {

            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        double amount = 60000;

        for (int i = 0; i < accounts.length; i++) {

            processor.processPayment(
                accounts[i],
                amount
            );
        }

        System.out.println(
            "Hostel accounts processed: "
            + processor.hostelCount
            + " | Day-scholar accounts processed: "
            + processor.dayScholarCount
        );
    }
}