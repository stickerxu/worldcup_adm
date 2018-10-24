package com.worldcup.adm.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArithmeticUtil {

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
    //组合 C(n,m) = A(n,m)/m!
}
