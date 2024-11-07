package project;

import java.util.Scanner;
import java.util.function.Function;

public class SimpsonNewtonWithInput {




    public static double simpsonsIntegration(Function<Double, Double> f, double a, double x, int n) {
        // Ensure that 'a' is not equal to 'x'
        if (a == x) {
            throw new IllegalArgumentException("Нижняя и верхняя границы не могут быть одинаковыми.");
        }

        double h = (x - a) / 2*n;
        double result = f.apply(a) + f.apply(x);

        double oddSum = 0;
        for (int i = 1; i < n; i += 2) {
            oddSum += f.apply(a + i * h);
        }

        double evenSum = 0;
        for (int i = 2; i < n; i += 2) {
            evenSum += f.apply(a + i * h);
        }

        result += 4 * oddSum + 2 * evenSum;
        result *= (h / 3);
        return result;
    }

    public static double derivative(Function<Double, Double> f, double x, double h) {
        return (f.apply(x + h) - f.apply(x - h)) / (2 * h);
    }

//    public static double newtonMethod(Function<Double, Double> G, double x0, double eps, int maxIterations) {
//        double x = x0;
//
//        for (int i = 0; i < maxIterations; i++) {
//            double gx = G.apply(x);
//
//            // Check if we're within the specified precision
//            if (Math.abs(gx) < eps) {
//                return x;
//            }
//
//            double dgx = derivative(G, x, 1e-7);
//            if (dgx == 0) {
//                throw new RuntimeException("Производная равна нулю. Метод Ньютона не может продолжаться.");
//            }
//
//            x -= gx / dgx;
//        }
//
//        throw new RuntimeException("Метод Ньютона не сошелся.");
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Нижняя граница (a): ");
            double a = scanner.nextDouble();

            System.out.print("Верхняя граница (x): ");
            double x = scanner.nextDouble();

            if (a == x) {
                System.out.println("Нижняя и верхняя границы должны быть разными.");
                return;
            }

            System.out.print("Введите число сегментов (n): ");
            int n = scanner.nextInt();

            System.out.print("Введите значение константы (c): ");
            double c = scanner.nextDouble();

            System.out.print("Начальное предположение (x0): ");
            double x0 = scanner.nextDouble();

            System.out.print("Точность для интегрирования (epsilon_1): ");
            double epsilon_1 = 0.001;

            System.out.print("Точность для метода Ньютона (epsilon_2): ");
            double epsilon_2 = 0.001;

            System.out.print("Максимальное количество итераций для метода Ньютона: ");
            int maxIterations = scanner.nextInt();  // Максимальное количество итераций для метода Ньютона

            Function<Double, Double> f = (t) -> t/(Math.pow(t,4)+4);

            Function<Double, Double> F = (x1) -> simpsonsIntegration(f, a, x1, n);

            Function<Double, Double> G = (x1) -> F.apply(x1) - c;

//            double solution = newtonMethod(G, x0, epsilon_2, maxIterations);
            double solution= simpsonsIntegration(f,a,x,n)-c;

            System.out.println("Значение x, решающее уравнение F(x) - c = 0: " + solution);
        } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("Обнаружена ошибка ввода: " + ex.getMessage());
        }
    }
}
