package Arrange;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String [] args)throws IOException{
        FileReader fileReader=new FileReader("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\Arrange/input.txt");
        FileWriter writer=new FileWriter("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\Arrange/output.txt");
        Scanner sc = new Scanner(fileReader);
        String[] str=sc.nextLine().split(" ");
        char[] ch1=str[0].toCharArray();
        char[] ch2=str[1].toCharArray();
        List<Character>list1=new String(ch1).chars().mapToObj(c->(char)c).collect(Collectors.toList());
        List<Character>list2=new String(ch2).chars().mapToObj(c->(char)c).collect(Collectors.toList());
        List<Character> sortedList1 = list1.stream().sorted().collect(Collectors.toList());
        List<Character>sortedList2=list2.stream().sorted().collect(Collectors.toList());
        if(sortedList1.equals(sortedList2)){
            writer.write("YES");
            writer.close();
        }
        else {
            writer.write("NO");
            writer.close();
        }
    }
}
