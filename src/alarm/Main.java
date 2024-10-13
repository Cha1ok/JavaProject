package alarm;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        File file = new File("C:/Users/kupts/IdeaProjects/untitled/src/alarm/input.txt");
        FileReader fileReader = new FileReader("C:/Users/kupts/IdeaProjects/untitled/src/alarm/input.txt");
        Scanner sc = new Scanner(fileReader);
        int[] a = new int[2];
        for (int i = 0; i < 2; i++) {
            a[i]=sc.nextInt();
        }
        int answer=a[1]-a[0];
        Writer writer = new FileWriter("C:/Users/kupts/IdeaProjects/untitled/src/alarm/output.txt");
        writer.write(String.valueOf(answer));
        writer.close();
    }
}
