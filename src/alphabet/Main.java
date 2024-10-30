package alphabet;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        File file= new File("C:/Users/kupts/IdeaProjects/untitled/src/alphabet/input.txt");
        Scanner sc=new Scanner(file);
        String data= sc.nextLine();
        char[] word=new char[26];
        word=data.toCharArray();
    }
}
