package Matrix;

import java.util.*;
public class Main {
    public static void main(String [] args){
        Scanner sc=new Scanner(System.in);
        Integer[][]a={{1,2,3,4},{1,2,3,4}};
        Matrix<Integer> matrix=new Matrix<>(a);
        matrix.print();
        }
    }

