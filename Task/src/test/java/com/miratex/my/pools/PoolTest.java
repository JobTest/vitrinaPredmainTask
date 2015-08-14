package com.miratex.my.pools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Саша on 14.08.2015.
 */
public class PoolTest {

    List<MyThread> threads;

    public PoolTest(){
        threads = Arrays.asList(new MyThread("5,3"), new MyThread("2,8"), new MyThread("4,4"));
    }
    public PoolTest(int pool){
        threads = new ArrayList<>(pool);
        for (int p=0; p<pool; p++)
            threads.add(new MyThread("pool-"+p));
    }

    public static void main(String[] args) {
        /* ********************************************************** */
        int[] numbers = {4,8,9,0,1,5,6,7,3,2};
        int    number = 1;
        int      find = -1;
        System.out.println( "("+number+") " + find(numbers,number,find) );

        /* ********************************************************** */
        PoolTest test = new PoolTest(10);
//        System.out.println( test.func(5,3) );

        for (MyThread thread:test.threads)
            thread.start();
        System.out.println( test.threads.get(0).funcc(1,7) );
        System.out.println( test.threads.get(1).funcc(2,6) );
        System.out.println( test.threads.get(2).funcc(3,5) );
        System.out.println( test.threads.get(2).funcc(4,4) );
        System.out.println( test.threads.get(2).funcc(5,3) );
        System.out.println( test.threads.get(2).funcc(6,2) );
        for (MyThread thread:test.threads)
            thread.stopThread();
    }

    public static int find(int[] numbers, int number, int find){
//        int find = -1;
//        for (int n=0; n<numbers.length; n++) {
//            if(numbers[n] == number) {
//                find = n;
//                return find;
//            }
//        }
//        return find;

//        boolean isFind = false;
//        int      count = -1;
//        while(!isFind)
//            if(numbers[++count] == number)
//                isFind = true;
//        return count;

        return numbers[++find]!=number ? find(numbers,number,find) : find;
    }

    public synchronized int func(int number, int step){
        try{ Thread.sleep(500); }catch(InterruptedException e){}
        return 1<step ? number*func(number,step-1) : number;
    }


    class MyThread extends Thread {
        public MyThread(String name){
            super(name);
        }

        @Override
        public void run(){
            synchronized(this){
                while (!isStop){
                    try { wait(); } catch(InterruptedException e){}
                    func=func(number,step);
                }
            }
        }

        public void stopThread(){
            synchronized (this){
                isStop = true;
                notify();
            }
        }
        public int funcc(int number, int step){
            this.number = number;
            this.step = step;
            synchronized(this){
                if(getState().toString().equals("WAITING")) {
                    notify();
                }
            }
            try { Thread.sleep(1000); }catch (InterruptedException e){}
            return func;
        }

        private boolean isStop = false;
        private int number;
        private int step;
        private int func;
    }
}

