import java.util.Scanner;
import java.util.HashMap;

public class FirstNonRepeatingCharacter {

    static char findFirstNonRepeatingChar(String text) {

        HashMap<Character, Integer> frequency =
            new HashMap<>();

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (frequency.containsKey(ch)) {
                frequency.put(ch, frequency.get(ch) + 1);
            } else {
                frequency.put(ch, 1);
            }
        }

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (frequency.get(ch) == 1) {
                return ch;
            }
        }

        return '\0';
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        char result = findFirstNonRepeatingChar(text);

        if (result == '\0') {
            System.out.println(
                "No Non-Repeating Character Found"
            );
        } else {
            System.out.println(
                "First Non-Repeating Character: '"
                + result + "'"
            );
        }

        sc.close();
    }
}