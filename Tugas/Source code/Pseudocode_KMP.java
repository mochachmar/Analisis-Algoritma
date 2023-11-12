// Pseudocode KMP:
// procedure PencocokanString(input P : string, T : string, n, m : integer, output idx : integer)
// { Masukan: pattern P yang panjangnya m dan teks T yang panjangnya n. Teks T direpresentasikan sebagai string (array of character)
// Keluaran: lokasi awal kecocokan (idx)
// }

// Deklarasi
//  i, j, lps[m] : integer
//  ketemu : boolean

// Algoritma:
//  i ← 0
//  j ← 0
//  ketemu ← false
//  BuatLongestProperSuffixArray(P, m, lps)  { Membuat longest proper suffix array menggunakan fungsi BuatLongestProperSuffixArray }

//  while (i < n) do
//    if P[j] = T[i] then
//      j ← j + 1
//      i ← i + 1

//      if j = m then { kecocokan string ditemukan }
//        ketemu ← true
//        idx ← i - j
//        break
//      endif
//    else
//      if j ≠ 0 then
//        j ← lps[j-1]
//      else
//        i ← i + 1
//      endif
//    endif
//  endwhile

//  if ketemu then
//    return idx
//  else
//    return -1
//  endif

// function BuatLongestProperSuffixArray(P : string, m : integer, lps : integer[])
// { Masukan: pattern P yang panjangnya m
// Keluaran: longest proper suffix array (lps) }

//  Deklarasi
//    len ← 0
//    i ← 1
//    lps[0] ← 0

//  while (i < m) do
//    if P[i] = P[len] then
//      len ← len + 1
//      lps[i] ← len
//      i ← i + 1
//    else
//      if len ≠ 0 then
//        len ← lps[len-1]
//      else
//        lps[i] ← 0
//        i ← i + 1
//      endif
//    endif
//  endwhile
// }

KMP in Java:
public static int kmpMatch(String text,
String pattern)
{
int n = text.length();
int m = pattern.length();
int b[] = computeBorder(pattern);
int i=0;
int j=0; 
while (i < n) {
if (pattern.charAt(j) == text.charAt(i)) {
if (j == m - 1)
return i - m + 1; // match
i++;
j++;
}
else if (j > 0)
j = b[j-1];
else
i++;
}
return -1; // no match
}// end of kmpMatch()

public static int[] computeBorder(String pattern)
{
int b[] = new int[pattern.length()];
fail[0] = 0;
int m = pattern.length();
int j = 0;
int i = 1;

while (i < m) {
if (pattern.charAt(j) == pattern.charAt(i)) {
//j+1 chars match
b[i] = j + 1;
i++;
j++;
}
else if (j > 0) // j follows matching prefix
j = b[j-1];
else { // no match
b[i] = 0;
i++;
}
}
return fail;
}// end of computeBorder()
