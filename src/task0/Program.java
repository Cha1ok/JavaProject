package task0;
import java.util.*;
public class Program{

     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("size of matrix:");
        int n = sc.nextInt();

        Matrix matrix = new Matrix(n);
        matrix.fillMatrix();
        matrix.print();
        matrix.gausMethod();
        matrix.solution();
        matrix.printSolution();
    }
}