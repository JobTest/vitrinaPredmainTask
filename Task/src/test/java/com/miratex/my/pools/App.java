package com.miratex.my.pools;

/**
 * Created by alexandr on 13.08.15.
 */
public class App {

    public int exponent(int number, int degree){
        try {
            Thread.sleep(500);
        } catch(InterruptedException e){}
        return 1<degree ? number*exponent(number,degree-1) : number;
    }

    public int number;
    public int degree;
}
