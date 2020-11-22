import java.util.*;

public class A1 {

	private static Map<Integer, Map<Integer, Double>> constructMap(int edgeCount, String[][] edgeInput) {
		Map<Integer, Map<Integer, Double>> adjacencyMap = new TreeMap<>();
		for (int i = 0; i < edgeCount; i++) {
			int vertexA = Integer.parseInt(edgeInput[i][0]);
			int vertexB = Integer.parseInt(edgeInput[i][1]);
			double weight = Double.parseDouble(edgeInput[i][2]);

			if (!adjacencyMap.containsKey(vertexA)) {
				Map<Integer, Double> connectedToA = new TreeMap<>();
				connectedToA.put(vertexB, weight);
				adjacencyMap.put(vertexA, connectedToA);
			} else {
				Map<Integer, Double> connectedToA = adjacencyMap.get(vertexA);
				connectedToA.put(vertexB, weight);
				adjacencyMap.put(vertexA, connectedToA);
			}

			if (!adjacencyMap.containsKey(vertexB)) {
				Map<Integer, Double> connectedToB = new TreeMap<>();
				connectedToB.put(vertexA, weight);
				adjacencyMap.put(vertexB, connectedToB);
			} else {
				Map<Integer, Double> connectedToB = adjacencyMap.get(vertexB);
				connectedToB.put(vertexA, weight);
				adjacencyMap.put(vertexB, connectedToB);
			}
		}

		return adjacencyMap;
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int vertexCount = -1;
		int edgeCount = -1;
		int fixedEdgeCount = -1;
		double totalWeight = 0;

		String edgeInput[][];

		try {
			vertexCount = Integer.parseInt(keyboard.nextLine());

			edgeCount = Integer.parseInt(keyboard.nextLine());
			edgeInput = new String[edgeCount][];

			for (int i = 0; i < edgeCount; i++) {
				edgeInput[i] = keyboard.nextLine().split(" ");
			}

			fixedEdgeCount = Integer.parseInt(keyboard.nextLine());

			for (int i = 0; i < fixedEdgeCount; i++) {
				String fixedEdgeInput[] = keyboard.nextLine().split(" ");

				for (int j = 0; j < edgeCount; j++) {
					if ((edgeInput[j][0].equals(fixedEdgeInput[0]) && 
						 edgeInput[j][1].equals(fixedEdgeInput[1])) ||
						(edgeInput[j][1].equals(fixedEdgeInput[0]) && 
						 edgeInput[j][0].equals(fixedEdgeInput[1]))) {

						totalWeight += Double.parseDouble(edgeInput[j][2]);
						edgeInput[j][2] = "0";
						break;
					}
				}
			}
		} catch (NumberFormatException e) {
			System.err.println("error: faulty input");
			keyboard.close();
			return;
		}

		keyboard.close();

		if (vertexCount <= 1 || edgeCount == 0) {
			System.out.println("0.00");
			return;
		}

		Map<Integer, Map<Integer, Double>> adjacencyMap = constructMap(edgeCount, edgeInput);

		int[] parent = new int[vertexCount];
		double[] distance = new double[vertexCount];
		boolean[] visited = new boolean[vertexCount];

		for (int i = 1; i < vertexCount; i++) {
			distance[i] = Double.MAX_VALUE;
			visited[i] = false;
		}

		parent[0] = -1;
		distance[0] = 0;

		for (int i = 0; i < vertexCount; i++) {
			int currentVertex = -1;
			double minDistance = Double.MAX_VALUE;

			for (int j = 0; j < vertexCount; j++) {
				if (visited[j]) {
					continue;
				}
				if (minDistance > distance[j]) {
					minDistance = distance[j];
					currentVertex = j;
				}
			}

			visited[currentVertex] = true;
			Map<Integer, Double> edgeMap = adjacencyMap.get(currentVertex);
			Set<Integer> connectedVertices = edgeMap.keySet();
			for (Integer connectedVertex : connectedVertices) {
				if (!visited[connectedVertex]) {
					double weight = edgeMap.get(connectedVertex);
					if (weight < distance[connectedVertex]) {
						parent[connectedVertex] = currentVertex;
						distance[connectedVertex] = weight;
					}
				}
			}
		}

		for (int i = 0; i < vertexCount; i++) {
			totalWeight += distance[i];
		}

		System.out.printf("%.2f\n", totalWeight);
	}
}
