public class BruteForcePatternMatching {
    public static int bruteForcePatternMatching(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String text = "SAYA SEDANG BELAJAR ALGORITMA GENETIKA";
        String pattern = "ALGORITMA";
        int index = bruteForcePatternMatching(text, pattern);
        if (index != -1) {
            System.out.println("Pola ditemukan pada indeks: " + index);
        } else {
            System.out.println("Pola tidak ditemukan di indeks");
        }
    }
}    
