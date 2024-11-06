package Matrix;

import java.util.*;
public class Matrix<T> {
    T[][]a;

    Matrix(T[][] a){
        this.a=a;
    }
   void print(){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[i].length;j++){
                System.out.println(a[i][j]+" ");
            }
        }
   }
}
