package org.example;

import java.util.concurrent.Executors;

class App {
    public static void main(String[] args) {
        int[][] matrixA = {
                { 2, 4, 6 },
                { 3, 5, 7 },
                { 1, 7, 9 }
        };

        int[][] matrixB = {
                { 4, 0, 0 },
                { 0, 4, 1 },
                { 9, 2, 1 }
        };

        System.out.println("Matrix A:");
        MatrixOperations.printMatrix(matrixA, 3, 3);

        System.out.println("Matrix B:");
        MatrixOperations.printMatrix(matrixB, 3, 3);

        int[][] matrixC = MatrixOperations.multiplyMatrices(matrixA, matrixB, 3, 3, 3,
                Executors.newVirtualThreadPerTaskExecutor());

        System.out.println("Matrix C (multiplication result):");
        MatrixOperations.printMatrix(matrixC, 3, 3);
    }
}
