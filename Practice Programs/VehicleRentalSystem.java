abstract class Vehicle {
    String vehicleId;
    private boolean rented = false;

    Vehicle(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    abstract double calculateCharge(int days);

    boolean isRented() {
        return rented;
    }

    void rent() {
        rented = true;
    }

    void returnVehicle() {
        rented = false;
    }
}

class Sedan extends Vehicle {
    Sedan(String id) {
        super(id);
    }

    double calculateCharge(int days) {
        return days * 50.0;
    }
}

class SUV extends Vehicle {
    SUV(String id) {
        super(id);
    }

    double calculateCharge(int days) {
        return days * 80.0;
    }
}

class Truck extends Vehicle {
    Truck(String id) {
        super(id);
    }

    double calculateCharge(int days) {
        return days * 120.0;
    }
}

class Rental {
    String customer;
    Vehicle vehicle;
    int days;

    Rental(String customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
    }

    void start() {
        if (vehicle.isRented()) {
            System.out.println(vehicle.vehicleId
                + " is currently unavailable.");
        } else {
            vehicle.rent();
            System.out.println(vehicle.vehicleId
                + " rented successfully by " + customer + ".");
            System.out.printf("Rental charge: $%.2f.%n",
                vehicle.calculateCharge(days));
        }
    }

    void returnVehicle() {
        if (vehicle.isRented()) {
            vehicle.returnVehicle();
            System.out.println(vehicle.vehicleId
                + " returned by " + customer + ".");
        }
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");

        Rental r1 = new Rental("Customer 1", sedan, 3);
        Rental r2 = new Rental("Customer 2", sedan, 2);
        Rental r3 = new Rental("Customer 3", suv, 5);

        r1.start();
        r2.start();
        r1.returnVehicle();
        r3.start();
    }
}