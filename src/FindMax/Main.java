package FindMax;

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        ArrayList<Integer>a=new ArrayList<>();
        File file=new File("C:/Users/kupts/IdeaProjects/untitled/src/FindMax/input.txt");
        FileReader fin=new FileReader(file);
        Scanner sc=new Scanner(fin);
        String str;
        str=sc.nextLine();
        StringTokenizer stringTokenizer=new StringTokenizer(str," ");
        while(stringTokenizer.hasMoreTokens()){
            int s=Integer.valueOf(stringTokenizer.nextToken());
            a.add(s);
        }
        int max= a.get(0);
        for(int i=0;i<a.size();i++){
            if(a.get(i)>max){
                max=a.get(i);
            }
        }
        FileWriter writer=new FileWriter("C:/Users/kupts/IdeaProjects/untitled/src/FindMax/output.txt");
        writer.write(String.valueOf(max));
        writer.close();
    }
}
