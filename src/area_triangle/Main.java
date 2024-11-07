package area_triangle;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        File file =new File("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\area_triangle/input.txt");
        FileReader fileReader=new FileReader(file);
        Scanner sc=new Scanner(fileReader);
        FileWriter writer=new FileWriter("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\area_triangle/output.txt");
        ArrayList<Pair<Integer,Integer>> points=new ArrayList<>();
        String str= sc.nextLine();
        String[] str1=str.split(" ");
        for(int i=0;i<str1.length-1;i+=2){
            int tempx=Integer.parseInt(str1[i]);
            int tempy=Integer.parseInt(str1[i+1]);
            Pair<Integer,Integer>point=new Pair<>(tempx,tempy);
            points.add(point);
        }
        for(int i=0;i<points.size();i++){
            System.out.println(points.get(i).getXpoint()+" "+points.get(i).getYpoint());
        }
           double result=0.5*Math.abs(((points.get(1).getXpoint()-points.get(0).getXpoint())*(points.get(2).getYpoint()-points.get(0).getYpoint())
            -(points.get(2).getXpoint()-points.get(0).getXpoint())*(points.get(1).getYpoint()-points.get(0).getYpoint())));
        writer.write(String.valueOf(result));
        writer.close();
    }
}
