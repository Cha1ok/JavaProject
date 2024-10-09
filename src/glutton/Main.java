package glutton;

import java.util.*;
import java.util.function.*;
public class Main {
    public static void main(String[] args) {
        ArrayList<Pair<Integer, String>> list = new ArrayList<Pair<Integer, String>>();

        Scanner sc = new Scanner(System.in);
        Scanner ss=new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int k=sc.nextInt();
            String v=sc.nextLine();
            Pair<Integer,String>pair=new Pair<>(k,v);
            list.add(pair);
        }
        double temp=1;
        for(int i=0;i< list.size();i++){
            temp=temp- (double) 1 /(list.get(i).getKey());
            if(temp<0){
                System.out.print(list.get(i).getValue());
                break;
            }
        }

    }
}