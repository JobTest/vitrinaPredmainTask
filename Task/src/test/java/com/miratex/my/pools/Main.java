package com.miratex.my.pools;

/**
 * Created by alexandr on 13.08.15.
 */
public class Main {

    public static void main(String[] args){
        int[][] data = {{1,2,3,4,5,6,7,8,9},{9,8,7,6,5,4,3,2,1}};

        /* *********[ 1 ]********** */
        System.out.println("USULY:");
//        App app = new App();
//        for (int pool=0; pool<data[0].length; pool++)
//            System.out.println("(" + data[0][pool] + "+" + data[1][pool] + ") " + app.exponent(data[0][pool], data[1][pool]));

        /* *********[ 2 ]********** */
        Pools pools = new Pools(5);
        pools.init();
//        try{ Thread.sleep(800); } catch (InterruptedException e){}
        System.out.println("POOL:");
        for (int pool=0; pool<data[0].length; pool++)
//            pools.getPool(data[0][pool], data[1][pool]).poolContinue();
            pools.put(data[0][pool], data[1][pool]);

//        long start = System.currentTimeMillis();
        for (int pool=0; pool<data[0].length; pool++)
            pools.getPool2().poolContinue();
//        long end = System.currentTimeMillis();
//        System.out.println( ":" + (end-start) );

        pools.stop();
    }

}
