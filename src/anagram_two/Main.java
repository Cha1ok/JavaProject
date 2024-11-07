package anagram_two;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String [] args)throws IOException {
        File file=new File("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\anagram_two/input.txt");
        FileReader fileReader=new FileReader(file);
        Scanner sc = new Scanner(fileReader);
        String str= sc.nextLine();
        String[] strt=str.split(" ");
        String str1=strt[0];
        String str2=strt[1];
        char[] ch1=str1.toCharArray();
        char[] ch2=str2.toCharArray();
        System.out.println(strt);
        FileWriter writer=new FileWriter("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\anagram_two/output.txt");
        for(int i=0;i<ch1.length;i++){
            System.out.println(ch1[i]);
            if(ch1[i]==ch2[i]){
                writer.write("NO");
                writer.close();
                System.exit(0);
            }
        }
        writer.write("Yes");
        writer.close();
    }
}
