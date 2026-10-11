abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days > 0 && days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days > 0 && days <= 10;
    }
}

class Contractor extends Employee {
    Contractor(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days > 0 && days <= 5;
    }
}

class LeaveRequest {
    Employee employee;
    String dates;
    private String status = "Pending";

    LeaveRequest(Employee employee, String dates, int days) {
        this.employee = employee;
        this.dates = dates;

        if (employee.canTakeLeave(days)) {
            System.out.println("Leave request submitted for "
                + employee.name + " (" + dates + "). Status: Pending.");
        } else {
            status = "Rejected";
            System.out.println("Leave request denied for "
                + employee.name + ": leave policy limit exceeded.");
        }
    }

    void review(String reviewer, boolean approve) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot review request. Status: " + status);
            return;
        }

        status = approve ? "Approved" : "Rejected";

        System.out.println(employee.name + "'s leave request ("
            + dates + ") " + status.toLowerCase() + ".");
        System.out.println("Status: " + status + ".");
    }

    void changeStatus(String newStatus) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change leave request status from "
                + status + " to " + newStatus + ".");
        } else {
            status = newStatus;
        }
    }
}

public class EmployeeLeaveRequestWorkflow {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest r1 = new LeaveRequest(john, "Jan 1-5", 5);
        r1.review("Alice", true);

        LeaveRequest r2 = new LeaveRequest(jane, "Feb 10-11", 2);
        r2.review("Bob", false);

        r1.changeStatus("Pending");
    }
}