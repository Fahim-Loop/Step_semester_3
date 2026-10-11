import java.util.*;

abstract class Seat {
    String seatId;

    Seat(String seatId) {
        this.seatId = seatId;
    }

    abstract double getPrice();
}

class RegularSeat extends Seat {
    RegularSeat(String id) { super(id); }
    double getPrice() { return 150; }
}

class PremiumSeat extends Seat {
    PremiumSeat(String id) { super(id); }
    double getPrice() { return 250; }
}

class ReclinerSeat extends Seat {
    ReclinerSeat(String id) { super(id); }
    double getPrice() { return 400; }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Show {
    private Set<String> bookedSeats = new HashSet<>();
    boolean started = false;

    boolean bookSeat(Seat seat) {
        if (bookedSeats.contains(seat.seatId)) {
            System.out.println("Seat " + seat.seatId
                + " is already booked for this show.");
            return false;
        }

        bookedSeats.add(seat.seatId);
        return true;
    }

    void releaseSeat(Seat seat) {
        bookedSeats.remove(seat.seatId);
    }
}

class Booking {
    Customer customer;
    Show show;
    List<Seat> seats = new ArrayList<>();
    boolean cancelled = false;

    Booking(Customer customer, Show show, Seat... selectedSeats) {
        this.customer = customer;
        this.show = show;

        if (selectedSeats.length == 0 || selectedSeats.length > 6) {
            System.out.println("Booking must contain 1 to 6 seats.");
            cancelled = true;
            return;
        }

        for (Seat seat : selectedSeats) {
            if (!show.bookSeat(seat)) {
                for (Seat booked : seats) {
                    show.releaseSeat(booked);
                }
                seats.clear();
                cancelled = true;
                return;
            }
            seats.add(seat);
        }

        double total = 0;
        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        StringJoiner ids = new StringJoiner(", ");
        for (Seat seat : seats) {
            ids.add(seat.seatId);
        }

        System.out.println("Booking confirmed for "
            + customer.name + ": " + ids
            + ". Total: ₹" + String.format("%.2f", total) + ".");
    }

    void cancel() {
        if (!cancelled && !show.started) {
            for (Seat seat : seats) {
                show.releaseSeat(seat);
            }

            cancelled = true;
            StringJoiner ids = new StringJoiner(", ");
            for (Seat seat : seats) {
                ids.add(seat.seatId);
            }

            System.out.println(customer.name + "'s booking cancelled.");
            System.out.println("Seats " + ids + " released.");
        } else {
            System.out.println("Booking cannot be cancelled.");
        }
    }
}

public class CampusPremiereTicketCounter {
    public static void main(String[] args) {
        Show show = new Show();

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking b1 = new Booking(asha, show,
            new RegularSeat("A1"),
            new RegularSeat("A2"),
            new PremiumSeat("F5"));

        Booking b2 = new Booking(ravi, show,
            new RegularSeat("A2"));

        Booking b3 = new Booking(ravi, show,
            new ReclinerSeat("R1"));

        b1.cancel();

        Booking b4 = new Booking(neha, show,
            new RegularSeat("A2"));
    }
}