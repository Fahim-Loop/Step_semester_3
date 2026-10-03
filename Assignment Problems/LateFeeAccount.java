public class LateFeeAccount {

    String regNo;
    double totalFee;

    public LateFeeAccount(
            String regNo,
            double totalFee) {

        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    // Final method
    final double calculateLateFee(int daysLate) {

        return (totalFee * daysLate) / 100;
    }

    // Final method
    final void printSummary(int daysLate) {

        if (daysLate <= 0) {

            System.out.println(
                regNo + " - On time, no late fee"
            );

        } else {

            double fee =
                calculateLateFee(daysLate);

            System.out.println(
                regNo
                + " | Total Fee: Rs "
                + totalFee
                + " | Late Fee: Rs "
                + fee
            );
        }
    }

    public static void main(String[] args) {

        String[] regNos = {
            "RA001",
            "RA002",
            "RA003",
            "RA004"
        };

        double[] totalFees = {
            200000,
            150000,
            180000,
            220000
        };

        int[] daysLate = {
            10,
            0,
            -2,
            5
        };

        for (int i = 0; i < regNos.length; i++) {

            LateFeeAccount account =
                new LateFeeAccount(
                    regNos[i],
                    totalFees[i]
                );

            account.printSummary(daysLate[i]);
        }
    }
}