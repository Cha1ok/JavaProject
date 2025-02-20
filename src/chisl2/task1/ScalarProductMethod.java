package chisl2.task1;
import java.util.*;
import java.io.*;

public class ScalarProductMethod {

    public static double findDominantEigenPair(double[][] A, double[] x, double eps, int maxIterations) {
        int n = x.length;
        double lambda = 0.0;
        double lambdaPrev;

        normalize(x);

        for (int iter = 0; iter < maxIterations; iter++) {
            double[] z = multiplyMatrixVector(A, x);

            lambdaPrev = lambda;
            double dotXZ = dotProduct(x, z);
            double dotXX = dotProduct(x, x);
            lambda = dotXZ / dotXX;

            normalize(z);
            System.arraycopy(z, 0, x, 0, n);

            if (iter > 0 && Math.abs(lambda - lambdaPrev) < eps) {
                break;
            }
        }
        return lambda;
    }

    private static double[][] readMatrixFromFile(String fileName) {
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            int n = fileScanner.nextInt();
            double[][] A = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = fileScanner.nextDouble();
                }
            }
            return A;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден: " + fileName, e);
        }
    }

    private static double[] multiplyMatrixVector(double[][] A, double[] x) {
        int n = x.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            result[i] = sum;
        }
        return result;
    }

    private static double dotProduct(double[] x, double[] y) {
        double sum = 0.0;
        for (int i = 0; i < x.length; i++) {
            sum += x[i] * y[i];
        }
        return sum;
    }

    private static void normalize(double[] v) {
        double norm = Math.sqrt(dotProduct(v, v));
        if (norm < 1e-14) {
            throw new RuntimeException("Норма вектора слишком мала, возможно, вектор нулевой.");
        }
        for (int i = 0; i < v.length; i++) {
            v[i] /= norm;
        }
    }

    public static void main(String[] args) throws IOException {
        String inputFileName = "C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\chisl2\\task1\\input2.txt";
        String outputFileName = "C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\chisl2\\task1\\output.txt";

        double[][] A = readMatrixFromFile(inputFileName);
        int n = A.length;

        double[] x = new double[n];
        x[0] = 1.0;

        double eps = 1e-7;
        int maxIter = 1000;

        double lambda = findDominantEigenPair(A, x, eps, maxIter);

        System.out.println("\nРезультаты:");
        System.out.println("Приближённое доминирующее собственное значение: " + lambda);
        System.out.println("Приближённый собственный вектор: " + java.util.Arrays.toString(x));

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {
            writer.println("Результаты:");
            writer.println("Приближённое доминирующее собственное значение: " + lambda);
            writer.println("Приближённый собственный вектор: " + java.util.Arrays.toString(x));
            System.out.println("Результаты успешно записаны в файл \"" + outputFileName + "\"");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Ошибка при записи в файл " + outputFileName);
        }
    }
}
