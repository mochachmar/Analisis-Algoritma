// Pseudocode BM:
// procedure PencocokanString(input P : string, T : string, n, m : integer, output idx : integer)
// { Masukan: pattern P yang panjangnya m dan teks T yang panjangnya n. Teks T direpresentasikan sebagai string (array of character)
// Keluaran: lokasi awal kecocokan (idx)
// }

// Deklarasi
//   i, j, skip : integer
//   ketemu : boolean
//   badCharTable[256] : integer

// Algoritma:
// i ← 0
// ketemu ← false
// BuatBadCharTable(P, m, badCharTable)  { Membuat bad character table menggunakan    fungsi BuatBadCharTable }

// while (i ≤ n - m) do
//     j ← m - 1

//     while (j ≥ 0) and (P[j] = T[i + j]) do
//       j ← j - 1
//     endwhile

//     if j < 0 then { kecocokan string ditemukan }
//       ketemu ← true
//       idx ← i
//       break
//     else
//       skip ← max(1, j - badCharTable[T[i + j]])
//       i ← i + skip
//     endif
//   endwhile

//   if ketemu then
//     return idx
//   else
//     return -1
//   endif

// function BuatBadCharTable(P : string, m : integer, badCharTable : integer[])
// { Masukan: pattern P yang panjangnya m
// Keluaran: bad character table (badCharTable) }

//   Deklarasi
//     i : integer

//   for i ← 0 to 255 do
//     badCharTable[i] ← m
//   endfor

//   for i ← 0 to m - 2 do
//     badCharTable[P[i]] ← m - 1 - i
//   endfor
// }

Boyer-Moore in Java:
public static int bmMatch(String text,
String pattern)
{
int last[] = buildLast(pattern);
int n = text.length();
int m = pattern.length();
int i = m-1;
if (i > n-1)
return -1; // no match if pattern is
// longer than text 	int j = m-1;
do {
if (pattern.charAt(j) == text.charAt(i))
if (j == 0)
return i; // match
else { // looking-glass technique
i--;
j--;
}
else { // character jump technique
int lo = last[text.charAt(i)]; //last occ
i = i + m - Math.min(j, 1+lo);
j = m - 1;
}
} while (i <= n-1);
return -1; // no match
} // end of bmMatch()
public static int[] buildLast(String pattern)
/* Return array storing index of last
occurrence of each ASCII char in pattern. */
{
int last[] = new int[128]; // ASCII char set
for(int i=0; i < 128; i++)
last[i] = -1; // initialize array
for (int i = 0; i < pattern.length(); i++)
last[pattern.charAt(i)] = i;
return last;
} // end of buildLast()
