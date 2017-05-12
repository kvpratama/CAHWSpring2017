package week5;

import java.util.Scanner;

public class No6 {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] rank = new int[N];
        int[] parent = new int[N];
        int[] value = new int[N];

        for (int i = 0; i < N; i++) {
            value[i] = Integer.MIN_VALUE;
        }

        No6 no6 = new No6();

        for (int i = 0; i < N; i++) {
            String st = s.next();
            int x = s.nextInt();
            int y = s.nextInt();

            if (st.equals("?")) {
                if (!no6.inSet(x, value) || !no6.inSet(y, value)) {
                    System.out.println("Not yet");
                } else if (no6.find(x, parent, value) != no6.find(y, parent, value)) {
                    System.out.println("Different");
                } else {
                    System.out.println("Same");
                }
            } else if (st.equals("!")) {
                if (!no6.inSet(x, value) && !no6.inSet(y, value)) {
                    no6.makeSet(N, parent, value, x);
                    no6.makeSet(N, parent, value, y);
                } else if (!no6.inSet(x, value)) {
                    no6.makeSet(N, parent, value, x);
                    int differentSet = value[no6.find(y, parent, value)];
                    int j = 0;
                    while (j < value.length) {
                        if (value[j] != differentSet) {
                            no6.union(value[j], x, parent, rank, value);
                            break;
                        }
                        j++;
                    }
                } else if (!no6.inSet(y, value)) {
                    no6.makeSet(N, parent, value, y);
                    int differentSet = value[no6.find(x, parent, value)];
                    int j = 0;
                    while (j < value.length) {
                        if (value[j] != differentSet) {
                            no6.union(value[j], y, parent, rank, value);
                            break;
                        }
                        j++;
                    }
                }
            }
        }
    }

    void makeSet(int N, int[] parent, int[] value, int x) {
        for (int i = 0; i < N; i++) {
            if (value[i] == Integer.MIN_VALUE) {
                value[i] = x;
                parent[i] = i;
                break;
            }
        }
    }

    boolean inSet(int x, int[] value) {
        for (int i = 0; i < value.length; i++) {
            if (value[i] == x) {
                return true;
            }
        }
        return false;
    }

    int find(int x, int[] parent, int[] value) {
        int i = 0;
        while (value[i] != x) {
            i++;
        }
        if (value[parent[i]] != x) {
            parent[i] = find(value[parent[i]], parent, value);
        }

        return parent[i];
    }

    void union(int x, int y, int[] parent, int[] rank, int[] value) {
        int xRoot = find(x, parent, value), yRoot = find(y, parent, value);

        if (value[xRoot] == value[yRoot])
            return;

        if (rank[xRoot] < rank[yRoot])
            parent[xRoot] = yRoot;

        else if (rank[yRoot] < rank[xRoot])
            parent[yRoot] = xRoot;

        else {
            parent[yRoot] = xRoot;
            rank[xRoot] = rank[xRoot] + 1;
        }
    }

}
