package week3;

public class No4 {

    public static void main(String[] args) {
        int n = 10000;
        double[][] matrix = {{4,13}, {1, 4}};
        double[][] result = matrixPow(matrix, n-1);

        double an = (2.0 * matrixMultiplyVector(result, new double[]{4,1})[0] - 1.0) % 100000;
        System.out.printf("%.0f\n", an);
    }

    public static double[][] identityMatrix(int n) {
        double[][] identityMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            identityMatrix[i][i] = 1;
        }
        return identityMatrix;
    }

    private static double[][] matrixPow(double[][] matrix, double n) {

        double[][] result = identityMatrix(matrix.length);
        while (n != 0) {
            if (n % 2 != 0) {
                result = matrixMultiplyMatrix(result, matrix);
            }
            n = Math.floor(n / 2);
            matrix = matrixMultiplyMatrix(matrix, matrix);
        }
        return result;
    }

    public static double[][] matrixMultiplyMatrix(double[][] matrix1, double[][] matrix2) {
        double[][] result = new double[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1.length; j++) {
                for (int k = 0; k < matrix1.length; k++) {
                    result[i][j] = (result[i][j] + matrix1[i][k] * matrix2[k][j]) % 100000;
                }
            }
        }
        return result;
    }

    public static double[] matrixMultiplyVector(double[][] matrix, double[] vector) {
        double[] result = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }
}
