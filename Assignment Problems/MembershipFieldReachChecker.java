class LibraryMember {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;
}

public class MembershipFieldReachChecker {

    static String classifyAccess(String modifier, String context) {

        if (context.equals("SAME_CLASS"))
            return "ALLOWED";

        if (context.equals("SAME_PACKAGE")) {
            if (modifier.equals("private"))
                return "DENIED";
            else
                return "ALLOWED";
        }

        if (context.equals("DIFFERENT_PACKAGE")) {
            if (modifier.equals("public"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        int pa = 0, pd = 0;
        int da = 0, dd = 0;
        int pra = 0, prd = 0;
        int puba = 0, pubd = 0;

        for (String[] a : attempts) {

            String result = classifyAccess(a[0], a[1]);

            if (a[0].equals("private")) {
                if (result.equals("ALLOWED")) pa++;
                else pd++;
            }
            else if (a[0].equals("default")) {
                if (result.equals("ALLOWED")) da++;
                else dd++;
            }
            else if (a[0].equals("protected")) {
                if (result.equals("ALLOWED")) pra++;
                else prd++;
            }
            else if (a[0].equals("public")) {
                if (result.equals("ALLOWED")) puba++;
                else pubd++;
            }
        }

        return "private: " + pa + " allowed / " + pd + " denied | "
             + "default: " + da + " allowed / " + dd + " denied | "
             + "protected: " + pra + " allowed / " + prd + " denied | "
             + "public: " + puba + " allowed / " + pubd + " denied";
    }

    public static void main(String[] args) {

        System.out.println(
            classifyAccess("private", "SAME_CLASS")
        );

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(attempts));
    }
}