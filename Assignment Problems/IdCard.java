public class IdCard {

    String name;
    int booksIssued;

    public IdCard(String name, int booksIssued) {

        this.name = name;
        this.booksIssued = booksIssued;
    }

    public static void main(String[] args) {

        IdCard ravi =
            new IdCard("Ravi", 0);

        // Copy the reference
        IdCard duplicate = ravi;

        // Change through second reference
        duplicate.booksIssued = 3;

        // Create separate object
        IdCard separate =
            new IdCard("Ravi", 3);

        System.out.println(
            "Ravi's booksIssued (via first variable): "
            + ravi.booksIssued
        );

        System.out.println(
            "duplicate == ravi: "
            + (duplicate == ravi)
        );

        System.out.println(
            "separate == ravi: "
            + (separate == ravi)
        );
    }
}