"""Created on Thu May 11 00:13:54 2023

@author: Moch_Achmar

# Algoritma DFS Penentuan Jalur 
# 1. Periksa apakah node tersebut merupakan goals
# 2. Periksa apakah node punya turunan, jika punya maka masukkan kedalam node list
# 3. Jika semua kemungkinan dari node sudah habis, barulah mundur ketitik sebelumya sampai pada titik pertama"""

graph = {
    'A': {'B': 4},
    'B': {'E': 3},
    'E': {'F': 1},
    'F': {}
}


def dfs(graph, start, goal, path=[], distance=0, expand_node='', node_list=''):
    path = path + [start]
    node_list += ' ' * len(expand_node) + '|' + ' ' * \
        (len(node_list) - len(expand_node) - 1) + '{' + start + '}'
    expand_node += ' ' * len(expand_node) + '|' + ' ' * \
        (len(node_list) - len(expand_node) - 1) + start

    if start == goal:
        return path, distance, expand_node, node_list

    if start not in graph:
        return None, None, expand_node, node_list

    shortest_path = None
    shortest_distance = float('inf')

    for node in graph[start]:
        if node not in path:
            new_path, new_distance, new_expand_node, new_node_list = dfs(
                graph, node, goal, path, distance + graph[start][node], expand_node, node_list)
            if new_path and new_distance < shortest_distance:
                shortest_path = new_path
                shortest_distance = new_distance
                expand_node = new_expand_node
                node_list = new_node_list

    return shortest_path, shortest_distance, expand_node, node_list


start_node = 'A'
goal_node = 'F'
path, distance, expand_node, node_list = dfs(graph, start_node, goal_node)

if path is None:
    print("Tidak ditemukan jalur antara", start_node, "dan", goal_node)
else:
    print("Node yang dilalui:", ' -> '.join(path))
    print("Jarak terpendek:", distance, "KM")
    print("Waktu eksekusi program:")
    print("Expand Node   | Node List")
    print(expand_node)
    print(node_list)
    print("     Goals    |    Not expand")
