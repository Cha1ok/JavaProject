public class test {
    public static void main(String[] args) {
        String num="10133890";
        int n = num.length();
        for (int i=n-1;i>=0;i--) {
            char a=num.charAt(i);
            int b=Character.getNumericValue(a);
            System.out.println(b);
            if (b%2!=0) {
                System.out.println( num.substring(0,i+1));
            }
        }
        System.out.println("");
    }
}