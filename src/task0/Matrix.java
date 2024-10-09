package task0;
import java.util.Scanner;

class Matrix {
    int n;
    double[][] a;
    double[] x;
    Scanner sc = new Scanner(System.in);

    Matrix(int n) {
        this.n = n;
        a = new double[n][n+1];
        x = new double[n];
    }

    void fillMatrix() {
        System.out.println("augmented:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                a[i][j] = sc.nextDouble();
            }
        }
    }

    void print() {
        System.out.println("Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                System.out.printf("%8.3f ", a[i][j]);
            }
            System.out.println();
        }
    }

    void gausMethod() {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double ratio = a[j][i] / a[i][i];
                for (int k = 0; k < n + 1; k++) {
                    a[j][k] = a[j][k] - ratio * a[i][k];
                }
            }
        }
    }

    void solution() {
        x[n-1] = a[n-1][n] / a[n-1][n-1];
        for (int i = n - 2; i >= 0; i--) {
            x[i] = a[i][n];
            for (int j = i + 1; j < n; j++) {
                x[i] = x[i] - a[i][j] * x[j];
            }
            if(Math.abs(a[i][i])<Math.pow(10,-12)){

            }
            x[i] = x[i] / a[i][i];
        }
    }

    void printSolution() {
        System.out.println("Solution:");
        for (int i = 0; i < n; i++) {
            System.out.printf("x[%d] = %.3f\n", i, x[i]);
        }
    }

}
