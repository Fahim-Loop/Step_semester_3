public class ReferenceDeskSubclassReach {

    static String classifyAccess(String modifier, String context) {

        if (context.equals("SAME_CLASS"))
            return "ALLOWED";

        if (context.equals("SAME_PACKAGE")) {
            if (modifier.equals("private"))
                return "DENIED";
            return "ALLOWED";
        }

        if (context.equals("DIFFERENT_PACKAGE")) {
            if (modifier.equals("public"))
                return "ALLOWED";
            return "DENIED";
        }

        if (context.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
            if (modifier.equals("protected") ||
                modifier.equals("public"))
                return "ALLOWED";
            return "DENIED";
        }

        if (context.equals("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {
            if (modifier.equals("public"))
                return "ALLOWED";
            return "DENIED";
        }

        return "DENIED";
    }

    static String firstDeniedAttempt(String[][] attempts) {

        for (int i = 0; i < attempts.length; i++) {

            String result =
                classifyAccess(attempts[i][0], attempts[i][1]);

            if (result.equals("DENIED")) {
                return attempts[i][0] + " via "
                     + attempts[i][1]
                     + " (attempt #" + (i + 1) + ")";
            }
        }

        return "None Denied";
    }

    public static void main(String[] args) {

        String[][] attempts = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println(firstDeniedAttempt(attempts));
    }
}
