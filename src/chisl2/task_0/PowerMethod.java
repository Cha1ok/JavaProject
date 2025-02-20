package chisl2.task_0;

import java.util.Scanner;

public class PowerMethod {


    public static double powerIteration(double[][] matrix, double[] vector, double epsilon, int maxIterations) {
        int n = vector.length;
        double[] currentVector = vector.clone();
        double eigenvalue = 0.0;
        double prevEigenvalue = 0.0;

        for (int iter = 0; iter < maxIterations; iter++) {

            double[] nextVector = multiplyMatrixVector(matrix, currentVector);

            double norm = euclideanNorm(nextVector);


            for (int i = 0; i < n; i++) {
                nextVector[i] /= norm;
            }

            prevEigenvalue = eigenvalue;
            eigenvalue = norm;

            if (iter > 0 && Math.abs(eigenvalue - prevEigenvalue) < epsilon) {
                System.arraycopy(nextVector, 0, currentVector, 0, n);
                break;
            }

            System.arraycopy(nextVector, 0, currentVector, 0, n);
        }

        System.arraycopy(currentVector, 0, vector, 0, n);
        return eigenvalue;
    }

    private static double[] multiplyMatrixVector(double[][] matrix, double[] vector) {
        int n = vector.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = 0.0;
            for (int j = 0; j < n; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    private static double euclideanNorm(double[] vector) {
        double sum = 0.0;
        for (double v : vector) {
            sum += v * v;
        }
        return Math.sqrt(sum);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите матрицу:");
        System.out.println("1. Матрица 2x2");
        System.out.println("2. Матрица 3x3");
        System.out.println("3. Матрица 4x4");
        int choice = scanner.nextInt();

        double[][] matrix;
        double[] initialVector;

        switch (choice) {
            case 1:
                matrix = new double[][]{{4, 1}, {1, 3}};
                initialVector = new double[]{1, 0};
                break;
            case 2:
                matrix = new double[][]{
                        {2, -1, 0},
                        {-1, 2, -1},
                        {0, -1, 2}
                };
                initialVector = new double[]{1, 0, 0};
                break;
            case 3:
                matrix = new double[][]{
                        {5, -2, 0, 0},
                        {-2, 5, -2, 0},
                        {0, -2, 5, -2},
                        {0, 0, -2, 5}
                };
                initialVector = new double[]{1, 0, 0, 0};
                break;
            default:
                System.out.println("Неверный выбор. Используется матрица 2x2 по умолчанию.");
                matrix = new double[][]{{4, 1}, {1, 3}};
                initialVector = new double[]{1, 0};
                break;
        }

        double epsilon = 1e-6;
        int maxIterations = 1000;

        double eigenvalue = powerIteration(matrix, initialVector, epsilon, maxIterations);

        System.out.printf("Доминирующее собственное значение: %.6f%n", eigenvalue);
        System.out.println("Соответствующий собственный вектор:");
        for (double v : initialVector) {
            System.out.printf("%.6f ", v);
        }
        System.out.println();
    }
}
