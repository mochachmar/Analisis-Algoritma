public class RabinKarpPatternMatching {
    public static int rabinKarp(String text, String pattern, int prime) {
        int n = text.length();
        int m = pattern.length();
        int hashPattern = hash(pattern, prime);
        int hashText = hash(text.substring(0, m), prime);
        for (int i = 0; i <= n - m; i++) {
            if (hashPattern == hashText) {
                if (text.substring(i, i + m).equals(pattern)) {
                    return i;
                }
            }
            if (i < n - m) {
                hashText = ((hashText - text.charAt(i) * power(prime, m - 1, prime)) * prime + text.charAt(i + m)) % prime;
                if (hashText < 0) {
                    hashText = (hashText + prime) % prime;
                }
            }
        }
        return -1;
    }

    public static int hash(String str, int prime) {
        int hashValue = 0;
        int n = str.length();
        for (int i = 0; i < n; i++) {
            hashValue = (hashValue * prime + str.charAt(i)) % prime;
        }
        return hashValue;
    }

    public static int power(int x, int y, int p) {
        int res = 1;
        x = x % p;
        while (y > 0) {
            if (y % 2 == 1) {
                res = (res * x) % p;
            }
            y = y / 2;
            x = (x * x) % p;
        }
        return res;
    }

    public static void main(String[] args) {
        String text = "MOCH ACHMAR";
        String pattern = "ACHMAR";
        int prime = 101;
        int index = rabinKarp(text, pattern, prime);
        if (index != -1) {
            System.out.println("Pattern found at index " + index);
        } else {
            System.out.println("Pattern not found");
        }
    }
}
