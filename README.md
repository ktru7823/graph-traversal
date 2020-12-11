# graph-traversal
1. The algorithm is meant to decide for a given (not necessarily connected) undirected graph, given points U and V, if a path exists between U and V. A DFS is used for the implementation of this solution.

Input is in the following form:

n

m

vertexId vertexId weight

vertexId vertexId weight

...

vertexId vertexId weight

q

vertexId vertexId

vertexId vertexId


Input explanation:
First define the graph by defining "n" vertices and "m" edges. Then a list of edges (defined by the vertices on each side), with their associated weight.
After defining the graph, provide a list of size "q". This is a list of pairs of vertices to apply the algorithm to. An example of valid input is provided in "graphSimple.in".

Output: For each pair of vertices to test in order, 0 (no path exists) or 1 (a path exists).

2. This is a variant of the Minimum Spanning Tree problem with an additional requirement: specified edges that must be included in the final tree. Prim's algorithm is used for the implementation of this solution.
3. This is another variant of the MST problem, an extension of (2), where there is an additional requirement: a specified set of vertices which must be connected to exactly 1 edge. This solution again uses Prim's algorithm.
