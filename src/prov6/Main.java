package prov6;

public class Main {
    public static void main(String[] args) {
        Vector<Integer> intVector = new Vector<>();
        intVector.add(10);
        intVector.add(20);

        Vector<Double> doubleVector = new Vector<>();
        doubleVector.add(3.14);
        doubleVector.add(2.71);

        System.out.println(intVector.get(0)); // Output: 10
        System.out.println(doubleVector.get(1)); // Output: 2.71

        intVector.removeLast();
        System.out.println(intVector.get(0)); // Output: 20

    }
}

