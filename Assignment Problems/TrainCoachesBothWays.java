
import java.util.Scanner;

class Node {
    String name;
    Node prev;
    Node next;

    Node(String name) {
        this.name = name;
        this.prev = null;
        this.next = null;
    }
}

public class TrainCoachesBothWays {
    Node head;
    Node tail;

    void addLast(String name) {
        Node newNode = new Node(name);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    void remove(String name) {
        Node current = head;

        while (current != null && !current.name.equals(name)) {
            current = current.next;
        }

        if (current == null) {
            return;
        }

        if (current == head) {
            head = current.next;
        } else {
            current.prev.next = current.next;
        }

        if (current == tail) {
            tail = current.prev;
        } else {
            current.next.prev = current.prev;
        }
    }

    void printForward() {
        System.out.print("Forward: ");

        Node current = head;

        while (current != null) {
            System.out.print(current.name);
            if (current.next != null) {
                System.out.print(" <-> ");
            }
            current = current.next;
        }

        System.out.println();
    }

    void printBackward() {
        System.out.print("Backward: ");

        Node current = tail;

        while (current != null) {
            System.out.print(current.name);
            if (current.prev != null) {
                System.out.print(" <-> ");
            }
            current = current.prev;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TrainCoachesBothWays train = new TrainCoachesBothWays();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            train.addLast(sc.next());
        }

        String coachToRemove = sc.next();

        train.remove(coachToRemove);

        train.printForward();
        train.printBackward();

        sc.close();
    }
}