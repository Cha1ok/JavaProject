import java.util.Scanner;
import java.util.function.Function;

public class SimpsonParabolaSolver {

    
    public static double simpsonsIntegration(Function<Double, Double> f, double a, double b, double epsilon) {
        int n = 1000;
        double previousIntegral = 0;
        double currentIntegral = calculateSimpson(f, a, b, n);


        while (Math.abs(currentIntegral - previousIntegral) > epsilon) {
            previousIntegral = currentIntegral;
            n *= 2;
            currentIntegral = calculateSimpson(f, a, b, n);
        }

        return currentIntegral;
    }


    public static double calculateSimpson(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double integral = f.apply(a) + f.apply(b);

        double oddSum = 0;
        for (int i = 1; i < n; i += 2) {
            oddSum += f.apply(a + i * h);
        }

        double evenSum = 0;
        for (int i = 2; i < n; i += 2) {
            evenSum += f.apply(a + i * h);
        }

        integral += 4 * oddSum + 2 * evenSum;
        integral *= h / 3;
        return integral;
    }


    public static double parabolaMethod(Function<Double, Double> G, double x0, double epsilon, int maxIterations) {
        double x = x0;

        for (int i = 0; i < maxIterations; i++) {
            double gx = G.apply(x);

            if (Math.abs(gx) < epsilon) {
                return x; // Solution found
            }

            double derivative = (G.apply(x + 1e-7) - G.apply(x - 1e-7)) / 2e-7;

            if (derivative == 0) {
                throw new RuntimeException("Derivative is zero. Cannot proceed with Newton's method.");
            }

            x -= gx / derivative; // Update x
        }

        throw new RuntimeException("Newton's method did not converge.");
    }

//    public static double parabolaMethod(Function<Double, Double> F, double a, double b, double c, double epsilon) {
//        double equation=Math.abs((F.apply((a+b)/2)-c));
//        while ((F.apply((a + b) / 2) - c) > epsilon) {
//            double mid = (a + b) / 2;
//            if (F.apply(mid) - c > 0) {
//                a = mid;
//            } else {
//                b = mid;
//            }
//            System.out.println(a+b/2);
//        }
//
//        return (a + b) / 2;
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter lower limit (a): ");
        double a = scanner.nextDouble();

        System.out.print("Enter upper limit (x): ");
        double b = scanner.nextDouble();

        System.out.print("Enter the constant (c): ");
        double c = scanner.nextDouble();

        System.out.print("Enter the starting guess for the root (x0): ");
        double x0 = scanner.nextDouble();

//        System.out.print("Enter accuracy for Simpson's method (epsilon_1): ");
        double epsilon_1 = 0.001;
//
//        System.out.print("Enter accuracy for the Newton method (epsilon_2): ");
        double epsilon_2 = 0.0001;
//
//        System.out.print("Enter maximum number of iterations for Newton's method: ");
        int maxIterations = 100;


        Function<Double, Double> f = (x) -> x/(Math.pow(x,4)+4);


        double integral = simpsonsIntegration(f, a, b, epsilon_1);


        Function<Double, Double> F = (x1) -> calculateSimpson(f, a, x1, 1000);


        Function<Double, Double> G = (x1) -> F.apply(x1);

        try {

            double root = parabolaMethod(G, x0, epsilon_2,maxIterations);

            System.out.println("Integral: " + integral);
            System.out.println("Root of the equation F(x) - c = 0: " + root);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
//double Parabola(double x0, double eps, double h)
//{
//    double x;
//    x = x0;
//    while (f1(x) > eps)
//    {
//        x = x - h*(f(x+h) - f(x-h))/(2*(f(x+h)+f(x-h)-2*f(x)));
//    }
//
//    return x;
//}
