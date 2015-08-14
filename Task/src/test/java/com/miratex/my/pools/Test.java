package com.miratex.my.pools;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Саша on 14.08.2015.
 */
public class Test {

    public static void main(String[] args) {
        /* ********************************************************** */
        int[] numbers = {4,8,9,0,1,5,6,7,3,2};
        int    number = 1;
        int      find = -1;
        System.out.println( "("+number+") " + find(numbers,number,find) );

        /* ********************************************************** */
        System.out.println( func(5,3) );

        List<MyThread> threads = Arrays.asList(new MyThread("5,3"), new MyThread("2,8"), new MyThread("4,4"));
        for (MyThread thread:threads)
            thread.start();
        threads.get(0).continousThread(5,3);
        threads.get(1).continousThread(2,8);
        threads.get(2).continousThread(4,4);
        for (MyThread thread:threads)
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

    public static int func(int number, int step){
        return 1<step ? number*func(number,step-1) : number;
    }


    static class MyThread extends Thread {
        public MyThread(String name){
            super(name);
        }
        public MyThread(int number, int step){
            this.number = number;
            this.step = step;
        }

        @Override
        public void run(){
            synchronized(this){
                while (!isStop){
//                    System.out.println("started!");
                    try {
                        System.out.println("started!");
                        wait();
                        System.out.println( func(number,step) );
                    }catch (InterruptedException e){}
                }
            }
        }

        public void stopThread(){
            synchronized (this){
                isStop = true;
                notify();
            }
        }
        public void continousThread(int number, int step){
            synchronized(this){
//                if(getState().toString().equals("WAITING")) {
                    this.number = number;
                    this.step = step;
                    notify();
//                }
            }
        }

        private boolean isStop = false;
        private int number;
        private int step;
    }
}

