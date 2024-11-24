package chisl.task3;

import java.util.*;
import java.io.*;
import java.util.function.Function;

public class StephensonMethod {

    // Функции для тестирования
    public static double f1(double x) {
        return Math.pow(x, 3) - 6 * Math.pow(x, 2) + 11 * x - 6; // Полином
    }

    public static double f2(double x) {
        return Math.sin(x); // Синус
    }

    public static double f3(double x) {
        return Math.exp(x) - 3 * Math.pow(x, 2); // Экспоненциально-квадратичная
    }

    // Метод Стефенсона для поиска одного корня
    public static Double stephensonMethod(double x0, double tolerance, int maxIterations, Function<Double, Double> f) {
        double x = x0;
        int iteration = 0;

        while (iteration < maxIterations) {
            double fx = f.apply(x);
            double fxShifted = f.apply(x + fx);

            // Проверяем деление на ноль
            double denominator = fxShifted - fx;
            if (Math.abs(denominator) < 1e-12) {
                return null; // Возвращаем null при ошибке
            }

            // Считаем следующую итерацию
            double xNext = x - Math.pow(fx, 2) / denominator;

            // Проверяем условие сходимости
            if (Math.abs(xNext - x) < tolerance) {
                return xNext; // Возвращаем найденное приближение
            }

            x = xNext;
            iteration++;
        }

        return null; // Возвращаем null, если сходимость не достигнута
    }

    // Поиск всех корней на заданном интервале
    public static List<Double> findAllRoots(double a, double b, double step, double tolerance, int maxIterations, Function<Double, Double> f) {
        List<Double> roots = new ArrayList<>();

        double x = a;
        while (x < b) {
            double xNext = x + step;

            // Проверяем, меняет ли функция знак на [x, xNext]
            if (f.apply(x) * f.apply(xNext) <= 0) {
                // Если есть смена знака, локализуем корень
                double initialGuess = (x + xNext) / 2; // Начальное приближение в середине отрезка
                Double root = stephensonMethod(initialGuess, tolerance, maxIterations, f);

                if (root != null) {
                    // Проверяем, что корень уникален (не дублируется из-за округления)
                    boolean isUnique = roots.stream().noneMatch(r -> Math.abs(r - root) < tolerance);
                    if (isUnique) {
                        roots.add(root);
                    }
                }
            }

            x = xNext; // Переходим к следующему подотрезку
        }

        return roots;
    }

    public static void main(String[] args) throws IOException {
        // Чтение из файла
        FileReader fileReader = new FileReader("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\chisl\\task3/input3.txt");
        Scanner sc = new Scanner(fileReader);
        FileWriter writer = new FileWriter("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\chisl\\task3/output.txt");

        // Считываем параметры из файла
        double a = sc.nextDouble(); // Начало интервала
        double b = sc.nextDouble(); // Конец интервала
        double step = sc.nextDouble(); // Шаг разбиения
        int funcChoice = sc.nextInt(); // Выбор функции (1 - f1, 2 - f2, 3 - f3)

        sc.close();

        // Выбираем функцию
        Function<Double, Double> selectedFunction;
        switch (funcChoice) {
            case 1 -> selectedFunction = StephensonMethod::f1;
            case 2 -> selectedFunction = StephensonMethod::f2;
            case 3 -> selectedFunction = StephensonMethod::f3;
            default -> throw new IllegalArgumentException("Некорректный выбор функции!");
        }

        // Параметры метода
        double tolerance = 1e-6; // Точность
        int maxIterations = 100; // Максимальное количество итераций

        // Поиск корней
        List<Double> roots = findAllRoots(a, b, step, tolerance, maxIterations, selectedFunction);

        // Записываем результаты в файл
        for (double root : roots) {
            writer.write(String.format("x = %.6f\n", root));
            System.out.printf("x = %.6f\n", root);
        }

        writer.close();
    }
}
