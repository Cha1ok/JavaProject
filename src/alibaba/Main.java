package alibaba;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        File file=new File("C:/Users/kupts/IdeaProjects/untitled/src/alibaba/input.txt");
        Scanner sc=new Scanner(file);
        int n=sc.nextInt();
        int m=sc.nextInt();
        ArrayList<Integer>a=new ArrayList<>();
        for(int i=0;i<n;i++){
            a.add(sc.nextInt());
        }
        a.sort(Collections.reverseOrder());
        int sum=0;
        for(int i=0;i<m;i++){
            sum+=a.get(i);
        }
        Writer writer=new FileWriter("C:/Users/kupts/IdeaProjects/untitled/src/alibaba/output.txt");
        writer.write(String.valueOf(sum));
        writer.close();
    }
}
