package com.miratex.synchronization;

/**
 * Created by Саша on 06.08.2015.
 */
public class My1 {

    public static void main(String[] args) {
//        try {
//            System.out.println( func(1) ); //System.out.println( func(1, 7999) );
//        } catch (Error e){ System.err.println(e); }

        Thread thread1 = new Thread(new MyRunnable1("1"));
        Thread thread2 = new Thread(new MyRunnable2("2"));
        thread1.start();
        thread2.start();
    }

    /**
     *  long (64) –9,223,372,036,854,775,808 to 9,223,372,036,854,775,807
     *   int (32)             –2,147,483,648 to 2,147,483,647
     * short (16)                    –32,768 to 32,767
     *  byte (8)                        –128 to 127
     */
    public static int func(int data, int step) throws Error{
        return 1<step ? data+func(data,step-1) : data;
    }
    public static int func(int data) throws Error{
        return data<11099 ? func(data+1) : data;
    }

}

class MyRunnable1 implements Runnable {
    public MyRunnable1(String name){
        this.name= name;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread-" + getName() + ": " + func(1));
        } catch (Error e){ System.err.println(e); }
    }

    public int func(int data){
        return data<17999 ? func(data+1) : data;
    }

    public String getName(){
        return name;
    }

    private String name;
}

class MyRunnable2 implements Runnable {
    public MyRunnable2(String name){
        this.name= name;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread-" + getName() + ": " + func(1));
        } catch (Error e){ System.err.println(e); }
    }

    public int func(int data){
        return data<1099 ? func(data+1) : data;
    }

    public String getName(){
        return name;
    }

    private String name;
}