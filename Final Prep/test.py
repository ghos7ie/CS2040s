from itertools import product, combinations

def generate_all_inputs(num_vertices):
    graph_types = ['directed', 'undirected']
    edge_combinations = list(combinations(range(num_vertices), 2))

    for edge_count in range(num_vertices - 1, num_vertices * (num_vertices - 1) // 2 + 1):
        for edges in combinations(edge_combinations, edge_count):
            for graph_type in graph_types:
                print(f"{num_vertices} vertices, {len(edges)} edges, {graph_type}")
                for edge in edges:
                    print(f"{edge[0]} {edge[1]}")

# Example usage
num_vertices = 4
generate_all_inputs(num_vertices)
