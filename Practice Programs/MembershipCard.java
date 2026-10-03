public class MembershipCard {

    static String libraryName;
    static String validUntil;

    String studentName;

    // Static block
    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";

        System.out.println("Library info loaded");
    }

    // Constructor
    public MembershipCard(String studentName) {
        this.studentName = studentName;
    }

    public void printConfirmation() {
        System.out.println(
            "Membership card issued: " + studentName
        );
    }

    public static void main(String[] args) {

        String[] names = {
            "Ananya",
            "Rohan",
            "Priya",
            "Arjun",
            "Sneha"
        };

        // Create cards for all students
        for (int i = 0; i < names.length; i++) {

            MembershipCard card =
                new MembershipCard(names[i]);

            card.printConfirmation();
        }
    }
}