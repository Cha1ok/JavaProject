package chisl.task4;

public class GaussSeidelStephenson {
    private static final double EPSILON = 1e-6;

    // Функции нелинейной системы
    private static double phi1(double x2, double x3) {
        return Math.cbrt(5 - x2 - x3); // x1 = (5 - x2 - x3)^(1/3)
    }

    private static double phi2(double x1, double x3) {
        return Math.sqrt((12 - x1 - x3) / 2); // x2 = sqrt((12 - x1 - x3) / 2)
    }

    private static double phi3(double x1, double x2) {
        return Math.sin(x1 + x2) + 2; // x3 = sin(x1 + x2) + 2
    }

    // Метод Стеффенсена для уточнения значения
    private static double steffensen(double xPrev, double x1, double x2) {
        double numerator = Math.pow(x1 - xPrev, 2);
        double denominator = x2 - 2 * x1 + xPrev;
        return xPrev - (denominator != 0 ? numerator / denominator : 0);
    }

    public static void main(String[] args) {
        // Начальные приближения
        double x1 = 1.0, x2 = 1.0, x3 = 1.0;
        double x1Prev, x2Prev, x3Prev; // Для хранения предыдущих значений
        int iteration = 0;

        // Итерационный процесс
        do {
            iteration++;

            x1Prev = x1;
            x2Prev = x2;
            x3Prev = x3;

            // Метод Зейделя с уточнением методом Стеффенсена
            double x1Temp1 = phi1(x2Prev, x3Prev);
            double x1Temp2 = phi1(x2Prev, x3Prev);
            x1 = steffensen(x1Prev, x1Temp1, x1Temp2);

            double x2Temp1 = phi2(x1, x3Prev);
            double x2Temp2 = phi2(x1, x3Prev);
            x2 = steffensen(x2Prev, x2Temp1, x2Temp2);

            double x3Temp1 = phi3(x1, x2);
            double x3Temp2 = phi3(x1, x2);
            x3 = steffensen(x3Prev, x3Temp1, x3Temp2);

            // Вывод текущих значений на каждой итерации
            System.out.printf("Iteration %d: x1 = %.6f, x2 = %.6f, x3 = %.6f%n", iteration, x1, x2, x3);

        } while (Math.abs(x1 - x1Prev) > EPSILON || Math.abs(x2 - x2Prev) > EPSILON || Math.abs(x3 - x3Prev) > EPSILON);

        // Вывод результата
        System.out.println("Solution:");
        System.out.printf("x1 = %.6f, x2 = %.6f, x3 = %.6f%n", x1, x2, x3);
    }
}
