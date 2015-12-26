package net.leojay.mechanical.main;

import static net.leojay.mechanical.tools.Print.*;

/**
 * Project:net.leojay.mechanical.main
 * <p>
 * Author:Crazy_LeoJay
 * Time:下午3:32
 */
public class C {

    public static void main(String[] args){
        double a = 12.4321;
        double b = 12.49999999;
        int c = (int)b;
        double v = 6e7;
        System.out.println(" " + (int)a + "和" + (int)b);
        System.out.println(" " + (int)v);
        System.out.println(v == 0);
        System.out.println(Math.pow(2,3));
        if (fun(false) & fun(false) )
            println("true");
    }

    static boolean fun(boolean b){
        println("ceshi");
        return b;
    }
}
