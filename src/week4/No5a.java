package week4;

import java.util.Scanner;

public class No5a {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] x = new int[n][n];
        int[][] total = new int[n][n];
        int[][] a = new int[n*2][n*2];
        int[][] r = new int[n*2][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                x[i][j] = scanner.nextInt();
                total[i][j] = x[i][j];
            }
        }

        for (int i = 0; i < n * 2; i++) {
            for (int j = 0; j < n * 2; j++) {
                if(i == j && j < n){
                    a[i][j] = 1;
                }else if(j>=n){
                    if (i>=n){
                        a[i][j] = x[i-n][j-n];
                    }else{
                        a[i][j] = x[i][j-n];
                    }
                }
            }
        }

        for (int i = 0; i < n * 2; i++) {
            for (int j = 0; j < n; j++) {
                if (i>=n){
                    r[i][j] = x[i-n][j];
                }else{
                    r[i][j] = x[i][j];
                }
            }
        }

        int[][] R = new int[n*2][n];
        R = matrixMultiplyMatrix(matrixPow(a, k-1), r);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(R[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] identityMatrix(int n) {
        int[][] identityMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            identityMatrix[i][i] = 1;
        }
        return identityMatrix;
    }

    private static int[][] matrixPow(int[][] matrix, int n) {

        int[][] result = identityMatrix(matrix.length);
        while (n != 0) {
            if (n % 2 != 0){
                result = matrixMultiplyMatrix(result, matrix);
            }
            n /= 2;
            matrix = matrixMultiplyMatrix(matrix, matrix);
        }
        return result;
    }

    public static int[][] matrixMultiplyMatrix(int[][] matrix1, int[][] matrix2) {
        int[][] result = new int[matrix1.length][matrix2.length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[i].length; j++) {
                for (int k = 0; k < matrix1.length; k++) {
                    result[i][j] = (result[i][j] + matrix1[i][k] * matrix2[k][j]) % 32767;
                }
            }
        }
        return result;
    }
}
