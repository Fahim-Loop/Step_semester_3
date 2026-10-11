
import java.util.*;

public class Marks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> names = new ArrayList<>();
        ArrayList<int[]> marksList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+", 2);
            String name = parts[0];

            String marksString = parts[1]
                    .replace("[", "")
                    .replace("]", "");

            String[] values = marksString.split(",");
            int[] marks = new int[3];

            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(values[i].trim());
            }

            names.add(name);
            marksList.add(marks);
        }

        int n = names.size();
        int[] subjectTotals = new int[3];
        int[] totals = new int[n];

        int topperIndex = 0;

        System.out.print("Totals ");

        for (int i = 0; i < n; i++) {
            int[] marks = marksList.get(i);

            for (int j = 0; j < 3; j++) {
                totals[i] += marks[j];
                subjectTotals[j] += marks[j];
            }

            if (totals[i] > totals[topperIndex]) {
                topperIndex = i;
            }

            if (i > 0) {
                System.out.print(", ");
            }

            System.out.print(names.get(i) + " " + totals[i]);
        }

        System.out.println();

        System.out.printf(
            "averages %.2f, %.2f, %.2f%n",
            subjectTotals[0] / (double) n,
            subjectTotals[1] / (double) n,
            subjectTotals[2] / (double) n
        );

        System.out.println(
            "topper " + names.get(topperIndex)
            + " (" + totals[topperIndex] + ")"
        );

        sc.close();
    }
}