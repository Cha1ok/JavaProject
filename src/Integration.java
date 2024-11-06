//import java.util.function.BiFunction;
//import java.util.function.Function;
//
//public class Integration {
//    public static double simps(Function<Double, Double> f, double a, double b, int n) {
//        double h = (b - a) / n;
//        double x, sum1 = 0, sum2 = 0, sum3 = 0;
//        for (int i = 0; i <= n; i++) {
//            x = a + i * h;
//            if (i == 0 || i == n) {
//                sum1 += f.apply(x);
//            } else if (i % 2 == 1) {
//                sum2 += f.apply(x);
//            } else {
//                sum3 += f.apply(x);
//            }
//        }
//        return h / 3 * (sum1 + 4 * sum2 + 2 * sum3);
//    }
//
//    public static double parabolaMethod(Function<Double, Double> F, double a, double b, double c, double tol) {
//        while (Math.abs(F.apply(b) - c) > tol) {
//            double mid = (a + b) / 2;
//            if (F.apply(mid) - c < 0) {
//                a = mid; // Корень должен быть выше середины
//            } else {
//                b = mid; // Корень должен быть ниже середины
//            }
//        }
//
//        return (a + b) / 2; // Возвращаем корень как среднее значение
//    }
//
//    public static void main(String[] args) {
//        Function<Double, Double> f = x -> x * x;
//        double a = 0;
//        double b = 2;
//        double c = 1;
//        double tol = 1e-6;
//        double x_star = parabolaMethod((x, y) -> f.apply(x), a, b, c, tol);
//        System.out.println("The solution to the equation F(x) - c = 0 is x = " + x_star);
//    }
//}
import java.util.function.Function;

public class Integration {
    // Метод Симпсона для вычисления интеграла
    public static double simps(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum1 = f.apply(a) + f.apply(b); // Начальное и конечное значение
        double sum2 = 0; // Сумма для нечетных сегментов
        double sum3 = 0; // Сумма для четных сегментов

        for (int i = 1; i < n; i++) {
            double x = a + i * h; // Текущее значение
            if (i % 2 == 1) {
                sum2 += f.apply(x); // Нечетные сегменты
            } else {
                sum3 += f.apply(x); // Четные сегменты
            }
        }

        return h / 3 * (sum1 + 4 * sum2 + 2 * sum3); // Метод Симпсона
    }

    // Метод парабол (бисекция) для решения уравнения F(x) - c = 0
    public static double parabolaMethod(Function<Double, Double> F, double a, double b, double c, double epsilon) {
        while (Math.abs(F.apply((a + b) / 2) - c) > epsilon) { // Проверка точности
            double mid = (a + b) / 2; // Середина интервала
            double value = F.apply(mid);

            if (value - c < 0) { // Если значение меньше константы
                a = mid; // Обновляем нижнюю границу
            } else { // Если значение больше или равно константе
                b = mid; // Обновляем верхнюю границу
            }
        }

        return (a + b) / 2; // Возвращаем приближенное значение корня
    }

    public static void main(String[] args) {
        Function<Double, Double> f = (x) -> x/Math.exp(x); // Пример функции

        double a = 0;
        double b = 2;
        double c = 1; // Константа
        double epsilon = 1e-6; // Точность
        int n = 1000; // Число сегментов для метода Симпсона

        // Функция F(x) - интеграл методом Симпсона
        Function<Double, Double> F = (x1) -> simps(f, a, x1, n);

        // Метод парабол для решения уравнения F(x) - c = 0
        double root = parabolaMethod(F, a, b, c, epsilon); // Используем метод бисекции

        System.out.println("Корень уравнения F(x) - c = 0: " + root);
    }
}
