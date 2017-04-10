package week3;

import java.util.Stack;

//Suppose that you are given an n × n checkerboard and a checker. You must move the
//checker from the top leftmost square (1, 1) to the bottom rightmost square (n, n) according
//to the following rule. At each step, you may move the checker to the one of the three
//squares:
//  (a) the square that is one to the right (that is →, but only if the checker is not already
//        in the rightmost column),
//  (b) the square that is one down (that is ↓, but only if the checker is not already in the
//        bottom row),
//  (c) the square that is one down and one to the right (that is &, but only if the checker
//        is not already in the bottom rightmost square).
//
// Each time, you move from square x to square y, you earn p(x, y) points. Give an algorithm
// that finds out the path (that is, the set of legal moves) from the top leftmost square (1, 1) to
// the bottom rightmost square (n, n) where the sum of all points in the path is the maximum.

public class MaxPath {
    public static void main(String[] args) {
        int[][] checkerboard = {{2, 5, 1, 6}, {6, 1, 1, 2}, {7, 2, 3, 2}, {1, 1, 3, 1}};
        int[][] solutionMap = new int[checkerboard.length][checkerboard.length];

        for (int i = 0; i < checkerboard.length; i++) {
            for (int j = 0; j < checkerboard.length; j++) {
                if (i == 0 && j == 0) {
                    solutionMap[i][j] = checkerboard[i][j];
                } else if (i == 0) {
                    solutionMap[i][j] = checkerboard[i][j] + solutionMap[i][j - 1];
                } else if (j == 0) {
                    solutionMap[i][j] = checkerboard[i][j] + solutionMap[i - 1][j];
                } else {
                    int down = solutionMap[i - 1][j] + checkerboard[i][j];
                    int right = solutionMap[i][j - 1] + checkerboard[i][j];
                    int dia = solutionMap[i - 1][j - 1] + checkerboard[i][j];
                    solutionMap[i][j] = Math.max(Math.max(down, right), dia);
                }
            }
        }
        Stack route = new Stack();
        int i = solutionMap.length - 1, j = solutionMap.length - 1;

        while (i != 0 || j != 0) {
            route.push(i + ", " + j);
            int up = 0, left = 0, dia = 0;
            if (i != 0) {
                up = solutionMap[i - 1][j];
            }
            if (j != 0) {
                left = solutionMap[i][j - 1];
            }
            if (i != 0 && j != 0) {
                dia = solutionMap[i - 1][j - 1];
            }

            int max = Math.max(Math.max(up, left), dia);
            if (max == up) {
                i--;
            } else if (max == left) {
                j--;
            } else {
                i--;
                j--;
            }
        }

        while (!route.empty()) {
            System.out.println(route.pop());
        }
    }
}
