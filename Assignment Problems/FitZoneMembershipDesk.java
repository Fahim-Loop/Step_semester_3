interface MembershipPlan {
    double calculateFee();
    String getName();
}

class MonthlyPlan implements MembershipPlan {
    public double calculateFee() {
        return 1000;
    }

    public String getName() {
        return "Monthly";
    }
}

class QuarterlyPlan implements MembershipPlan {
    public double calculateFee() {
        return 3000 * 0.90;
    }

    public String getName() {
        return "Quarterly";
    }
}

class AnnualPlan implements MembershipPlan {
    public double calculateFee() {
        return 12000 * 0.75;
    }

    public String getName() {
        return "Annual";
    }
}

class Membership {
    private String memberName;
    private MembershipPlan plan;
    private String status = "Active";

    Membership(String memberName, MembershipPlan plan) {
        this.memberName = memberName;
        this.plan = plan;

        System.out.printf("%s membership created for %s. Fee: ₹%.2f. Status: %s.%n",
            plan.getName(), memberName, plan.calculateFee(), status);
    }

    void checkIn() {
        if (status.equals("Active")) {
            System.out.println(memberName + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: " + memberName
                + "'s membership is " + status + ".");
        }
    }

    void freeze() {
        if (status.equals("Active")) {
            status = "Frozen";
            System.out.println(memberName + "'s membership frozen.");
            System.out.println("Status: " + status + ".");
        } else {
            System.out.println("Cannot freeze an " + status
                + " membership.");
        }
    }

    void unfreeze() {
        if (status.equals("Frozen")) {
            status = "Active";
            System.out.println(memberName + "'s membership unfrozen.");
            System.out.println("Status: " + status + ".");
        } else {
            System.out.println("Cannot unfreeze a "
                + status + " membership.");
        }
    }

    void expire() {
        if (!status.equals("Expired")) {
            status = "Expired";
            System.out.println(memberName + "'s membership expired.");
            System.out.println("Status: " + status + ".");
        }
    }
}

public class FitZoneMembershipDesk {
    public static void main(String[] args) {
        Membership asha = new Membership("Asha", new QuarterlyPlan());
        Membership ravi = new Membership("Ravi", new MonthlyPlan());

        asha.checkIn();
        asha.freeze();
        asha.checkIn();

        ravi.expire();
        ravi.freeze();
    }
}