import java.util.function.Function;

public class ParabolaMethod {

    // Метод парабол для решения уравнения F(x) = 0
    public static double parabolaMethod(Function<Double, Double> f, double x1, double x2, double x3, double epsilon, int maxIterations) {
        for (int i = 0; i < maxIterations; i++) {
            // Значения функции в трех точках
            double fx1 = f.apply(x1);
            double fx2 = f.apply(x2);
            double fx3 = f.apply(x3);

            // Построение параболы через три точки
            double numerator = (x2 - x1) * (fx3 - fx2) - (x3 - x2) * (fx2 - fx1);
            double denominator = (x3 - x1) * (fx3 - fx2) - (x3 - x2) * (fx2 - fx1);

            if (denominator == 0) {
                throw new RuntimeException("Парабола не может быть построена.");
            }

            // Пересечение параболы с осью x
            double xNew = x2 - (numerator / denominator);

            if (Math.abs(f.apply(xNew)) < epsilon) { // Если значение функции близко к нулю
                return xNew; // Корень найден
            }

            // Обновление трех точек
            x1 = x2;
            x2 = x3;
            x3 = xNew;
        }

        throw new RuntimeException("Метод парабол не сошелся.");
    }

    public static void main(String[] args) {
        // Пример функции для тестирования метода парабол
        Function<Double, Double> f = (x) -> x * x - 4; // Уравнение с корнями

        double x1 = 0;
        double x2 = 1;
        double x3 = 2;
        double epsilon = 1e-6; // Точность
        int maxIterations = 100;

        double root = parabolaMethod(f, x1, x2, x3, epsilon, maxIterations);

        System.out.println("Корень уравнения: " + root);
    }
}
