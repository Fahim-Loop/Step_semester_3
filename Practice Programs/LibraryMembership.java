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

    static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;

        for (String id : memberIds) {
            try {
                new LibraryMember(id, borrowLimit);
                enrolled++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }
}

class StudentMember extends LibraryMember {
    private String course;

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

public class LibraryMembership {
    public static void main(String[] args) {
        StudentMember s =
                new StudentMember("STU10", 3, "CSE");

        s.borrowBook();
        s.borrowBook();

        System.out.println(s.getBooksBorrowed());

        System.out.println(
                LibraryMember.enrollBatch(
                        new String[]{"STU1", "LB1", "STU2", " ", "STU3"},
                        3
                )
        );
    }
}
