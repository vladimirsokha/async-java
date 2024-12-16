package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class MatrixOperations {
    public static void printMatrix(int[][] matrix, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB, int m, int n, int k,
            ExecutorService executor) {

        int[][] matrixC = new int[m][k];
        AtomicInteger currentRow = new AtomicInteger(0);
        AtomicInteger currentColumn = new AtomicInteger(0);

        int elementsCount = m * k;
        AtomicInteger elementsDone = new AtomicInteger(0);

        for (int t = 0; t < elementsCount; t++) {
            executor.submit(() -> {
                int row, col;

                synchronized (currentRow) {
                    row = currentRow.get();
                    col = currentColumn.getAndIncrement();

                    if (col == k) {
                        currentColumn.set(0);
                        col = currentColumn.getAndIncrement();
                        row = currentRow.incrementAndGet();
                    }
                }

                if (row < m && col < k) {
                    int sum = 0;
                    for (int i = 0; i < n; i++) {
                        sum += matrixA[row][i] * matrixB[i][col];
                    }
                    matrixC[row][col] = sum;

                    int completed = elementsDone.incrementAndGet();
                    if (completed == elementsCount) {
                        executor.shutdown();
                    }
                }
            });
        }
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return matrixC;
    }
}
