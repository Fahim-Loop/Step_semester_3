
import java.util.*;

abstract class Staff {
    String name;

    Staff(String name) {
        this.name = name;
    }

    abstract int calculatePay();

    abstract String getType();

    Payslip getPayslip() {
        return new Payslip(name, getType(), calculatePay());
    }
}

class FullTime extends Staff {
    int salary;

    FullTime(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    int calculatePay() {
        return salary;
    }

    String getType() {
        return "FullTime";
    }
}

class PartTime extends Staff {
    int hours;
    int rate;

    PartTime(String name, int hours, int rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    int calculatePay() {
        return hours * rate;
    }

    String getType() {
        return "PartTime";
    }
}

class Intern extends Staff {
    int stipend;

    Intern(String name, int stipend) {
        super(name);
        this.stipend = stipend;
    }

    int calculatePay() {
        return stipend;
    }

    String getType() {
        return "Intern";
    }
}

class Payslip {
    String name;
    String type;
    int pay;

    Payslip(String name, String type, int pay) {
        this.name = name;
        this.type = type;
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "Payslip[name=" + name
                + ", type=" + type
                + ", pay=" + pay + "]";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Payslip> payslips = new ArrayList<>();

        while (sc.hasNext()) {
            String type = sc.next();
            String name = sc.next();

            Staff staff = null;

            switch (type) {
                case "FullTime":
                    int salary = sc.nextInt();
                    staff = new FullTime(name, salary);
                    break;

                case "PartTime":
                    int hours = sc.nextInt();
                    int rate = sc.nextInt();
                    staff = new PartTime(name, hours, rate);
                    break;

                case "Intern":
                    int stipend = sc.nextInt();
                    staff = new Intern(name, stipend);
                    break;
            }

            if (staff != null) {
                payslips.add(staff.getPayslip());
            }
        }

        int total = 0;
        Payslip topEarner = null;

        for (Payslip payslip : payslips) {
            System.out.println(payslip);

            total += payslip.pay;

            if (topEarner == null || payslip.pay > topEarner.pay) {
                topEarner = payslip;
            }
        }

        System.out.println("Total " + total);

        if (topEarner != null) {
            System.out.println("top earner " + topEarner.name);
        }

        sc.close();
    }
}