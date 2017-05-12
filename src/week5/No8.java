package week5;

import java.util.Scanner;

public class No8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[][] graph = new int[N][N];
        int[][] installedGraph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = scanner.nextInt();
                installedGraph[i][j] = graph[i][j];
            }
        }

        int K = scanner.nextInt();
        int installed_u, installed_v;
        for (int i = 0; i < K; i++) {
            installed_u = scanner.nextInt();
            installed_v = scanner.nextInt();
            installedGraph[installed_u][installed_v] = Integer.MIN_VALUE;
        }

        minSpanningTree(graph, installedGraph, N);
    }

    static int minKey(int key[], Boolean visited[], int N) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < N; v++)
            if (visited[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        return min_index;
    }

    public static void printMatrix(int matrix[][]) {
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[0].length; k++) {
                System.out.print(matrix[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void minSpanningTree(int graph[][], int installedGraph[][], int N) {
        int spanningTree[] = new int[N];
        int key[] = new int[N];
        Boolean visited[] = new Boolean[N];

        for (int i = 0; i < N; i++) {
            key[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        key[0] = 0;
        spanningTree[0] = -1;

        for (int count = 0; count < N - 1; count++) {
            int u = minKey(key, visited, N);
            visited[u] = true;

            for (int v = 0; v < N; v++){
                if (installedGraph[u][v] != 0 && visited[v] == false &&
                        installedGraph[u][v] < key[v]) {
                    spanningTree[v] = u;
                    key[v] = installedGraph[u][v];
                }
            }
        }

        int[][] resultMatrix = new int[N][N];
        int u, v, weight;
        for (int i = 1; i < N; i++) {
            u = spanningTree[i];
            v = i;
            weight = graph[i][spanningTree[i]];
            resultMatrix[u][v] = weight;
            resultMatrix[v][u] = weight;
        }

        int totW = 0;
        for (int i = 0; i < key.length; i++) {
            if (key[i] != Integer.MIN_VALUE) {
                totW += key[i];
            }
        }
        System.out.println(totW);

        printMatrix(resultMatrix);
    }
}