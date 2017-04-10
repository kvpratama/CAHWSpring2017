package week3;



public class No1 {
    public static void main(String[] args) {
        int totalMarble = 4;
        int pickMarble = 3;
        int[][] fiboNsteps = FiboNSteps(pickMarble);

        int[][] result = matrixPow(fiboNsteps, totalMarble);
        for (int[] i : result) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static int[][] FiboNSteps(int n) {
        int[][] fiboNsteps = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    fiboNsteps[i][j] = 1;
                } else if (i - 1 == j) {
                    fiboNsteps[i][j] = 1;
                } else {
                    fiboNsteps[i][j] = 0;
                }
            }
        }
        return fiboNsteps;
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
        int[][] result = new int[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1.length; j++) {
                for (int k = 0; k < matrix1.length; k++) {
                    result[i][j] = (result[i][j] + matrix1[i][k] * matrix2[k][j]) % 65535;
                }
            }
        }
        return result;
    }

    public static int[] vectorMultiplyMatrix(int[] vector, int[][] matrix) {
        int[] tempVector = new int[vector.length];
        for (int i = 0; i < vector.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                tempVector[i] += vector[j] * matrix[j][i];
            }
        }
        for (int i = 0; i < tempVector.length; i++) {
            System.out.print(tempVector[i] + " ");
        }
        System.out.println();
        return tempVector;
    }
}
