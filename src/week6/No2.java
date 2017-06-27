package week6;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class No2 {

    private int[] parent;
    private Queue<Integer> queue;
    private int numberOfVertices;
    private boolean[] visited;

    public No2(int numberOfVertices) {
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

    public int maxFlow(int graph[][], int source, int destination) {
        int u, v;
        int maxFlow = 0;
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
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public static void main(String... arg) {
        int[][] graph;
        int numberOfNodes;
        int source;
        int sink;
        int maxFlow;

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        numberOfNodes = Integer.parseInt(input);
        int matrixSize = ((numberOfNodes - 2) * 2) + 2;
        graph = new int[matrixSize + 1][matrixSize + 1];

        input = scanner.nextLine();
        int weight[] = splitInputString(input);

        for (int i = 0; i < numberOfNodes - 1; i++) {
            if (i != 0) {
                graph[i * 2][i * 2 + 1] = weight[i];
            }
            input = scanner.nextLine();
            int row[] = splitInputString(input);
            for (int j = 0; j < row.length; j++) {
                if (row[j] > i - 1) {
                    graph[i * 2 + 1][row[j] * 2] = Integer.MAX_VALUE;
                }
            }
        }

        source = 1;
        sink = matrixSize;

        No2 no2 = new No2(matrixSize);
        maxFlow = no2.maxFlow(graph, source, sink);

        System.out.println(maxFlow);
    }

    public static int[] splitInputString(String input) {
        String splitInput[] = input.split(" ");
        int splitInt[] = new int[splitInput.length];

        for (int i = 0; i < splitInput.length; i++) {
            splitInt[i] = Integer.parseInt(splitInput[i]);
        }

        return splitInt;
    }
}