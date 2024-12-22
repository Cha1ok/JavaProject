package chisl.task3;

import java.util.*;
import java.io.*;
import java.util.function.Function;

public class StephensonMethod {

    public static double f1(double x) {
        return Math.pow(x, 3) - 6 * Math.pow(x, 2) + 11 * x - 6;
    }

    public static double f2(double x) {
        return Math.sin(x);
    }

    public static double f3(double x) {
        return Math.exp(x) - 3 * Math.pow(x, 2);
    }

    public static Double stephensonMethod(double x0, double tolerance, int maxIterations, Function<Double, Double> f) {
        double x = x0;
        int iteration = 0;

        while (iteration < maxIterations) {
            double fx = f.apply(x);
            double fxShifted = f.apply(x + fx);

            double denominator = fxShifted - fx;
            if (Math.abs(denominator) < 1e-12) {
                return null;
            }

            double xNext = x - Math.pow(fx, 2) / denominator;

            if (Math.abs(xNext - x) < tolerance) {
                return xNext;
            }

            x = xNext;
            iteration++;
        }

        return null;
    }

    public static List<Double> findAllRoots(double a, double b, double step, double tolerance, int maxIterations, Function<Double, Double> f) {
        List<Double> roots = new ArrayList<>();

        double x = a;
        while (x < b) {
            double xNext = x + step;

            if (f.apply(x) * f.apply(xNext) <= 0) {
                double initialGuess = (x + xNext) / 2;
                Double root = stephensonMethod(initialGuess, tolerance, maxIterations, f);

                if (root != null) {
                    boolean isUnique = roots.stream().noneMatch(r -> Math.abs(r - root) < tolerance);
                    if (isUnique) {
                        roots.add(root);
                    }
                }
            }

            x = xNext;
        }

        return roots;
    }

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\chisl\\task3/input1.txt");
        Scanner sc = new Scanner(fileReader);
        FileWriter writer = new FileWriter("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\chisl\\task3/output.txt");

        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double step = sc.nextDouble();
        int funcChoice = sc.nextInt();

        sc.close();

        Function<Double, Double> selectedFunction;
        switch (funcChoice) {
            case 1 -> selectedFunction = StephensonMethod::f1;
            case 2 -> selectedFunction = StephensonMethod::f2;
            case 3 -> selectedFunction = StephensonMethod::f3;
            default -> throw new IllegalArgumentException("Некорректный выбор функции!");
        }

        double tolerance = 1e-10;
        int maxIterations = 10000;

        List<Double> roots = findAllRoots(a, b, step, tolerance, maxIterations, selectedFunction);

        for (double root : roots) {
            writer.write(String.format("x = %.6f\n", root));
            System.out.printf("x = %.6f\n", root);
        }

        writer.close();
    }
}
