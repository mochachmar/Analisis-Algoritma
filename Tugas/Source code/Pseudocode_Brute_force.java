// Pseudocode Brute Force:
// procedure PencocokanString(input P : string, T : string, n, m : integer, output idx : integer)
// { Masukan: pattern P yang panjangnya m dan teks T yang panjangnya n. Teks T direpresentasikan sebagai string (array of character)
// Keluaran: lokasi awal kecocokan (idx)
// }

// Deklarasi
//  i : integer
//  ketemu : boolean

// Algoritma:
//  i←0
//  ketemu←false
//  while (i ≤ n-m) and (not ketemu) do
//  	j←1
//  	while (j ≤ m) and (P_j=T_(i+j) ) do
// 		j←j+1
//  	endwhile
//  	{ j > m or P_j=T_(i+j) }

//  	if j = m then { kecocokan string ditemukan }
// 		ketemu←true
//  	else
// 		i←i+1 {geser pattern satu karakter ke kanan teks }
//  	endif
//  endwhile
//  { i > n – m or ketemu }
//  if ketemu then return i+1 else return -1 endif


Brute Force in Java:
public static int brutematch(String T, String P)
{    int n = T.length(); // n is length of text
     int m = P.length();; // m is length of pattern
     int j;

     for(int i=0; i <= (n-m); i++) {
j = 0;
while ((j < m) && (T.charAt(i+j)== P.charAt(j)) ) {
j++;
}
if (j == m)
return i; // match at i
      }
      return -1; // no match
}
// end of brutematch()
