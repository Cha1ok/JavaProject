package aPlusb;

import java.io.*;
import java.util.*;
public class Summ {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> numeric = new ArrayList<>();
        int s = 0;
        String str;
        File file=new File("C:/Users/kupts/IdeaProjects/untitled/src/aPlusb/input.txt");
        FileReader fin = new FileReader(file);
        Scanner sc = new Scanner(fin);
        str = sc.nextLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        while(file.length()>0){
            int a = Integer.valueOf(st.nextToken());
            numeric.add(a);

        }
        for (Integer aNumeric : numeric) {
            s += aNumeric;
        }
        FileWriter countStepFinish = new FileWriter("C:/Users/kupts/IdeaProjects/untitled/src/aPlusb/output.txt");
        countStepFinish.write(String.valueOf(s));
        countStepFinish.close();
    }
}