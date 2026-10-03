public class Participant {

    String name;
    String teamName;
    boolean registered;

    // Constructor with name and team name
    public Participant(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.registered = true;
    }

    // Constructor for solo participant
    public Participant(String name) {
        this(name, "Unassigned");
    }

    // Print participant status
    public void printStatus() {
        System.out.println(name + " | " + teamName
                + " | Registered: " + registered);
    }

    public static void main(String[] args) {

        String[] names = {
            "Ravi",
            "Meera",
            "Karthik",
            "Divya"
        };

        String[] teamNames = {
            "ByteBusters",
            "",
            "CodeCrafters",
            ""
        };

        // Process every participant
        for (int i = 0; i < names.length; i++) {

            Participant participant;

            if (teamNames[i].equals("")) {
                participant = new Participant(names[i]);
            } else {
                participant = new Participant(names[i], teamNames[i]);
            }

            participant.printStatus();
        }
    }
}xx