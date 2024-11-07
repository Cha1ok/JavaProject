package amount_two;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        File file=new File("C:/Users/kupts/IdeaProjects/untitled/src/amount_two/input.txt");
        FileReader fileReader=new FileReader(file);
        Scanner sc=new Scanner(fileReader);
        int x=sc.nextInt();
        int count=0;
        for(int i=1;i<=1500/2;i++){
            for(int j=i;j<=1500/2;j++){
                for(int k=j;k<=1500/2;k++){
                    for(int e=k;e<=1500/2;e++){
                        if(i+j+k+e==x){
                            count++;
                        }
                    }
                }
            }
        }
        Writer writer=new FileWriter("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\amount_two/output.txt");
        writer.write(String.valueOf(count));
        writer.close();
    }
}
