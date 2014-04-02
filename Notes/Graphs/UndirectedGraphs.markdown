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


