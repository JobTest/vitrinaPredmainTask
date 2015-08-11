package com.miratex.my;

/**
 * Created by alexandr on 11.08.15.
 * ********************************
 */
public class Source1 {

    public static void main(String[] args) {
        /* *********************[ 1 ]******************** */
//        func1();

        /* *********************[ 2 ]******************** */
        func2();
        System.out.println("(thread-main) stop");

        /* *********************[ http://javatalks.ru/topics/21133 ]******************** */
        // что такое сервлет jsp их отличие ** сервлеты, jsp, jsf - что на чем завязан.
    }

    public static void func1(){
        A a = new A();
        B b = new B();
        /* *****************[ #1.1 ]***************** */
        a = b;     // (несмотря на то что класс 'B' нследуется от класса 'A') в случае выполнения присваивания присходит приведения типов. В случае присваивание класса-наследника к классу-предка происходит неявное приведение типов, поскольку у класса-наследника присутствуют все методы его предка - здесь будет полное соответствие согласно типу класса-предка (и потеря данных будет неявная)...
        b = (B) a; // В случае присваивания класса-предка к классу-наследника - проблема в том что класс-предок может неиметь всех методов своего наследника. Поэтому здесь нужно явно указывать приведение типов (при этом конечно произойдет явная потеря данных)
        /* *****************[ #1.2 ]***************** */
//        List l = (LinkedList) new Object(); // 'ClassCastException' - 'Object' cannot be cast to 'LinkedList'
    }

    public static void func2(){
        Thread thread1 = new Thread(new MyRunnable1("thread-1"));
        Thread thread2 = new Thread(new MyRunnable2("thread-2"));
        /* *****************[ #3.0 ]***************** */
        thread1.start();
        thread2.start();
        try {
            /*
             * Говорим основному потоку чтобы он подождал пока выполнятся другие-побочные потоки
             */
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {}
    }
    static class MyRunnable1 implements Runnable{
        public MyRunnable1(String name){
            this.name = name;
        }
        @Override public void run(){
            try {
                Thread.sleep(2000);
            } catch(InterruptedException e){}
            System.out.println("("+name+") stop");
        }
        private String name;
    }
    static class MyRunnable2 implements Runnable{
        public MyRunnable2(String name){
            this.name = name;
        }
        @Override public void run(){
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e){}
            System.out.println("("+name+") stop");
        }
        private String name;
    }
}

class A {}
class B extends A {}