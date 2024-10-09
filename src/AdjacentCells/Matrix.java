package AdjacentCells;

import java.util.*;
public class Matrix {
    int[][] a;
    Matrix(){
        a=new int[8][8];
    }
    void fillMatrix(){
        int count=1;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                a[i][j]=count++;
            }
        }

    }
    void printMatrix(){
        for(int i=0;i<8;i++){
            System.out.println();
            for(int j=0;j<8;j++){
                System.out.print(a[i][j]+ " ");
            }
        }
    }
    ArrayList<Integer> foundAdjacent(int x){
        ArrayList<Integer> solution=new ArrayList<>();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(a[i][j]==x){
                    if(i-1>=0){
                        solution.add(a[i-1][j]);
                    }
                    if(i+1<8){
                        solution.add(a[i+1][j]);
                    }
                    if(j-1>=0){
                        solution.add(a[i][j-1]);
                    }
                    if(j+1<8){
                        solution.add(a[i][j+1]);
                    }
                }
            }
        }
        return solution;
    }
}
