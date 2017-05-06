package week4;

import java.util.*;

public class No6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int k = scanner.nextInt();
        int totalPath = 0;

        Map<Integer, Integer[]> edges = new HashMap<>();
        Map<Integer, Integer[]> VatK = new HashMap<>();

        for (int i = 1; i <= v; i++) {
            int numEdgeVi = scanner.nextInt();
            Integer[] edge = new Integer[numEdgeVi];
            for (int j = 0; j < numEdgeVi; j++) {
                edge[j] = scanner.nextInt();
            }
            edges.put(i, edge);
        }

        for (int i = 1; i <= v; i++) {
            for (int j = 0; j <= k; j++) {
                if (j == 0) {
                    VatK.put(j, new Integer[]{i});
                } else {
                    List<Integer> edgesAtK = new ArrayList<>();
                    for (int l = 0; l < VatK.get(j - 1).length; l++) {
                        for (int m = 0; m < edges.get(VatK.get(j-1)[l]).length; m++) {
                            edgesAtK.add(edges.get(VatK.get(j-1)[l])[m]);
                            System.out.print(edges.get(VatK.get(j-1)[l])[m] + " ");
                        }
                    }
                    System.out.println();
                    Integer[] e = new Integer[edgesAtK.size()];
                    e = edgesAtK.toArray(e);
                    VatK.put(j, e);
                }
            }
            System.out.println(VatK.get(k).length);
            totalPath += VatK.get(k).length;
            System.out.println();
        }
        System.out.println(totalPath);
    }
}
