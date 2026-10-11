
import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class PriorityTokenInsertion {
    Node head;
    Node tail;

    void addFirst(int token) {
        Node newNode = new Node(token);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    void addLast(int token) {
        Node newNode = new Node(token);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    void insertAt(int index, int token) {
        if (index == 0) {
            addFirst(token);
            return;
        }

        Node current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        Node newNode = new Node(token);
        newNode.next = current.next;
        current.next = newNode;

        if (newNode.next == null) {
            tail = newNode;
        }
    }

    void printList() {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityTokenInsertion list = new PriorityTokenInsertion();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            list.addLast(sc.nextInt());
        }

        int operations = sc.nextInt();

        for (int i = 0; i < operations; i++) {
            String operation = sc.next();

            switch (operation) {
                case "addFirst":
                    list.addFirst(sc.nextInt());
                    break;

                case "addLast":
                    list.addLast(sc.nextInt());
                    break;

                case "insertAt":
                    int index = sc.nextInt();
                    int token = sc.nextInt();
                    list.insertAt(index, token);
                    break;
            }
        }

        list.printList();
        sc.close();
    }
}