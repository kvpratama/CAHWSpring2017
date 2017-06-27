package week6;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class No1a {

    private int[] parent;
    private Queue<Integer> queue;
    private int numberOfVertices;
    private boolean[] visited;

    public No1a(int numberOfVertices) {
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

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        int noMileage = Integer.parseInt(input);

        input = s.nextLine();
        int mileagePoint[] = splitInputString(input);

        input = s.nextLine();
        int noPayment = Integer.parseInt(input);

        input = s.nextLine();
        int payment[] = splitInputString(input);

        int mileageUsage[][] = new int[noMileage][];

        for (int i = 0; i < noMileage; i++) {
            input = s.nextLine();
            mileageUsage[i] = splitInputString(input);
        }

        int matrixSize = 2 + noMileage + noPayment;

        int[][] graph = new int[matrixSize + 1][matrixSize + 1];

        int index = 2;

        for (int n : mileagePoint){
            graph[1][index] = n;
            index++;
        }

        index = mileagePoint.length + 2;
        for (int n : payment) {
            graph[index][matrixSize] = n;
            index++;
        }

        for (int i = 0; i < noMileage; i++) {
            for (int j = 0; j < mileageUsage[i].length; j++) {
                graph[i+2][mileageUsage[i][j] + noMileage + 2] = mileagePoint[i];
            }
        }

        int source = 1;
        int sink = matrixSize;

        No1a no1a = new No1a(matrixSize);
        int maxFlow = no1a.maxFlow(graph, source, sink);

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
