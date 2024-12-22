package chisl.task5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;

public class simpsonP {


    public static double simpsonsIntegration(Function<Double, Double> f, double a, double b, double epsilon_1, int n) {
        double previousIntegral = 0;
        double currentIntegral = calculateSimpson(f, a, b, n);

        while (Math.abs(currentIntegral - previousIntegral) > epsilon_1) {
            previousIntegral = currentIntegral;
            n *= 2;
            currentIntegral = calculateSimpson(f, a, b, n);
        }

        return currentIntegral;
    }

    public static double calculateSimpson(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum1 = f.apply(a) + f.apply(b);
        double sum2 = 0;
        double sum3 = 0;

        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            if (i % 2 == 1) {
                sum2 += f.apply(x);
            } else {
                sum3 += f.apply(x);
            }
        }

        return h / 3 * (sum1 + 4 * sum2 + 2 * sum3);
    }


    public static void main(String[] args) {

        File inputFile = new File("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\chisl\\task5\\input3.txt");

        try (Scanner scanner = new Scanner(inputFile)) {

            double a = scanner.nextDouble();
            double b = scanner.nextDouble();
            double c = scanner.nextDouble();
            int n = scanner.nextInt();

            double epsilon_1 = 0.001;

            Function<Double, Double> f1 = (x) -> Math.pow(x, 2) - Math.log(x) - 2 * Math.cos(x);
            Function<Double, Double> f2 = (x) -> 3 * Math.pow(x, 3) - 2 * Math.pow(x, 2) + x - 5;
            Function<Double, Double> f3 = (x) -> 1 / (1 + Math.pow(x, 2));


            double integral = simpsonsIntegration(f3, a, b, epsilon_1, n);

            File outputFile = new File("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\chisl\\task5\\output.txt");
            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write(String.valueOf(integral));
            }

            System.out.println("Integral: " + integral);

        } catch (IOException e) {
            System.out.println("Error reading or writing files: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error in calculations: " + e.getMessage());
        }
    }
}
