abstract class WashCycle {
    abstract int getDuration();
    abstract double getCharge();
    abstract String getType();
}

class QuickWash extends WashCycle {
    int getDuration() { return 30; }
    double getCharge() { return 20; }
    String getType() { return "Quick"; }
}

class NormalWash extends WashCycle {
    int getDuration() { return 45; }
    double getCharge() { return 30; }
    String getType() { return "Normal"; }
}

class HeavyWash extends WashCycle {
    int getDuration() { return 60; }
    double getCharge() { return 45; }
    String getType() { return "Heavy"; }
}

class WashingMachine {
    private String id;
    private boolean busy = false;

    WashingMachine(String id) {
        this.id = id;
    }

    boolean isBusy() {
        return busy;
    }

    void startWash(String student, WashCycle wash) {
        if (busy) {
            System.out.println("Machine " + id + " is currently busy.");
        } else {
            busy = true;
            System.out.printf(
                "%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                wash.getType(), id, student,
                wash.getDuration(), wash.getCharge()
            );
        }
    }

    void completeWash() {
        if (busy) {
            busy = false;
            System.out.println(id + " cycle completed.");
            System.out.println(id + " is now free.");
        }
    }
}

public class HostelLaundryQueue {
    public static void main(String[] args) {
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.startWash("Asha", new QuickWash());
        m1.startWash("Ravi", new HeavyWash());
        m2.startWash("Ravi", new HeavyWash());

        m1.completeWash();
        m1.startWash("Neha", new NormalWash());
    }
}