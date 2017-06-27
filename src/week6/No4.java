package week6;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class No4 {

    private int[] parent;
    private Queue<Integer> queue;
    private int numberOfVertices;
    private boolean[] visited;

    public No4(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.queue = new LinkedList<Integer>();
        parent = new int[numberOfVertices + 1];
        visited = new boolean[numberOfVertices + 1];
    }

    public boolean bfs(int source, int goal, int graph[][]) {
        boolean pathFound = false;
        int destination, element;
        for (int vertex = 1; vertex <= numberOfVertices; vertex++) {
            parent[vertex] = -1;
            visited[vertex] = false;
        }
        queue.add(source);
        parent[source] = -1;
        visited[source] = true;

        while (!queue.isEmpty()) {
            element = queue.remove();
            destination = 1;
            while (destination <= numberOfVertices) {
                if (graph[element][destination] > 0 && !visited[destination]) {
                    parent[destination] = element;
                    queue.add(destination);
                    visited[destination] = true;
                }
                destination++;
            }
        }

        if (visited[goal]) {
            pathFound = true;
        }
        return pathFound;
    }

    public int[][] maxFlow(int graph[][], int source, int destination) {
        int u, v;
        int pathFlow;
        int[][] residualGraph = new int[numberOfVertices + 1][numberOfVertices + 1];

        for (int sourceVertex = 1; sourceVertex <= numberOfVertices; sourceVertex++) {
            for (int destinationVertex = 1; destinationVertex <= numberOfVertices; destinationVertex++) {
                residualGraph[sourceVertex][destinationVertex] = graph[sourceVertex][destinationVertex];
            }
        }

        while (bfs(source, destination, residualGraph)) {
            pathFlow = Integer.MAX_VALUE;
            for (v = destination; v != source; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }
            for (v = destination; v != source; v = parent[v]) {
                u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }
        }

        return residualGraph;
    }

    public static void main(String... arg) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int noTestCase = Integer.parseInt(input);

        for (int t = 0; t < noTestCase; t++) {
            input = scanner.nextLine();
            int projectAndEquipment[] = splitInputString(input);
            int noProject = projectAndEquipment[0];
            int noEquipment = projectAndEquipment[1];

            int matrixSize = noProject + noEquipment + 2;
            int[][] graph = new int[matrixSize + 1][matrixSize + 1];

            input = scanner.nextLine();
            int projectProfit[] = splitInputString(input);

            input = scanner.nextLine();
            int equipmentPrice[] = splitInputString(input);

            int projectRequirement[][] = new int[noProject][];

            for (int i = 0; i < noProject; i++) {
                input = scanner.nextLine();
                projectRequirement[i] = splitInputString(input);
            }

            int index = 2;
            for (int n : equipmentPrice) {
                graph[1][index] = n;
                index++;
            }

            index = equipmentPrice.length + 2;
            for (int n : projectProfit) {
                graph[index][matrixSize] = n;
                index++;
            }

            for (int i = 0; i < noProject; i++) {
                for (int j = 0; j < projectRequirement[i].length; j++) {
                    if (projectRequirement[j][i] == 1) {
                        graph[i + 2][j + noEquipment + 2] = equipmentPrice[i];
                    }
                }
            }

            int source = 1;
            int sink = matrixSize;

            No4 no4 = new No4(matrixSize);
            int[][] residualGraph = no4.maxFlow(graph, source, sink);

            int residue = 0;
            for (int i = 0; i < noProject; i++) {
                residue += residualGraph[i + noEquipment + 2][matrixSize];
            }
            System.out.println(residue);
        }
    }

    // String input = "1 2 3 4 5";
    // to =
    // int splitInt[] = {1, 2, 3, 4, 5};
    public static int[] splitInputString(String input) {
        String splitInput[] = input.split(" ");
        int splitInt[] = new int[splitInput.length];

        for (int i = 0; i < splitInput.length; i++) {
            splitInt[i] = Integer.parseInt(splitInput[i]);
        }

        return splitInt;
    }
}
