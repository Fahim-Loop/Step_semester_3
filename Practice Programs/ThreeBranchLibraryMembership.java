class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    private int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException();
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    public void borrowBook() {
        booksBorrowed++;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        System.out.println("General Member | Books Borrowed: " +
                booksBorrowed);
    }
}

class StudentMember extends LibraryMember {
    protected String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student Member | Course: " + course +
                " | Books Borrowed: " + getBooksBorrowed());
    }
}

class HonorsStudentMember extends StudentMember {
    private int bonusLimit;

    public HonorsStudentMember(String memberId, int borrowLimit,
                               String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public void displayInfo() {
        System.out.println("Honors Student Member | Course: " + course +
                " | Bonus Limit: " + bonusLimit +
                " | Books Borrowed: " + getBooksBorrowed());
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(String memberId, int borrowLimit,
                         String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    public void displayInfo() {
        System.out.println("Faculty Member | Department: " + department +
                " | Books Borrowed: " + getBooksBorrowed());
    }
}

public class ThreeBranchLibraryMembership {

    static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }

        return "General or Student Member";
    }

    static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;

        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
        }

        return total;
    }

    public static void main(String[] args) {
        LibraryMember member1 =
                new LibraryMember("STU1", 3);

        StudentMember member2 =
                new StudentMember("STU2", 3, "CSE");

        HonorsStudentMember member3 =
                new HonorsStudentMember("STU3", 3, "ECE", 2);

        FacultyMember member4 =
                new FacultyMember("STU4", 5, "Physics");

        member1.displayInfo();
        member2.displayInfo();
        member3.displayInfo();
        member4.displayInfo();

        System.out.println(classifyGeneration(member3));
        System.out.println(classifyGeneration(member4));

        member2.borrowBook();
        member2.borrowBook();

        member3.borrowBook();

        member4.borrowBook();
        member4.borrowBook();
        member4.borrowBook();

        System.out.println(
                getTotalBooksBorrowed(
                        new LibraryMember[]{member2, member3, member4}
                )
        );
    }
}