package chisl.taks1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LUDecomposition {

    // Чтение матрицы и вектора из одного файла
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

    // Запись решения в файл
    private static void writeSolutionToFile(String filename, double[] solution) throws IOException {
        FileWriter writer = new FileWriter(filename);
        for (int i = 0; i < solution.length; i++) {
            writer.write(String.format("x[%d] = %.4f\n", i, solution[i]));
        }
        writer.close();
    }

    // LU-разложение с фиксированной единичной диагональю у матрицы U
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

    // Прямой ход для решения Ly = b
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

    // Обратный ход для решения Ux = y
    private static double[] backwardSubstitution(double[][] U, double[] y) {
        int n = U.length;
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += U[i][j] * x[j];
            }
            x[i] = y[i] - sum; // так как диагональ в U единичная
        }
        return x;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Чтение матрицы и вектора из одного файла
        Object[] data = readMatrixAndVectorFromFile("C:/Users/79186/Desktop/data.txt");
        double[][] A = (double[][]) data[0];
        double[] b = (double[]) data[1];

        // Размер матрицы
        int n = A.length;

        // Создаем матрицы L и U
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];

        // Инициализируем единичную диагональ матрицы U
        for (int i = 0; i < n; i++) {
            U[i][i] = 1.0;
        }

        // Выполняем LU-разложение
        luDecomposition(A, L, U);

        // Решаем систему Ly = b (прямой ход)
        double[] y = forwardSubstitution(L, b);

        // Решаем систему Ux = y (обратный ход)
        double[] x = backwardSubstitution(U, y);

        // Записываем результат в файл
        writeSolutionToFile("C:/Users/79186/Desktop/solution.txt", x);
    }
}
