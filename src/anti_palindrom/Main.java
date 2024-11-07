package anti_palindrom;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\anti_palindrom/input.txt");
        FileReader fileReader = new FileReader(file);
        Scanner sc = new Scanner(fileReader);
        String str = sc.nextLine();
        char[] ch = str.toCharArray();
        Set<Character> symbols= new HashSet<>();
        FileWriter writer = new FileWriter("C:\\Users\\kupts\\IdeaProjects\\untitled\\src\\anti_palindrom/output.txt");
        int j=0;
        boolean palindrom=true;
        for(int i=str.length()-1;i>=0;i--){
            if(ch[j]!=ch[i]){
                palindrom=false;
            }
            symbols.add(ch[i]);
            j++;
        }
        String result = (symbols.size() == 1) ? "NO SOLUTION" :
                (!palindrom) ? str : str.substring(0, str.length()-1);
        writer.write(result);
        writer.close();
    }
}

