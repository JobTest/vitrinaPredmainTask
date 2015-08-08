package com.miratex.synchronization;

/**
 * Created by Саша on 07.08.2015.
 */
public class My2 {

    public static void main(String[] args) {
//        if (a1.equals(a2))
//            System.out.println("TRUE");
//        else
//            System.out.println("FALSE");
        Thread thread1 = new Thread(new MyRunnable1());
        Thread thread2 = new Thread(new MyRunnable2());
        thread1.start();
        thread2.start();
    }


    static class MyRunnable1 implements Runnable {
        @Override
        public void run(){
            if (a1.equals(a2))
                System.out.println("TRUE");
            else
                System.out.println("FALSE");
        }
    }
    static class MyRunnable2 implements Runnable {
        @Override
        public void run(){
            if (a2.equals(a1))
                System.out.println("TRUE");
            else
                System.out.println("FALSE");
        }
    }

    static A a1 = new A("Sasha1");
    static A a2 = new A("Sasha2");
}

class A {
    public A(String name){
        this.name = name;
    }

    /**
     *      'правило симметрии' - (если А==В, то В==А) для любых x!=null и y!=null вызов x.equals(y) должен вернуть true тогда и только тогда, когда вызов y.equals(x) возвращает true
     * 'правило транзитивности' - (если А==В и В==С, то А==С) для любых x, y и z, не равных null, таких, что x.equals(y)==true и y.equals(z)==true, выполняется также и x.equals(z)==true
     * 'правило рефлексивности' - (объект всегда равен самому себе) для любого x!=null вызов x.equals(x) должен вернуть true
     */
    @Override
    public synchronized boolean equals(Object o){
        A a = (A) o;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){ System.err.println(e); }
        if (this == a) return true;
        if ( name != null && a.getName() != null ) return getName() == a.getName() ? true : false;
        return false;
    }

    public synchronized String getName(){
        return name;
    }

    private String name;
}