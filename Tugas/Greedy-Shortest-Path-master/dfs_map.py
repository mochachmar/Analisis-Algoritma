import networkx as nx
import matplotlib.pyplot as plt


def shortest(maps, asal, tujuan):
    result = []
    result.append(asal)

    while tujuan not in result:
        current_node = result[-1]

        jarak_terpendek = min(maps[current_node].values())

        for node, jarak in maps[current_node].items():
            if jarak == jarak_terpendek:
                result.append(node)
    return result


maps = {
    'A': {'B': 1, 'C': 4},
    'B': {'D': 1, 'F': 3},
    'D': {'H': 5},
    'F': {'H': 4},
    'C': {'D': 3},
    'H': {}
}


def draw(maps, shortest_path):
    g = nx.DiGraph()

    for node, connections in maps.items():
        for target, weight in connections.items():
            g.add_edge(node, target, weight=str(weight) + " km")

    pos = nx.shell_layout(g)
    edge_labels = nx.get_edge_attributes(g, 'weight')
    node_colors = ['g' if node == asal else 'b' for node in g.nodes()]
    node_colors[list(g.nodes()).index(tujuan)] = 'r'

    nx.draw_networkx_nodes(g, pos, node_size=1000, node_color=node_colors)
    nx.draw_networkx_edges(g, pos)
    nx.draw_networkx_labels(g, pos)
    nx.draw_networkx_edge_labels(g, pos, edge_labels=edge_labels)
    plt.title("MAPS")
    plt.axis("off")
    plt.show()


def main():
    asal = 'A'
    tujuan = 'H'
    print("Jarak terpendek dari", asal, "ke", tujuan, "adalah:")
    print(shortest(maps, asal, tujuan))

    print("\nMAPS:")
    draw(maps, shortest(maps, asal, tujuan))


if __name__ == '__main__':
    main()
