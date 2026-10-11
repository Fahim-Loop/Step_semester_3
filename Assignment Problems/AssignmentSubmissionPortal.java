import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    String title;
    int maxMarks;
    LocalDate dueDate;

    Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    abstract double getDailyPenalty();
}

class CodingAssignment extends Assignment {
    CodingAssignment(String title, int marks, LocalDate date) {
        super(title, marks, date);
    }

    double getDailyPenalty() {
        return 0.10;
    }
}

class WrittenAssignment extends Assignment {
    WrittenAssignment(String title, int marks, LocalDate date) {
        super(title, marks, date);
    }

    double getDailyPenalty() {
        return 0.20;
    }
}

class Submission {
    String student;
    Assignment assignment;
    LocalDate submissionDate;
    private String status = "Submitted";

    Submission(String student, Assignment assignment,
               LocalDate submissionDate) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;

        long daysLate = Math.max(0,
            ChronoUnit.DAYS.between(
                assignment.dueDate, submissionDate
            ));

        System.out.println(student + "'s submission for '"
            + assignment.title + "' received ("
            + (daysLate == 0 ? "on time" : daysLate + " days late")
            + "). Status: " + status + ".");
    }

    void grade(double marks) {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '"
                + assignment.title + "' has already been graded.");
            return;
        }

        long daysLate = Math.max(0,
            ChronoUnit.DAYS.between(
                assignment.dueDate, submissionDate
            ));

        double penalty = assignment.getDailyPenalty() * daysLate;
        double finalMarks = marks * (1 - penalty);
        finalMarks = Math.max(0,
            Math.min(finalMarks, assignment.maxMarks));

        System.out.printf("%s graded: %.0f/%d",
            student, finalMarks, assignment.maxMarks);

        if (daysLate > 0) {
            System.out.printf(" after %.0f%% late penalty",
                penalty * 100);
        }

        System.out.println(".");
        status = "Graded";
        System.out.println("Status: " + status + ".");
    }
}

public class AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Assignment coding = new CodingAssignment(
            "Linked List Lab", 50, LocalDate.of(2026, 3, 10));

        Assignment written = new WrittenAssignment(
            "Design Essay", 50, LocalDate.of(2026, 3, 12));

        Submission s1 = new Submission(
            "Asha", coding, LocalDate.of(2026, 3, 10));

        Submission s2 = new Submission(
            "Ravi", written, LocalDate.of(2026, 3, 14));

        s1.grade(45);
        s2.grade(40);
        s1.grade(45);
    }
}