package chisl.task4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GaussSeidel {
    private static final double EPSILON = 1e-8;
    private static final int MAX_ITERATIONS = 50;
    private static final String INPUT_FILE = "C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\chisl\\task4/input.txt";
    private static final String OUTPUT_FILE = "C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\chisl\\task4/output.txt";

    private static double f1_1(double x1, double x2, double x3) {
        return Math.pow(Math.sin(2*x1+Math.pow(x2,2)-6),2)+Math.cos(x2*x3)-1;
    }

    private static double f2_1(double x1, double x2, double x3) {
        return Math.exp(Math.cos((3.14*x1/2))+Math.pow(x3,2))+2*Math.pow(x1,3)+Math.pow(x2,2)-7;
    }

    private static double f3_1(double x1, double x2, double x3) {
        return 3*Math.pow(x1,2)-2*Math.pow(x2,2)+Math.pow(x3+1,2)+4;
    }

    private static double f1_2(double x1, double x2, double x3) {
        return 2*x1+3*x2-x3-5;
    }

    private static double f2_2(double x1, double x2, double x3) {
        return 3*x1-x2+4*x3-13;
    }

    private static double f3_2(double x1, double x2, double x3) {
        return 5*x1-2*x2+2*x3-7;
    }

    private static double f1_3(double x1, double x2, double x3) {
        return x1 * x1 + x2 + x3 - 10;
    }

    private static double f2_3(double x1, double x2, double x3) {
        return x2 * x2 - x1 + x3 - 5;
    }

    private static double f3_3(double x1, double x2, double x3) {
        return x3 * x3 - x1 - x2 + 4;
    }

    private static double newton(double x, double fValue, double derivativeValue) {
        if (Math.abs(derivativeValue) < EPSILON) {
            return Double.NaN;
        }
        return x - (fValue / derivativeValue);
    }

    private static boolean isUnique(List<double[]> roots, double x1, double x2, double x3) {
        for (double[] root : roots) {
            if (Math.abs(root[0] - x1) < EPSILON &&
                    Math.abs(root[1] - x2) < EPSILON &&
                    Math.abs(root[2] - x3) < EPSILON) {
                return false;
            }
        }
        return true;
    }

    private static double[] readIntervalAndStep(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            double step = Double.parseDouble(reader.readLine().trim());

            String intervalLine = reader.readLine();
            String[] interval = intervalLine.split(",");
            if (interval.length != 2) {
                throw new IllegalArgumentException("Некорректный формат файла. Ожидается 2 значения интервала.");
            }

            double choice=Double.parseDouble(reader.readLine());

            double start = Double.parseDouble(interval[0].trim());
            double end = Double.parseDouble(interval[1].trim());
            return new double[]{step, start, end,choice};
        }
    }

    private static void writeRootsToFile(String filename, List<double[]> roots) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (double[] root : roots) {
                writer.write(String.format("%.6f, %.6f, %.6f%n", root[0], root[1], root[2]));
            }
            System.out.println("Корни успешно записаны в файл: " + filename);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            double[] data = readIntervalAndStep(INPUT_FILE);
            double step = data[0];
            double intervalStart = data[1];
            double intervalEnd = data[2];
            double choice=data[3];

            List<double[]> roots = new ArrayList<>();

            for (double initialX1 = intervalStart; initialX1 <= intervalEnd; initialX1 += step) {
                for (double initialX2 = intervalStart; initialX2 <= intervalEnd; initialX2 += step) {
                    for (double initialX3 = intervalStart; initialX3 <= intervalEnd; initialX3 += step) {
                        double x1 = initialX1, x2 = initialX2, x3 = initialX3;
                        double x1Prev, x2Prev, x3Prev;
                        int iteration = 0;

                        boolean validSolution = true;

                        do {
                            iteration++;

                            x1Prev = x1;
                            x2Prev = x2;
                            x3Prev = x3;

                            double f1Value, f2Value, f3Value, df1Value, df2Value, df3Value;
                            if (choice == 1) {
                                f1Value = f1_1(x1, x2Prev, x3Prev);
                                df1Value = 4*Math.cos(2*x1+Math.pow(x2Prev,2)-6);
                                f2Value = f2_1(x1, x2, x3Prev);
                                df2Value = 2*x2;
                                f3Value = f3_1(x1, x2, x3);
                                df3Value = 2*x3+2;
                            } else if (choice == 2) {
                                f1Value = f1_2(x1, x2Prev, x3Prev);
                                df1Value = Math.cos(x1);
                                f2Value = f2_2(x1, x2, x3Prev);
                                df2Value = 2 * x2;
                                f3Value = f3_2(x1, x2, x3);
                                df3Value = x1 * x2;
                            } else {
                                f1Value = f1_3(x1, x2Prev, x3Prev);
                                df1Value = 2 * x1;
                                f2Value = f2_3(x1, x2, x3Prev);
                                df2Value = 2 * x2;
                                f3Value = f3_3(x1, x2, x3);
                                df3Value = 2 * x3;
                            }

                            x1 = newton(x1, f1Value, df1Value);
                            if (Double.isNaN(x1)) {
                                validSolution = false;
                                break;
                            }

                            x2 = newton(x2, f2Value, df2Value);
                            if (Double.isNaN(x2)) {
                                validSolution = false;
                                break;
                            }

                            x3 = newton(x3, f3Value, df3Value);
                            if (Double.isNaN(x3)) {
                                validSolution = false;
                                break;
                            }

                        } while ((Math.abs(x1 - x1Prev) > EPSILON ||
                                Math.abs(x2 - x2Prev) > EPSILON ||
                                Math.abs(x3 - x3Prev) > EPSILON) &&
                                iteration < MAX_ITERATIONS);

                        if (validSolution && iteration < MAX_ITERATIONS && isUnique(roots, x1, x2, x3)) {
                            roots.add(new double[]{x1, x2, x3});
                            System.out.printf("Found root: x1 = %.6f, x2 = %.6f, x3 = %.6f%n", x1, x2, x3);
                        }
                    }
                }
            }

            writeRootsToFile(OUTPUT_FILE, roots);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
