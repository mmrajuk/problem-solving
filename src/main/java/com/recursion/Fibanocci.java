package com.recursion;

import java.util.LinkedHashMap;
import java.util.Map;

public class Fibanocci {

    private static Map<String,Integer> fibCallCounter = new LinkedHashMap<>();
    //iterativeReverse
    private static int fibonacciIterative(int n){

        int F1 = 0;
        int F2 = 1;
        int F = 0;

        for(int i=2;i<=n; i++){
            F = F1 + F2;
            F1 = F2;
            F2 = F;
        }
        return F;
    }

    //recursive
    private static int fibonacciRecursive(int n){
        fibCallCounter.put("F("+n+")",
                fibCallCounter.getOrDefault("F("+n+")",0)+1);
        if(n<=1)
            return n;
        int result1 =  fibonacciRecursive(n-1);
        //System.out.println("Fibonacci of F("+n+"-1):"+ result1);
        int result2 = fibonacciRecursive(n-2);
        //System.out.println("Fibonacci of F("+n+"-2):"+ result2);
        return result1 + result2;
        //return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
    }

    public static void main(String[] args) {
        System.out.println("Fibonacci Iterative Result :"+ fibonacciIterative(5));
        System.out.println("Fibonacci Recursive Result :"+ fibonacciRecursive(10));

        System.out.println("Recursive call counts :");
        fibCallCounter.forEach((k,v)-> System.out.println(k+" total call count:"+ v));
    }
}
