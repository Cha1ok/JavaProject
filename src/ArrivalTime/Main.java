package ArrivalTime;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        File file=new File("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\ArrivalTime/input.txt");
        FileReader fileReader=new FileReader(file);
        Scanner sc = new Scanner(fileReader);
        FileWriter writer=new FileWriter("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\ArrivalTime/output.txt");
        String[] dataOp=sc.nextLine().split(":");
        int[] timeOp=new int[2];
        timeOp[0]=Integer.parseInt(dataOp[0]);
        timeOp[1]=Integer.parseInt(dataOp[1]);
        int[] dataAr=new int[2];
        dataAr[0]=sc.nextInt();
        dataAr[1]=sc.nextInt();
        int[] answer=new int[2];
        answer[0]=dataAr[0]+timeOp[0];
        answer[1]=dataAr[1]+timeOp[1];
       writer.write(String.valueOf(answer[0])+":"+String.valueOf(answer[1]));
        writer.close();

    }
}
