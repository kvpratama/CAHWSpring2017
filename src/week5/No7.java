package week5;

import java.util.Scanner;

public class No7 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int u, v, weight;
        int[][] adjMatrix = new int[N][N];

        for (int i = 0; i < M; i++) {
            u = scanner.nextInt() - 1;
            v = scanner.nextInt() - 1;
            weight = scanner.nextInt();
            adjMatrix[u][v] = weight;
            adjMatrix[v][u] = weight;
        }

        maxSpanningTree(adjMatrix, N);
    }

    static int maxKey(int key[], Boolean visited[], int N) {
        int max = Integer.MIN_VALUE, max_index = -1;

        for (int v = 0; v < N; v++)
            if (visited[v] == false && key[v] > max) {
                max = key[v];
                max_index = v;
            }
        return max_index;
    }

    static void maxSpanningTree(int graph[][], int N) {
        int spanningTree[] = new int[N];
        int key[] = new int[N];
        Boolean visited[] = new Boolean[N];

        for (int i = 0; i < N; i++) {
            key[i] = Integer.MIN_VALUE;
            visited[i] = false;
        }

        key[0] = 0;
        spanningTree[0] = -1;

        for (int count = 0; count < N - 1; count++) {
            int u = maxKey(key, visited, N);
            visited[u] = true;

            for (int v = 0; v < N; v++)
                if (graph[u][v] != 0 && visited[v] == false &&
                        graph[u][v] > key[v]) {
                    spanningTree[v] = u;
                    key[v] = graph[u][v];
                }
        }

        int totW = 0;
        for (int i = 1; i < N; i++) {
            totW += graph[i][spanningTree[i]];
        }
        System.out.println(totW);
    }
}
