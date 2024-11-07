package chisl.task2;

import java.io.*;
import java.util.*;

public class AitkenScheme {

    public static double aitkenScheme(double[] x, double[] y, double xp) {
        int n = x.length;
        double[][] p = new double[n][n];
        for (int j = 0; j < n; j++) {
            p[j][j] = y[j];
            for (int i = j - 1; i >= 0; i--) {
                if(x[j]-x[i]==0){
                    System.exit(0);
                }
                if (i + 1 < n && j - 1 >= 0) {
                    p[i][j] = ((xp - x[i]) * p[i + 1][j] - (xp - x[j]) * p[i][j - 1]) / (x[j] - x[i]);
                }
            }
        }
        return p[0][n-1];
    }

    public static void main(String[] args) throws IOException {

        File inputFile = new File("C:/Users/kupts/IdeaProjects/untitled/src/chisl/task2/input.txt");
        File outputFile = new File("C:/Users/kupts/IdeaProjects/untitled/src/chisl/task2/output.txt");


        Scanner scanner = new Scanner(inputFile);


        String[] xValues = scanner.nextLine().split(" ");
        double[] x = new double[xValues.length];
        for (int i = 0; i < xValues.length; i++) {
            x[i] = Double.parseDouble(xValues[i]);
        }


        String[] yValues = scanner.nextLine().split(" ");
        double[] y = new double[yValues.length];
        for (int i = 0; i < yValues.length; i++) {
            y[i] = Double.parseDouble(yValues[i]);
        }


        // Записываем результат в файл
        System.out.println(x[x.length-1]);
        FileWriter writer=new FileWriter(outputFile);
        for(double i=x[0];i<=x[x.length-1];i+=0.007){
            double temp=aitkenScheme(x,y,i);
            writer.write(String.valueOf(temp)+"\n");
            System.out.println("В точке " + i + " значение функции (Схема Эйткена): " + aitkenScheme(x,y,i));
        }

        writer.close();

        System.out.println("Результат вычислений записан в файл output.txt");
    }
}
