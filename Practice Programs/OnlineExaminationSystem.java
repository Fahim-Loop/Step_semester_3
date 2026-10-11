import java.util.*;

abstract class Question {
    String questionId;
    int points;

    Question(String questionId, int points) {
        this.questionId = questionId;
        this.points = points;
    }

    abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    String correctAnswer;

    MultipleChoiceQuestion(String id, int points, String answer) {
        super(id, points);
        correctAnswer = answer;
    }

    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    String correctAnswer;

    TrueFalseQuestion(String id, int points, String answer) {
        super(id, points);
        correctAnswer = answer;
    }

    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class ShortAnswerQuestion extends Question {
    String correctAnswer;

    ShortAnswerQuestion(String id, int points, String answer) {
        super(id, points);
        correctAnswer = answer;
    }

    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Examination {
    String name;
    List<Question> questions = new ArrayList<>();

    Examination(String name) {
        this.name = name;
    }

    void addQuestion(Question q) {
        questions.add(q);
    }
}

class Attempt {
    String student;
    Examination exam;
    Map<Question, String> answers = new LinkedHashMap<>();
    private boolean submitted = false;

    Attempt(String student, Examination exam) {
        this.student = student;
        this.exam = exam;
        System.out.println(exam.name + " started by " + student + ".");
    }

    void answer(Question q, String answer) {
        if (submitted) {
            System.out.println(
                "Cannot change answers for a submitted examination.");
            return;
        }

        if (exam.questions.contains(q)) {
            answers.put(q, answer);
            System.out.println("Answer recorded for " + q.questionId + ".");
        }
    }

    void submit() {
        if (submitted) {
            System.out.println("Examination already submitted.");
            return;
        }

        submitted = true;
        System.out.println(exam.name + " submitted by " + student + ".");

        int score = 0;
        int total = 0;

        for (Question q : exam.questions) {
            total += q.points;
            String ans = answers.getOrDefault(q, "");
            boolean correct = q.evaluate(ans);

            if (correct) {
                score += q.points;
            }

            System.out.println(q.questionId + ": "
                + (correct ? "Correct (" + q.points + " points)"
                           : "Incorrect (0 points)"));
        }

        System.out.println("Total score: " + score + "/" + total + ".");
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Examination exam = new Examination("Exam A");

        Question q1 = new MultipleChoiceQuestion("Question 1", 5, "C");
        Question q2 = new TrueFalseQuestion("Question 2", 5, "False");

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        Attempt attempt = new Attempt("Student 1", exam);

        attempt.answer(q1, "C");
        attempt.answer(q2, "True");
        attempt.submit();
        attempt.answer(q1, "A");
    }
}