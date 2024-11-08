package arithmetic_progression;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        File file = new File("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\arithmetic_progression/input.txt");
        FileReader fileReader=new FileReader(file);
        FileWriter writer=new FileWriter("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\arithmetic_progression/output.txt");
        Scanner sc= new Scanner(fileReader);
        ArrayList<Integer>a=new ArrayList<>();
        int count=0;
        while (count<2){
            a.add(Integer.parseInt(sc.next()));
            count++;
        }
        int n=Integer.parseInt(sc.next());
        int dif=a.get(1)-a.get(0);
        int answer=0;
        for(int i=0;i<=n;i++){
            if(i==n){
                answer=a.get(i);
            }
            int k=a.get(i)+dif;
            a.add(k);
        }
        writer.write(String.valueOf(answer));
        writer.close();
    }
}
