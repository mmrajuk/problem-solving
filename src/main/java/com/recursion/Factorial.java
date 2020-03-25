package com.recursion;

public class Factorial {

    private static int factorial(int n){
        //if (n>0) n * (n-1)
        //if n=0 return 1
        System.out.println("Calculating F("+n+")");
        if(n ==0)
            return 1;
        int result = n * factorial(n-1);
        System.out.println("Done F("+n+")"+result);
        return result;
    }

    public static void main(String[] args) {
        factorial(5);
    }
}
