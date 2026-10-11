
import java.util.*;

interface Chargeable {
    void charge();
}

abstract class Vehicle {
    String passNumber;
    String owner;

    Vehicle(String passNumber, String owner) {
        this.passNumber = passNumber;
        this.owner = owner;
    }

    abstract int getPassFee();

    abstract String getType();
}

class Bike extends Vehicle {
    Bike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 300;
    }

    String getType() {
        return "Bike";
    }
}

class Car extends Vehicle {
    Car(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 1000;
    }

    String getType() {
        return "Car";
    }
}

class EBike extends Vehicle implements Chargeable {
    EBike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 300;
    }

    String getType() {
        return "EBike";
    }

    public void charge() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

class ECar extends Vehicle implements Chargeable {
    ECar(String passNumber, String owner) {
        super(passNumber, owner);
    }

    int getPassFee() {
        return 1000;
    }

    String getType() {
        return "ECar";
    }

    public void charge() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("PASS")) {
                String type = sc.next();
                String passNumber = sc.next();
                String owner = sc.next();

                Vehicle vehicle = null;

                switch (type) {
                    case "Bike":
                        vehicle = new Bike(passNumber, owner);
                        break;
                    case "Car":
                        vehicle = new Car(passNumber, owner);
                        break;
                    case "EBike":
                        vehicle = new EBike(passNumber, owner);
                        break;
                    case "ECar":
                        vehicle = new ECar(passNumber, owner);
                        break;
                }

                if (vehicle != null) {
                    vehicles.put(passNumber, vehicle);
                    System.out.println(passNumber + " (" 
                        + vehicle.getType() + ") pass fee " 
                        + vehicle.getPassFee());
                }

            } else if (operation.equals("CHARGE")) {
                String passNumber = sc.next();
                Vehicle vehicle = vehicles.get(passNumber);

                if (vehicle == null) {
                    System.out.println("Vehicle not found");
                } else if (vehicle instanceof Chargeable) {
                    ((Chargeable) vehicle).charge();
                } else {
                    System.out.println(passNumber 
                        + " rejected: charging unsupported");
                }
            }
        }

        sc.close();
    }
}