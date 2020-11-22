import java.util.*;

public class A1 {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int vertexCount = -1;
		int edgeCount = -1;
		int pairCount = -1;

		String[] pairInput;

		List<ArrayList<Integer>> adjacencyList = new ArrayList<>();
		if (!keyboard.hasNextLine()) {
			keyboard.close();
			return;
		}
		
		String vertexInput = keyboard.nextLine();
		if (vertexInput.isEmpty()) {
			keyboard.close();
			return;
		}
		vertexCount = Integer.parseInt(vertexInput);
		for (int i = 0; i < vertexCount; i++) {
			adjacencyList.add(new ArrayList<Integer>());
		}
		edgeCount = Integer.parseInt(keyboard.nextLine());

		for (int i = 0; i < edgeCount; i++) {
			String[] idPair = keyboard.nextLine().split(" ");
			int id1 = Integer.parseInt(idPair[0]);
			int id2 = Integer.parseInt(idPair[1]);

			ArrayList<Integer> vertex = adjacencyList.get(id1);
			vertex.add(id2);
			vertex = adjacencyList.get(id2);
			vertex.add(id1);
		}

		pairCount = Integer.parseInt(keyboard.nextLine());
		pairInput = new String[pairCount];
		for (int i = 0; i < pairCount; i++) {
			pairInput[i] = keyboard.nextLine();
		}

		keyboard.close();

		if (vertexCount <= 1 || edgeCount == 0) {
			System.out.println("0");
			return;
		}

		for (int i = 0; i < pairCount; i++) {
			String buffer = pairInput[i];
			String[] idPair = buffer.split(" ");
			int id1 = Integer.parseInt(idPair[0]);
			int id2 = Integer.parseInt(idPair[1]);

			boolean visited[] = new boolean[vertexCount];

			if(reachableDFS(id1, id2, visited, adjacencyList)) {
				System.out.println("1");
			} else {
				System.out.println("0");
			}
		}
	}

	private static boolean reachableDFS(int start, int goal, boolean visited[], List<ArrayList<Integer>> adjacencyList) {

		if (visited[goal]) {
			return true;
		}

		visited[start] = true;
		for (int vertexId : adjacencyList.get(start)) {
			if (!visited[vertexId]) {
				if(reachableDFS(vertexId, goal, visited, adjacencyList)) {
					return true;
				}
			}
		}

		if (visited[goal]) {
			return true;
		}

		return false;
	}
}
