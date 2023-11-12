public class BoyerMoorePatternMatching {
    public static int[] preprocessBadCharacterTable(String pattern) {
        int m = pattern.length();
        int[] badChar = new int[256];
        for (int i = 0; i < 256; i++) {
            badChar[i] = m;
        }
        for (int i = 0; i < m - 1; i++) {
            badChar[pattern.charAt(i)] = m - 1 - i;
        }
        return badChar;
    }

    public static int boyerMoore(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] badChar = preprocessBadCharacterTable(pattern);
        int s = 0;
        while (s <= n - m) {
            int j = m - 1;
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
                j--;
            }
            if (j < 0) {
                return s;
            } else {
                s = s + Math.max(1, badChar[text.charAt(s + j)] - j);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String text = "SAYA SEDANG BELAJAR ALGORITMA GENETIKA";
        String pattern = "ALGORITMA";
        int index = boyerMoore(text, pattern);
        if (index != -1) {
            System.out.println("Pattern found at index " + index);
        } else {
            System.out.println("Pattern not found");
        }
    }
}
