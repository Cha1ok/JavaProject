

import java.io.*;
import java.util.Arrays;

public class AitkenScheme  {
    // Метод для вычисления по схеме Эйткена
    public static double aitkenScheme(double[] x, double[] y, double xp) {
        int n=x.length;
        double[][] p = new double[n][n];
        double[][] eps = new double[n][n];

        // Инициализируем таблицу p значениями y
        for (int j = 0; j < n; j++) {
            p[j][j] = y[j];
            for (int i = j - 1; i >= 0; i--) {
                if (i + 1 < n && j - 1 >= 0) { // Проверка, чтобы избежать выхода за границы массива
                    p[i][j] = ((xp - x[i]) * p[i + 1][j] - (xp - x[j]) * p[i][j - 1]) / (x[j] - x[i]);
                }

                // Рассчитываем разницу eps
                if (i > 0 && j - 1 >= 0) { // Проверка на границы для eps
                    eps[i][j] = Math.abs(p[i][j] - p[i - 1][j - 1]);
                }

                // Условие остановки, если разница меньше 0.001
                if (i > 0 && Math.abs(p[i][j] - p[i - 1][j - 1]) < 0.001) {
                    return Math.round(p[i][j] * 10000.0) / 10000.0; // Округляем до 4 знаков
                }
            }
        }

        // Поиск минимального eps и возвращение соответствующего значения
        double minEps = Arrays.stream(eps).flatMapToDouble(Arrays::stream).min().getAsDouble();

        for (int i = 0; i < eps.length; i++) {
            for (int j = 0; j < eps[i].length; j++) {
                if (eps[i][j] == minEps) {
                    return Math.round(p[i][j] * 10000.0) / 10000.0; // Округляем до 4 знаков
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        File file =new File("C:/Users/kupts/IdeaProjects/untitled/src/chisl/task2/input.txt");
        double[] x = {1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0}; // Узловые точки
        double[] y = {2.7182, 3.0041, 3.3201, 3.6692, 4.0552, 4.816, 4.9530, 5.4739, 6.0496, 6.6858, 7.3890}; // Значения функции
        double point = 1.53; // Точка для интерполяции

        double aitkenValue = aitkenScheme(x, y, point);
        System.out.println("В точке " + point + " значение функции (Схема Эйткена): " + aitkenValue);
    }
}
