package AdjacentCells;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        FileReader fileReader=new FileReader("C:/Users/kupts/IdeaProjects/untitled/src/AdjacentCells/input.txt");
        Scanner sc=new Scanner(fileReader);
        int x=sc.nextInt();
        Matrix matrix=new Matrix();
        matrix.fillMatrix();
        matrix.printMatrix();
        ArrayList<Integer>solutoin;
        solutoin=matrix.foundAdjacent(x);
        Collections.sort(solutoin);
        Writer writer=new FileWriter("C:/Users/kupts/IdeaProjects/untitled/src/AdjacentCells/output.txt");
        for(int i=0;i<solutoin.size();i++){
            System.out.println(solutoin.get(i));
            writer.write(String.valueOf(solutoin.get(i))+" ");
        }
        writer.close();
    }
}
