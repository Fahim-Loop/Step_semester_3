public class PayrollEmployee {

    String id;
    double salary;

    // Constructor
    public PayrollEmployee(
            String id,
            double salary) {

        this.id = id;
        this.salary = salary;
    }

    // Field and parameter have same name
    void raiseSalary(double salary) {

        this.salary = this.salary + salary;
    }

    void printSalary() {

        System.out.println(
            id + " | Final Salary: Rs "
            + salary
        );
    }

    public static void main(String[] args) {

        PayrollEmployee[] employees = {

            new PayrollEmployee("E-101", 40000),
            new PayrollEmployee("E-102", 55000),
            new PayrollEmployee("E-103", 62000),
            new PayrollEmployee("E-104", 48000)
        };

        double bonus = 5000;

        // One pass
        for (int i = 0; i < employees.length; i++) {

            employees[i].raiseSalary(bonus);

            employees[i].printSalary();
        }
    }
}