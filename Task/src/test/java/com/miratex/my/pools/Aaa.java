package com.miratex.my.pools;

/**
 * Created by alexandr on 12.08.15.
 */
public class Aaa {

    public static void main(String[] args) {
        MyThread myThread = new MyThread("1");
        myThread.start();
        try {
            Thread.sleep(1000);
            func(myThread);
        } catch (InterruptedException e){}
    }

    public static synchronized void func(Thread thread){
        try {
            thread.wait();
        } catch (InterruptedException e1){
        } catch (IllegalMonitorStateException e2){}
    }
}

class MyThread extends Thread {

    public MyThread(String tName){
        this.tName = tName;
    }

    @Override
    public void run(){
        while (true) {
            try{ Thread.sleep(300); } catch (InterruptedException e){}
            System.out.println("Name: " + tName);
        }
    }

    public String getTName() {
        return tName;
    }

    private String tName;
}
