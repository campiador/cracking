
public class Main {

    public static void main(String[] args) {

//        System.out.println(urlifyLinearTime("Mr John Smith    ", 13));
        System.out.println(oneAway("Bam1", "Ba1") ? "One Away!" : "Not One Away!");





    }

    private static String urlify(String str, int length) {

        int actual_length = str.length();

        char[] charSeq = str.toCharArray();

        for (int i = 0; i < actual_length; i++) {

            if (charSeq[i] == ' ') {

                charSeq[i] = '%';

                for (int j = actual_length - 1; j > i + 2; j--) {
                    charSeq[j] = charSeq[j - 2];
                }

                charSeq[i + 1] = '2';
                charSeq[i + 2] = '0';
                i += 2;

            }

        }

        return new String(charSeq);
    }

    /**
     * Let's say we have n spaces in the original url, the characters before the first space are untouched, the chars
     * at the exclusive interval between the first and second space need to shift right by two, the characters between
     * the second and third space will be shifted by four, then 6 and so on and so forth.
     *
     * Because we are shifting right, we start from right to left to avoid overwriting characters we have not read yet!
     *
     * @param str
     * @param trueLength
     * @return urlified string
     */
    private static String urlifyLinearTime(String str, int trueLength) {

        if (trueLength == 0) {
            return "";
        } else if (trueLength == 1) {
            if (str.charAt(0) == ' ') {
                return "%20";
            } else return String.valueOf(str.charAt(0));
        }


        char[] chars = str.toCharArray();

        int actualLength = str.length();

        int offset = actualLength - trueLength;

        for (int i = trueLength - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                offset -= 2;
            }
            chars[i + offset] = chars[i];
        }

        for (int i = 0; i < actualLength; i++) {
            if (chars[i] == ' ') {
                chars[i] = '%';
                chars[i + 1] = '2';
                chars[i + 2] = '0';
            }
        }

        return new String(chars);
    }

    private static boolean oneAway(String str1, String str2){


        if (str1.equals(str2)) return true;

        if (str1.length() == str2.length()) {
            return checkReplace(str1, str2);
        } else if (str1.length() > str2.length()) {
            return checkInsertion(str1, str2);
        } else {
            return checkInsertion(str2, str1);
        }



    }

    private static boolean checkReplace(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        boolean seenReplacement = false;

        for (int i = 0; i < chars1.length; i++) {

            if (chars1[i] != chars2[i]) {
                if (seenReplacement) {
                    return false;
                }

                seenReplacement = true;
            }
        }

        return true;
    }


    private static boolean checkInsertion(String longer, String shorter) {

        if (longer.length() - shorter.length() != 1) {
            return false;
        }

        char[] charsLong = longer.toCharArray();
        char[] charsShort = shorter.toCharArray();

        int seenInsertion = 0;

        for (int i = 0; i < charsShort.length; i++) {
            if (charsLong[i + seenInsertion] != charsShort[i]) {
                if (seenInsertion == 1) {
                    return false;
                }

                seenInsertion = 1;
            }
        }

        return true;
    }

}
