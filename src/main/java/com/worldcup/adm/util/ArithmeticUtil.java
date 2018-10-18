package com.worldcup.adm.util;

public class ArithmeticUtil {

    public static void main(String[] args) {
        System.out.println(factorial(0));
    }

    /**
     * 计算n的阶乘，即： n! = n * (n-1) * (n-2) * ... * 2 * 1
    * */
    public static long factorial(int n) {
        long nf = 1;
        while (n > 0) {
            nf = nf * n--;
        }
        return nf;
    }

    //排列 A(n,m) = n!/(n-m)!
    public static long array() {
        long result = 1;
        return result;
    }
    //组合 C(n,m) = A(n,m)/m!
}
