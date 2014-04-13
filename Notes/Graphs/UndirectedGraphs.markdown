Graphs:
-------

**Graph**: a set of *vertices* and a collection of *edges* that each connect a pair of vertices.

We use the names 0 through to V-1 for the vertices in a V-vertex graph.

We use the notation 'v-w' to refer to the edge that connects vertices `v` and `w`. 'w-v' refers to the same edge. Graphs are defined independently of the drawing; two diagrams that represent the same graph represent graphs that are 'isomorphic' (i.e., equivalent).

We also define:

**self-loop**: an edge that connects a vertex to itself

**parallel edges**: 2 edges that connect the same pair of vertices

Graphs with parallel edges are called 'multigraphs', and graphs with no parallel edges or self-loops are 'simple graphs'.

Definitions
-----------------

When there is an edge connecting 2 vertices, we say the 2 vertices are *adjacent*, and that the edge is *incident to* both vertices.

The *degree* of a vertex is the number of edges incident to it.

A *subgraph* is a subset of a graph's edges (and associated vertices) that constitutes a graph.

A *path* in a graph is a sequence of vertices connected by edges. A *simple path* is one with no repeated vertices. A *cycle* is a path with at least one edge and whose sequence of vertices start and end with the same vertex. A *simple cycle* is a cycle with no repeated edges or vertices (other than the first and last vertices). The *length* of a path is its # of edges.

![anatomy](anatomy.png)

We say that one vertex is *connected* to another if there exists a path that contains both of them.

Notation: `u-v-w-x-u` represents a cycle from `u` to `v` to `w` to `x` and back to `u`.

A graph is *connected* if there is a path from every vertex to every other vertex in the graph. A graph that is *not connected* consists of a set of connected components, which are "maximal connected subgraphs"

An *acyclic graph* is a graph with no cycles.


A *tree* is an acyclic connected graph. A disjoint set of trees is called a *forest*. A *spanning tree* of a connected graph is a subgraph that contains all that graph's vertices and is a single tree. A *spanning forest* of a graph is the union of spanning trees of its connected components.

![acyclic](acyclic.png)

A graph G with V vertices is a tree if and only if it satisfies any of these 5 conditions:

1) G has V - 1 edges and no cycles
2) G has V - 1 edges and is connected
3) G is connected, but removing any edge disconnects it
4) G is acyclic, but adding any edge creates a cycle
5) Exactly one simple path connects each pair of vertices in G


The *density* of a graph is the proportion of possible pairs of vertices that are connected by edges. Note that for an undirected graph (graph whose edges do not have a sense of direction, e.g.., all the graphs discussed so far) with V vertices, there are potentially (V choose 2) edges.

A *sparse* graph has relatively few of the possible edges, whereas a *dense graph* has relatively many of the possible edges. We think of a graph as sparse if its # of different edges is within a small constant factor of V, and dense otherwise.

A *bipartite graph* is one whose vertices can be divided into 2 sets such that all edges connect a vertex in one set with a vertex in the other set. The below pictures shows that each edge connects two vertices, each of which is a different colour. Thus, all the nodes of one colour can be put in one set, and all the others in another set to satisfy the above conditions.

![for example](bipartite.png)

Ways of representing a Graph API:

- An *adjacency matrix* is a V by V boolean array; if two vertices `i` and `j` have an edge between them, then `arr[i][j]` should be true. However this is space inefficient; the space cost of V^2 is too high (graphs with millions of vertices are common).

- An array of edges, using an Edge class with 2 instance variables of type int; each corresponding to a vertex. However, finding `adj(v)` (finds all the edges adjacent to vertex `v`) would involve examining all the edges, and thus it would be slow.

- An array of *adjacency lists*, where a vertex-indexed array of lists of the vertices adjacent to each vertex. Thus, each array element would be a vertex, and the Vertex class defines an instance variable for the list of array indices that the vertex is connected to. The list itself can just be a set or a bag (multi-set, to allow for parallel edges). This is the best approach so far and is what we will use.

We use a bag in our implementation, but one can use a symbol table if they want operations such as adding or deleting a vertex. One can also use a Set instead of a Bag for adjacency lists.

A summary of these implementations is below:

![big o](big-o.png)

Depth-first Search (DFS)
------------------------

The DFS works recursively as follows:

To visit a vertex, mark it as having been visited in a `marked[]` int array.
Then, recursively visit all adjacent nodes that have not yet been visited. Thus, each node will be visited once.

We can also use this to determine if a graph is connected. Perform a dfs which maintains the # of elements that are set to true in the marked[] array. If this number is equal to the # of vertices, the graph must be connected (since all the vertices were able to be visited).

Breadth-first Search (BFS)
--------------------------

DFS isn't great for solving the Single-source shortest path problem (Is there a path from a source vertex `s` to a given target vertex `v`; if so, what's the shortest such path (minimal # of edges)?).

BFS on the other hand is based on solving this problem. To find a shortest path, we start at `s` and check for `v` among all vertices we can reach by following 1 edge, then by following 2 edges, and so on till `v` is found. Note that the BFS uses an explicit queue, while the recursive DFS uses an implicit stack (through recursion).

This can be done by maintaining a queue of vertices that have been marked but whose adjacency lists have not been checked. We put the source vertex `s` on the queue, then perform these steps:

1) Take the next vertex `v` from the queue and mark it
2) Put onto the queue all unmarked vertices adjacent to `v`

For example:

Consider the following graph

![bfs](bfs.png)

    Queue: 0

    1) Dequeue 0

    Queue:

    2) Enqueue 1, 2, 5 (all unmarked adjacent nodes) and mark them

    Queue: 1, 2, 5

    3) Dequeue 1

    Queue: 2, 5

    4) Enqueue nothing (all adjacent nodes are already marked)

    Queue: 2, 5

    5) Dequeue 2

    Queue: 5

    6) Enqueue 3, 4 (all unmarked adjacent nodes) and mark them

    Queue: 5, 3, 4

    7) Dequeue 5, 3, 4 (nothing to enqueue after each dequeue since the adjacent nodes are marked). We skip these steps for simplicity.

    Queue: 3, 4

    Queue: 4

    Queue:

    8) Queue is empty: we're done.


Connectedness
-------------

DFS can be used to find all the connected components of a graph. A graph is processed once through DFS, and afterwards, `connected(v, w)` can be used to check if vertices v and w are connected in `O(1)` time.
3
Steps:

1) For every unmarked vertex `v` in the graph `g`: call dfs(g, v)
2)`dfs(g,v)` will mark every vertex `v` with an id. the id used is incremented after each iteration of step 1
3) connected(v, w) -> id[v] == id[w];

DFS is faster than the union-find algorithm in practice since it provides a constant-time guarantee, which union-find does not. In practice, the difference is negligible, and union-find is faster since it doesn't have to build a full representation of the graph.

Additionally, union-find is an "online" algorithm, (can check if 2 vertices are connected in near constant-time even while adding edges), while DFS must first pre-process the graph.


Detecting Cycles and Bipartiteness
----------------------------------

DFS can also be used to determine if a graph contains a cycle (assuming no self-loops and no parallel edges).

Consider a graph with 4 vertices connected in a square formation. Starting a DFS at one vertex will eventually lead back to the original vertex, if it wasn't already marked. Thus, if the vertex is marked, and it's not the vertex that you were JUST at (i.e., the vertex that led you to the current vertex), then there must be a cycle.
