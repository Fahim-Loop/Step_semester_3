import java.time.LocalDate;
import java.util.*;

abstract class Room {
    String roomNumber;

    Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    abstract double calculatePrice(int days);
}

class StandardRoom extends Room {
    StandardRoom(String number) {
        super(number);
    }

    double calculatePrice(int days) {
        return days * 100;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(String number) {
        super(number);
    }

    double calculatePrice(int days) {
        return days * 200;
    }
}

class Suite extends Room {
    Suite(String number) {
        super(number);
    }

    double calculatePrice(int days) {
        return days * 350;
    }
}

class Reservation {
    String customer;
    Room room;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate cancellationDeadline;
    boolean cancelled = false;

    Reservation(String customer, Room room,
                LocalDate start, LocalDate end,
                LocalDate deadline) {
        this.customer = customer;
        this.room = room;
        this.startDate = start;
        this.endDate = end;
        this.cancellationDeadline = deadline;
    }

    boolean overlaps(LocalDate start, LocalDate end) {
        return !cancelled
            && start.isBefore(endDate)
            && end.isAfter(startDate);
    }

    void cancel(LocalDate today) {
        if (cancelled) {
            System.out.println("Reservation already cancelled.");
        } else if (today.isAfter(cancellationDeadline)) {
            System.out.println("Cancellation deadline has passed.");
        } else {
            cancelled = true;
            System.out.println("Reservation for " + customer + ", "
                + room.getClass().getSimpleName() + " "
                + room.roomNumber + " (" + startDate + "-" + endDate
                + ") cancelled successfully.");
        }
    }
}

class Hotel {
    List<Reservation> reservations = new ArrayList<>();

    boolean isAvailable(Room room, LocalDate start, LocalDate end) {
        for (Reservation r : reservations) {
            if (r.room.roomNumber.equals(room.roomNumber)
                && r.overlaps(start, end)) {
                return false;
            }
        }
        return true;
    }

    void book(String customer, Room room,
              LocalDate start, LocalDate end,
              LocalDate deadline) {
        if (!start.isBefore(end)) {
            System.out.println("Invalid reservation dates.");
            return;
        }

        if (!isAvailable(room, start, end)) {
            System.out.println(room.getClass().getSimpleName()
                + " " + room.roomNumber
                + " is not available from " + start + " to " + end + ".");
            return;
        }

        Reservation r = new Reservation(
            customer, room, start, end, deadline);
        reservations.add(r);

        int days = (int) java.time.temporal.ChronoUnit.DAYS
            .between(start, end);

        System.out.println("Reservation confirmed for " + customer
            + ", " + room.getClass().getSimpleName() + " "
            + room.roomNumber + " (" + start + "-" + end + ").");

        System.out.printf("Price: $%.2f.%n",
            room.calculatePrice(days));
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        Room standard = new StandardRoom("101");
        Room deluxe = new DeluxeRoom("201");

        LocalDate start1 = LocalDate.of(2026, 1, 1);
        LocalDate end1 = LocalDate.of(2026, 1, 5);

        if (hotel.isAvailable(standard, start1, end1)) {
            System.out.println("Standard Room 101 is available from "
                + start1 + " to " + end1 + ".");
        }

        hotel.book("Customer A", standard, start1, end1,
            LocalDate.of(2025, 12, 25));

        hotel.book("Customer B", standard,
            LocalDate.of(2026, 1, 3),
            LocalDate.of(2026, 1, 7),
            LocalDate.of(2025, 12, 25));

        Reservation first = hotel.reservations.get(0);
        first.cancel(LocalDate.of(2025, 12, 20));

        hotel.book("Customer C", deluxe,
            LocalDate.of(2026, 2, 10),
            LocalDate.of(2026, 2, 12),
            LocalDate.of(2026, 2, 5));
    }
}