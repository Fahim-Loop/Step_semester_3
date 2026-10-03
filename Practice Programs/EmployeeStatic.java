public class EmployeeStatic {

    // Instance fields
    String empName;
    double salary;

    // Static fields
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    // Constructor
    public EmployeeStatic(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;

        employeeCount++;
    }

    // Static method
    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {

        // Create three Employee objects
        EmployeeStatic employee1 =
            new EmployeeStatic("Ravi", 50000);

        EmployeeStatic employee2 =
            new EmployeeStatic("Meera", 55000);

        EmployeeStatic employee3 =
            new EmployeeStatic("Karthik", 60000);

        // Call static method using class name
        EmployeeStatic.printCompanyInfo();
    }
}