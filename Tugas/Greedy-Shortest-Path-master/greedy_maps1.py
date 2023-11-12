import networkx as nx
import matplotlib.pyplot as plt


def shortest(maps, asal, tujuan):
    result = []
    result.append(asal)

    while tujuan not in result:
        current_node = result[-1]
        print("Node Terakhir:", current_node)

        jarak_terpendek = min(maps[current_node].values())
        print("Node Dengan Jarak Terpendek Dari Node Terakhir:", jarak_terpendek)

        for node, jarak in maps[current_node].items():
            print("Node Selanjutnya:", node, "| Jarak:", jarak)
            if jarak == jarak_terpendek:
                result.append(node)

    return result


full_maps = {
    'A': {'B': 1, 'C': 4},
    'B': {'D': 1, 'F': 3},
    'D': {'H': 5},
    'F': {'H': 4},
    'C': {'D': 3},
    'H': {}
}

simple_maps = {
    'A': {'B': 1},
    'B': {'D': 1},
    'D': {'H': 5},
    'H': {}
}


def draw(maps):
    g = nx.DiGraph()

    for node, connections in maps.items():
        for target, weight in connections.items():
            g.add_edge(node, target, weight=str(weight) + " km")

    pos = nx.shell_layout(g)
    edge_labels = nx.get_edge_attributes(g, 'weight')
    node_colors = ['g' if node == 'A' else 'b' for node in g.nodes()]
    node_colors[list(g.nodes()).index('H')] = 'r'

    nx.draw_networkx_nodes(g, pos, node_size=1000, node_color=node_colors)
    nx.draw_networkx_edges(g, pos)
    nx.draw_networkx_labels(g, pos)
    nx.draw_networkx_edge_labels(g, pos, edge_labels=edge_labels)
    plt.title("MAPS")
    plt.axis("off")
    plt.show()


def main():
    choose = int(input("Choose Maps:\n1. Simple Maps\n2. Full Maps\n\n"))

    if choose == 1:
        print("\nSimple Maps:")
        draw(simple_maps)
        maps = shortest(simple_maps, "A", "H")
        print("\nJalur Dari 'A' ke 'H':", maps)
    elif choose == 2:
        print("\nFull Maps:")
        draw(full_maps)
        maps = shortest(full_maps, "A", "H")
        print("\nJalur Dari 'A' ke 'H':", maps)
    else:
        print("Invalid choice.")


if __name__ == '__main__':
    main()
