package tst;

import java.util.Scanner;
import java.util.function.Function;

public class SimpsonP {

    // Simpson's method for integrating with given accuracy
    public static double simpsonsIntegration(Function<Double, Double> f, double a, double b, double epsilon_1) {
        int n = 1000; // Initial number of segments
        double previousIntegral = 0;
        double currentIntegral = calculateSimpson(f, a, b, n);

        while (Math.abs(currentIntegral - previousIntegral) > epsilon_1) {
            previousIntegral = currentIntegral;
            n *= 2; // Double the segments to increase accuracy
            currentIntegral = calculateSimpson(f, a, b, n);
        }

        return currentIntegral; // Return the final integral
    }

    public static double calculateSimpson(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum1 = f.apply(a) + f.apply(b); // Initial and final values
        double sum2 = 0;
        double sum3 = 0;

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            if (i % 2 == 1) {
                sum2 += f.apply(x); // Odd segments
            } else {
                sum3 += f.apply(x); // Even segments
            }
        }

        return h / 3 * (sum1 + 4 * sum2 + 2 * sum3); // Simpson's rule
    }

    // Muller's method to solve the equation F(x) - c = 0
    public static double mullerMethod(Function<Double, Double> F, double x0, double x1, double x2, double epsilon_2, int maxIterations) {
        double x = x2;
        double h1 = x1 - x0;
        double h2 = x2 - x1;

        for (int i = 0; i < maxIterations; i++) {
            double fx = F.apply(x); // Function value at 'x'
            if (Math.abs(fx) < epsilon_2) { // If it's close enough to zero
                return x; // Found the root
            }

            double delta1 = (F.apply(x1) - F.apply(x0)) / h1;
            double delta2 = (F.apply(x2) - F.apply(x1)) / h2;

            double a = (delta2 - delta1) / (h2 + h1);
            double b = a * h2 + delta2;
            double c = F.apply(x2);

            double D = Math.sqrt(b * b - 4 * a * c);
            double E = b < 0 ? -D : D;

            double h = -2 * c / (b + E);

            double x_temp = x;
            x = x2 + h;

            if (Math.abs(x - x_temp) < epsilon_2) {
                return x;
            }

            x0 = x1;
            x1 = x2;
            x2 = x;

            h1 = x1 - x0;
            h2 = x2 - x1;
        }

        throw new RuntimeException("Muller method did not converge.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter lower limit (a): ");
        double a = scanner.nextDouble();



        System.out.print("Enter the constant (c): ");
        double c = scanner.nextDouble();

        double epsilon_1 = 0.001; // Accuracy for Simpson's method
        double epsilon_2 = 0.0001; // Accuracy for Muller's method
        int maxIterations = 100;

        // Example function
        Function<Double, Double> f = (x) ->Math.pow(x,2)-Math.log(x)-2*Math.cos(x);

        // Simpson's method for integrating with given accuracy

        // Function F(x) = Integral from 'a' to 'x'
        Function<Double, Double> F = (x) -> calculateSimpson(f, a, x, 1000)-c;


        try {
            // Find the root of the equation F(x) - c = 0 using Muller's method
            double root = mullerMethod(F, 3, 1, 4, epsilon_2, maxIterations);

            double integral= simpsonsIntegration(f,a,root,epsilon_1);

            System.out.println("Integral: " + integral);
            System.out.println("Root of the equation F(x) - c = 0: " + root);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
