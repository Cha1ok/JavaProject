package chisl.task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LUDecomposition {

    private static Object[] readMatrixAndVectorFromFile(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        int n = sc.nextInt();
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextDouble();
            }
        }
        double[] vector = new double[n];
        for (int i = 0; i < n; i++) {
            vector[i] = sc.nextDouble();
        }
        sc.close();
        return new Object[]{matrix, vector};
    }

    private static void writeSolutionToFile(String filename, double[] solution) throws IOException {
        FileWriter writer = new FileWriter(filename);
        for (int i = 0; i < solution.length; i++) {
            writer.write(String.format("x[%d] = %.4f\n", i, solution[i]));
        }
        writer.close();
    }

    private static void luDecomposition(double[][] A, double[][] L, double[][] U) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                double sum = 0.0;
                for (int k = 0; k < j; k++) {
                    sum += L[i][k] * U[k][j];
                }
                L[i][j] = A[i][j] - sum;
            }
            for (int j = i + 1; j < n; j++) {
                double sum = 0.0;
                for (int k = 0; k < i; k++) {
                    sum += L[i][k] * U[k][j];
                }
                U[i][j] = (A[i][j] - sum) / L[i][i];
            }
        }
    }

    private static double[] forwardSubstitution(double[][] L, double[] b) {
        int n = L.length;
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < i; j++) {
                sum += L[i][j] * y[j];
            }
            y[i] = (b[i] - sum) / L[i][i];
        }
        return y;
    }

    private static double[] backwardSubstitution(double[][] U, double[] y) {
        int n = U.length;
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += U[i][j] * x[j];
            }
            x[i] = y[i] - sum;
        }
        return x;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Object[] data = readMatrixAndVectorFromFile("C:/Users/kupts/IdeaProjects/untitled/src/chisl/task1/data.txt");
        double[][] A = (double[][]) data[0];
        double[] b = (double[]) data[1];

        int n = A.length;

        // Создаем матрицы L и U
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];

        for (int i = 0; i < n; i++) {
            U[i][i] = 1.0;
        }

        luDecomposition(A, L, U);

        double[] y = forwardSubstitution(L, b);

        double[] x = backwardSubstitution(U, y);

        writeSolutionToFile("C:/Users/kupts/IdeaProjects/untitled/src/chisl/task1/solution.txt", x);
    }
}