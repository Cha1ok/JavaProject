package alcohol;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] agrs)throws IOException{
        File file=new File("C:/Users/kupts/IdeaProjects/untitled/src/alcohol/input.txt");
        FileReader fileReader=new FileReader(file);
        Scanner sc=new Scanner(fileReader);
        long n=file.length();
        int[]a=new int[3];
        System.out.println(n);
        for(int i=0;i<3;i++){
            a[i]=sc.nextInt();
        }
        int c=a[0];
        int h=a[1];
        int o=a[2];
        int answer=0;
        while(c>=0 && h>=0 && o>=0){
            c-=2;
            h-=6;
            o-=1;
            if(c>=0 && h>=0 && o>=0){
                answer+=1;
            }
        }
        Writer writer=new FileWriter("C:/Users/kupts/IdeaProjects/untitled/src/alcohol/output.txt");
        writer.write(String.valueOf(answer));
        writer.close();
    }
}
