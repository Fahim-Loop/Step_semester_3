class GymMember {
    protected String memberId;
    protected int monthlyFee;
    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException();
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println("Standard Member | Sessions: " + sessionsAttended);
    }
}

class PremiumMember extends GymMember {
    protected String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public void displayInfo() {
        System.out.println("Premium Member | Trainer: " + trainerName +
                " | Sessions: " + getSessionsAttended());
    }
}

class EliteMember extends PremiumMember {
    private String lockerNumber;

    public EliteMember(String memberId, int monthlyFee, String trainerName,
                       String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    @Override
    public void displayInfo() {
        System.out.println("Elite Member | Trainer: " + trainerName +
                " | Locker: " + lockerNumber +
                " | Sessions: " + getSessionsAttended());
    }
}

class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    public void displayInfo() {
        System.out.println("Group Class Member | Class: " + className +
                " | Sessions: " + getSessionsAttended());
    }
}

public class ThreeTierGymMembership {

    static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        }

        return "Standard or Premium Member";
    }

    static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;

        for (GymMember member : members) {
            total += member.getSessionsAttended();
        }

        return total;
    }

    public static void main(String[] args) {
        GymMember member1 = new GymMember("MEM1", 1000);
        PremiumMember member2 = new PremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember member3 = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember member4 = new GroupClassMember("MEM4", 1500, "Zumba");

        member1.displayInfo();
        member2.displayInfo();
        member3.displayInfo();
        member4.displayInfo();

        System.out.println(classifyGeneration(member3));
        System.out.println(classifyGeneration(member4));

        member2.attendSession();
        member2.attendSession();
        member2.attendSession();

        member3.attendSession();
        member3.attendSession();

        member4.attendSession();
        member4.attendSession();
        member4.attendSession();
        member4.attendSession();

        System.out.println(
                getTotalSessionsAttended(
                        new GymMember[]{member2, member3, member4}
                )
        );
    }
}