import java.util.Scanner;
import java.util.function.Function;

public class SimpsonNewtonWithInput {

    // Метод Симпсона для интегрирования с заданным числом сегментов
    public static double simpsonsIntegration(Function<Double, Double> f, double a, double x, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Число сегментов должно быть больше нуля.");
        }

        if (a == x) {
            throw new IllegalArgumentException("Нижняя и верхняя границы не могут быть одинаковыми.");
        }

        double h = (x - a) / n;
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

    // Метод Ньютона для поиска корня функции G(x)
    public static double newtonMethod(Function<Double, Double> G, double x0, double epsilon, int maxIterations) {
        double x = x0;

        for (int i = 0; i < maxIterations; i++) {
            double gx = G.apply(x); // значение функции G(x)

            if (Math.abs(gx) < epsilon) { // если в пределах заданной точности
                return x; // решение найдено
            }

            // Численно вычисляем производную
            double derivative = (G.apply(x + 1e-7) - G.apply(x - 1e-7)) / 2e-7;

            if (derivative == 0) {
                throw new RuntimeException("Производная равна нулю. Метод Ньютона не может продолжаться.");
            }

            x -= gx / derivative; // обновляем значение x
        }

        throw new RuntimeException("Метод Ньютона не сошелся за максимальное количество итераций.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите нижнюю границу (a): ");
            double a = scanner.nextDouble();

            System.out.print("Введите верхнюю границу (x): ");
            double x = scanner.nextDouble();

            if (a == x) {
                System.out.println("Нижняя и верхняя границы должны быть разными.");
                return;
            }

            System.out.print("Введите число сегментов (n): ");
            int n = scanner.nextInt();

            if (n <= 0) {
                System.out.println("Число сегментов должно быть больше нуля.");
                return;
            }

            System.out.print("Введите значение константы (c): ");
            double c = scanner.nextDouble();

            System.out.print("Начальное предположение (x_0): ");
            double x_0 = scanner.nextDouble();

            System.out.print("Точность для метода Ньютона (epsilon_2): ");
            double epsilon_2 = scanner.nextDouble();

            if (epsilon_2 <= 0) {
                System.out.println("Точность должна быть больше нуля.");
                return;
            }

            System.out.print("Максимальное количество итераций для метода Ньютона: ");
            int maxIterations = scanner.nextInt();

            Function<Double, Double> f = (t) -> Math.sin(t); // пример функции

            Function<Double, Double> F = (x1) -> simpsonsIntegration(f, a, x1, n);

            Function<Double, Double> G = (x1) -> F.apply(x1) - c;

            // Решение уравнения методом Ньютона
            double solution = newtonMethod(G, x_0, epsilon_2, maxIterations);

            System.out.println("Значение x, решающее уравнение F(x) - c = 0: " + solution);
        } catch (RuntimeException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("Ошибка ввода: " + ex.getMessage());
        }
    }
}

