package ACM;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        ArrayList<String>data=new ArrayList<>();
        ArrayList<String>name=new ArrayList<>();
        FileReader fin=new FileReader("C:/Users/kupts/IdeaProjects/untitled/src/ACM/input.txt");
        Scanner sc=new Scanner(fin);
        while (sc.hasNextLine()){
            data.add(sc.nextLine());
        }
        for(int i=0;i<data.size();i++){
            if(i!=0){
                name.add(data.get(i));
            }
        }
        Collections.sort(name);
        System.out.println(data);
        String result = data.get(0) + ": " + String.join(", ", name); // Имена перечислить через запятую
        PrintWriter pw = new PrintWriter(new File("C:/Users/kupts/IdeaProjects/untitled/src/ACM/output.txt"));
        pw.print(result);
        pw.close();
    }
}
