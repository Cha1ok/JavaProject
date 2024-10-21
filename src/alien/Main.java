package alien;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        File file=new File("C:/Users/kupts/IdeaProjects/untitled/src/alien/input.txt");
        Scanner sc=new Scanner(file);
        int[] a=new int[3];
        for(int i=0;i<3;i++){
            a[i]=sc.nextInt();
        }
        double SOwner=Math.PI*Math.pow(a[0],2);
        double SumOfS=Math.PI*Math.pow(a[1],2)+Math.PI*Math.pow(a[2],2);
        Writer writer=new FileWriter("C:/Users/kupts/IdeaProjects/untitled/src/alien/output.txt");
        if(SumOfS<SOwner){
            writer.write("YES");
        }
        else
            writer.write("NO");
        writer.close();
    }
}
