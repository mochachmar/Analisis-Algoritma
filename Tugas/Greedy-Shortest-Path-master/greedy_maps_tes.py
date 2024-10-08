"""Created on Thu May 11 00:13:54 2023

@author: Moch_Achmar

# Algoritma Greedy Permasalahan Penentuan Jalur Terpendek

Diketahui:
	Jalur: A, B, C, D, E, F, G, H
Ditanya:
	Jalur dengan jarak terpendek dari Universitas Dipa Makassar ke Barru
Jawab:
	Solusi = {'A', 'C', 'D', 'E', 'F', 'B'}
# Kekurangan: 
- Klo misalnya tidak ada penanganan kasus jika titik tujuan tidak ditemukan, 
- atau jika terdapat node yang memiliki nilai negatif (bergerak balik)."""


def shortest(maps, asal, tujuan):  # mencari jalur terpendek dari maps (graph yg dibangun)
    result = []  # node dengan jarak terpendek simpan ke dalam list
    result.append(asal)  # inisialisasi node pertama dengan nilai asal

    while tujuan not in result:  # telusuri graph sampai tujuan ditemukan
        current_node = result[-1]  # -1 == list di index terakhir
#        print ("Node Terakhir :",current_node)
        # Cari local maximum (nilai/jarak terkecil dari node terakhir ke node selanjutnya)
        node, jarak_terpendek = min(maps[current_node].items())
#        print ("Node Dengan Jarak Terpendek Dari Node Terakhir :",node,"|", jarak_terpendek)

        # iterasi mencari node selanjutnya
        for node, jarak in maps[current_node].items():
            #            print ("Node Selanjutnya :",node,"Jarak :",jarak)
            if jarak == jarak_terpendek:
                # ambil node dengan jarak terpendek dan tambahkan ke list result
                # agar iterasi selanjutnya dimulai dari node sekarang.
                result.append(node)

    return result


maps = {  # graph direpresentasikan dengan menggunakan dictionary di dalam dictionary,
    'A': {'G': 9, 'C': 4},
    'G': {'E': 6},
    'C': {'D': 6, 'H': 12},
    'D': {'E': 7},
    'H': {'F': 15},
    'E': {'F': 8},
    'F': {'B': 5}
}
mapss = {  # graph direpresentasikan dengan menggunakan dictionary di dalam dictionary,
    'A': {'C': 4, 'G': 9},
    'G': {'E': 6},
    'C': {'D': 6},
    'D': {'E': 7}
}

a = shortest(maps, "A", "B")
print("\nJalur Dari 'A' ke 'B' :", a)