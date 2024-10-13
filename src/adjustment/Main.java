package adjustment;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Reading the file
        File file = new File("C:/Users/kupts/IdeaProjects/untitled/src/adjustment/input.txt");
        Scanner sc = new Scanner(file);

        // Read the first line of the file
        String data = sc.nextLine();
        String[] st = data.split("");
        sc.close();  // Closing the scanner to avoid resource leaks

        // Display the fourth character (for debugging purposes)
        System.out.println(st[4]);

        int answer = 0;

        // Adjusting the logic to compare strings with .equals()
        if (st[1].equals("-")) {
            if (st[0].equals("x")) {
                answer = Integer.parseInt(st[2]) + Integer.parseInt(st[4]);
            } else if (st[2].equals("x")) {
                answer = Integer.parseInt(st[4]) - Integer.parseInt(st[0]);
            }
        } else if (st[1].equals("+")) {
            if (st[0].equals("x")) {
                answer = Integer.parseInt(st[4]) - Integer.parseInt(st[2]);
            } else if (st[2].equals("x")) {
                answer = Integer.parseInt(st[4]) - Integer.parseInt(st[0]);
            }
        }

        // Output the result
        System.out.println(answer);

        // Writing the answer to the output file
        Writer writer = new FileWriter("C:/Users/kupts/IdeaProjects/untitled/src/adjustment/output.txt");
        writer.write(Integer.toString(answer));
        writer.close();  // Closing the writer to avoid resource leaks
    }
}
