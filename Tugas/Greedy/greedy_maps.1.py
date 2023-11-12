"""Created on Thu May 11 00:13:54 2023
@author: Moch_Achmar
# Algoritma Greedy Permasalahan Penentuan Jalur Terpendek
# Kekurangan: 
- Klo misalkan tidak ada penanganan kasus jika titik tujuan tidak ditemukan, 
- atau jika ada node yg memiliki nilai negatif (bergerak balik)."""

import networkx as nx
import matplotlib.pyplot as plt
import time


def shortest(graph, source, dest):
    result = []
    result.append(source)
    total_weight = 0

    while dest not in result:
        current_node = result[-1]
        print("Node Terakhir:", current_node)

        min_weight = float('inf')
        next_node = None

        for node, weight in graph[current_node].items():
            print("Node Selanjutnya:", node, "| Jarak:", weight)
            if weight < min_weight:
                min_weight = weight
                next_node = node

        if next_node is None:
            break

        result.append(next_node)
        total_weight += min_weight

    if dest in result:
        return result, total_weight
    else:
        return [], float('inf')


full_graph = {
    'A': {'B': 4, 'C': 5},
    'B': {'E': 3},
    'C': {'D': 3, 'E': 1},
    'D': {'F': 4},
    'E': {'F': 1},
    'F': {}
}

simple_graph = {
    'A': {'B': 4},
    'B': {'E': 3},
    'E': {'F': 1},
    'F': {}
}


def draw(graph):
    g = nx.DiGraph()
    for node, connections in graph.items():
        for target, weight in connections.items():
            g.add_edge(node, target, weight=str(weight) + " km")
    pos = nx.shell_layout(g)
    edge_labels = nx.get_edge_attributes(g, 'weight')
    node_colors = ['g' if node == 'A' else 'b' for node in g.nodes()]
    node_colors[list(g.nodes()).index('F')] = 'r'
    nx.draw_networkx_nodes(g, pos, node_size=1000, node_color=node_colors)
    nx.draw_networkx_edges(g, pos)
    nx.draw_networkx_labels(g, pos)
    nx.draw_networkx_edge_labels(g, pos, edge_labels=edge_labels)
    plt.title("graph")
    plt.axis("off")
    plt.show()


def main():
    repeat = True
    while repeat:
        choose = int(
            input("Choose graph:\n1. Simple graph\n2. Full graph\n\n"))
        if choose == 1:
            print("\nSimple graph:")
            draw(simple_graph)
            start_time = time.time()
            graph, total_weight = shortest(simple_graph, "A", "F")
            end_time = time.time()
            print("\nJalur Dari 'A' ke 'F':", graph)
            print("Total Jarak:", total_weight, "km")
            print("Waktu yang dibutuhkan:", end_time - start_time, "detik")
            print("Graph : ")
        elif choose == 2:
            print("\nFull graph:")
            draw(full_graph)
            start_time = time.time()
            graph, total_weight = shortest(full_graph, "A", "F")
            end_time = time.time()
            print("\nJalur Dari 'A' ke 'F':", graph)
            print("Total Jarak:", total_weight, "km")
            print("Waktu yang dibutuhkan:", end_time - start_time, "detik")
            print("Graph : ")
        else:
            print("Invalid choice.")

        repeat_input = input(
            "\nApakah ingin menginput ulang? (Y/N) atau (y/t) : ")
        if repeat_input.lower() not in ["y", "yes"]:
            repeat = False


if __name__ == '__main__':
    main()
