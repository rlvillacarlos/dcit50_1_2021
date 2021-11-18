package edu.cvsu.dcit50;

import java.util.Arrays;

/**
 *
 * @author rlvillacarlos
 */
public class CounterClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Counter counters[] ={new Counter(), new Counter(), new Counter()};
        
        counters[0].increment();
        counters[1].increment();
        System.out.println(counters[0]);
        System.out.println(counters[1]);
        System.out.println(counters[0].equals(counters[1]));
        //counters[0] == counters[1]
        System.out.println(counters[0].compareTo(counters[1]));
        
        System.out.println("");
        
        counters[0].increment();
        System.out.println(counters[0]);
        System.out.println(counters[1]);
        System.out.println(counters[0].equals(counters[1]));
        //counters[0] > counters[1]
        System.out.println(counters[0].compareTo(counters[1]));
        
        System.out.println("");
        
        counters[2].increment();
        counters[2].increment();
        counters[2].increment();
        System.out.println(counters[0]);
        System.out.println(counters[2]);
        System.out.println(counters[0].equals(counters[2]));
        //counters[0] < counters[2]
        System.out.println(counters[0].compareTo(counters[2]));
        
        System.out.println("\nOriginal Order");
        for (Counter c: counters){
            System.out.println(c);
        }
        
        System.out.println("\nSorted Order");
        Arrays.sort(counters);
        
        for (Counter c: counters){
            System.out.println(c);
        }
        
    }
    
}
