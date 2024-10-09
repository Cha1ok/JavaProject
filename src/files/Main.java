package files;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String inputFile = "C:/Users/kupts/OneDrive/Рабочий стол/a.txt";
        String outputFile = "C:/Users/kupts/OneDrive/Рабочий стол/b.txt";

        try (FileReader reader = new FileReader(inputFile);
             FileWriter writer = new FileWriter(outputFile)) {

            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
        }
    }
}