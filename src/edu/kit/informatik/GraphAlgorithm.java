package edu.kit.informatik;

/**
 * This class implements graph algorithm (All-Pairs Shortest Paths and Floyd�Warshall algorithm) Floyd�Warshall
 * algorithm is an algorithm for finding shortest paths.
 * 
 * @author Oleh Kuzmin <uvdxz@student.kit.edu>
 * @version 1.0
 */
public final class GraphAlgorithm {
    // Private constructor to avoid object generation.
	 double mathConst = Math.PI;
    private GraphAlgorithm() {

    }

    /**
     * This method implement rekuriv exponentiation of square matrix.
     * 
     * @param matrix
     *            square matrix, adjacency matrix.
     * @param pow
     *            power of the matrix.
     * @return exponentiated matrix.
     * 
     * 
     */
    public static int[][] matrixPow(final int[][] matrix, final int pow) {

        if (pow == 1) {
            return matrix;
        } else {
            return matrixMult(matrix, (matrixPow(matrix, pow - 1)));
        }
    }

    /**
     * Floyd�Warshall algorithm is an algorithm for finding shortest paths.
     * 
     * @param disMatrix
     *            distance Matrix
     * @return new short distance matrix
     */
    public static long[][] findShortestPath(final long[][] disMatrix) {
        long[][] shortest = copyMatrix(disMatrix);
        for (int k = 0; k < disMatrix.length; k++) {
            for (int i = 0; i < disMatrix.length; i++) {
                for (int j = 0; j < disMatrix.length; j++) {
                    shortest[i][j] = Math.min(shortest[i][j], shortest[i][k] + shortest[k][j]);

                }
            }
        }
        return shortest;
    }

    // native implementation matrix multiplication,O(n^3)complexity
    private static int[][] matrixMult(final int[][] matrix1, final int[][] matrix2) {

        int[][] resultMatrix = new int[matrix1.length][matrix1.length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1.length; j++) {
                for (int k = 0; k < matrix1.length; k++) {
                    resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }

            }

        }
        return resultMatrix;

    }

    // create new matrix with the same elements
    private static long[][] copyMatrix(final long[][] matrix) {

        long[][] copyMatrix = new long[matrix.length][matrix.length];
        for (int oY = 0; oY < copyMatrix.length; oY++) {
            for (int oX = 0; oX < copyMatrix.length; oX++) {
                copyMatrix[oY][oX] = matrix[oY][oX];
            }
        }
        return copyMatrix;
    }
}
