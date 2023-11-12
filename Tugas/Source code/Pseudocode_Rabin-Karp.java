// Pseudocode Rabin-Karp:
// procedure PencocokanString(input P : string, T : string, n, m : integer, output idx : integer)
// { Masukan: pattern P yang panjangnya m dan teks T yang panjangnya n. Teks T direpresentasikan sebagai string (array of character)
// Keluaran: lokasi awal kecocokan (idx)
// }

// Deklarasi
//   i, j, pHash, tHash, h : integer
//   ketemu : boolean
//   prime : integer

// Algoritma:
//   prime ← 101  { Bilangan prima yang digunakan dalam Rabin-Karp }
//   i ← 0
//   ketemu ← false
//   pHash ← hitungHash(P, m, prime)  { Menghitung hash dari pattern P menggunakan fungsi hitungHash }
//   tHash ← hitungHash(T, m, prime)  { Menghitung hash dari substring pertama teks T menggunakan fungsi hitungHash }
//   h ← power(prime, m - 1) mod prime  { Nilai h yang akan digunakan dalam perhitungan rolling hash }

//   while (i ≤ n - m) do
//     if pHash = tHash then
//       j ← 0
//       while (j < m) and (P[j] = T[i + j]) do
//         j ← j + 1
//       endwhile

//       if j = m then  { kecocokan string ditemukan }
//         ketemu ← true
//         idx ← i
//         break
//       endif
//     endif

//     if i < n - m then
//       tHash ← (prime * (tHash - T[i] * h) + T[i + m]) mod prime
//       if tHash < 0 then
//         tHash ← tHash + prime
//       endif
//     endif

//     i ← i + 1
//   endwhile

//   if ketemu then
//     return idx
//   else
//     return -1
//   endif

// function hitungHash(s : string, len, prime : integer) : integer
// { Masukan: string s dengan panjang len, bilangan prima prime
// Keluaran: nilai hash dari string s }

//   Deklarasi
//     hashVal, i : integer

//   hashVal ← 0
//   for i ← 0 to len - 1 do
//     hashVal ← (hashVal * prime + s[i]) mod prime
//   endfor

//   return hashVal
// }

Rabin-Karp in Java:
public static int rabinKarpMatch(String text, String pattern) {
    int last[] = buildLast(pattern);
    int n = text.length();
    int m = pattern.length();
    int i = m - 1;
    
    if (i > n - 1)
        return -1; // no match if pattern is longer than text
    
    int j = m - 1;
    
    do {
        if (pattern.charAt(j) == text.charAt(i)) {
            if (j == 0)
                return i; // match
            else { // looking-glass technique
                i--;
                j--;
            }
        } else { // character jump technique
            int lo = last[text.charAt(i)]; // last occurrence
            i = i + m - Math.min(j, 1 + lo);
            j = m - 1;
        }
    } while (i <= n - 1);
    
    return -1; // no match
}

public static int[] buildLast(String pattern) {
    int last[] = new int[128]; // ASCII char set
    
    for (int i = 0; i < 128; i++)
        last[i] = -1; // initialize array
    
    for (int i = 0; i < pattern.length(); i++)
        last[pattern.charAt(i)] = i;
    
    return last;
}
