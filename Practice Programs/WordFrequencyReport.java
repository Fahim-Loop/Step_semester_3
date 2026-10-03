import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class WordFrequencyReport {

    static void printFilteredWordFrequency(String feedback) {

        // Convert to lowercase
        String text = feedback.toLowerCase();

        // Remove punctuation
        text = text.replace(".", "");
        text = text.replace(",", "");

        // Split into words
        String[] words = text.split("\\s+");

        // Stop words
        String[] stopWords = {
            "the", "was", "and", "a", "is", "of", "in"
        };

        HashMap<String, Integer> frequency = new HashMap<>();

        // Count words
        for (int i = 0; i < words.length; i++) {

            boolean isStopWord = false;

            // Check whether the word is a stop word
            for (int j = 0; j < stopWords.length; j++) {

                if (words[i].equals(stopWords[j])) {
                    isStopWord = true;
                    break;
                }
            }

            // Skip stop words
            if (isStopWord) {
                continue;
            }

            // Add word to HashMap
            if (frequency.containsKey(words[i])) {
                frequency.put(
                    words[i],
                    frequency.get(words[i]) + 1
                );
            } else {
                frequency.put(words[i], 1);
            }
        }

        // Convert HashMap entries into an array
        Map.Entry<String, Integer>[] entries =
            frequency.entrySet().toArray(
                new Map.Entry[0]
            );

        // Sort by frequency in descending order
        for (int i = 0; i < entries.length - 1; i++) {

            for (int j = i + 1; j < entries.length; j++) {

                if (entries[j].getValue() > entries[i].getValue()) {

                    Map.Entry<String, Integer> temp = entries[i];
                    entries[i] = entries[j];
                    entries[j] = temp;
                }
            }
        }

        // Print results
        for (int i = 0; i < entries.length; i++) {

            System.out.println(
                entries[i].getKey()
                + ": "
                + entries[i].getValue()
            );
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter feedback: ");
        String feedback = sc.nextLine();

        printFilteredWordFrequency(feedback);

        sc.close();
    }
}