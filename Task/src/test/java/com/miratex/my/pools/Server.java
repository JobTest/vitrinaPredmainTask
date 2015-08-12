package com.miratex.my.pools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Саша on 12.08.2015.
 */
public class Server {

    private int max;

    public Server(int max){
        this.max = max;
    }

    public List<App2> pools = new ArrayList<>();
    public void init(){
        for (int p=0; p<max; p++)
            pools.add(new App2("app-" + p));

        for (int p=0; p<max; p++)
            pools.get(p).start();
    }
    public void stop(){
        for (int p=0; p<max; p++)
            pools.get(p).poolStop();
    }
    public App2 pool(int number, int degree){
        App2 app1 = null;
        while (app1 == null){
            for (int p=0; p<max; p++){
                if( pools.get(p).getState().toString().equals("WAITING") ) {
                    App2 app2 = pools.get(p);
                    app2.number = number;
                    app2.degree = degree;
                    pools.set(p, app2);
                    app1 = pools.get(p);
                    p=9;
                    return app1;
                }
            }
            try{ Thread.sleep(800); } catch (InterruptedException e){}
        }
        return app1;
    }

    public static void main(String[] args){
//        func();

        Server server = new Server(3);

        server.init();
//        try{ Thread.sleep(800); } catch (InterruptedException e){}
        server.pool(5,3).poolContinue();
        server.pool(2,8).poolContinue();
        server.pool(4,4).poolContinue();
        server.pool(5,3).poolContinue();
        server.pool(2,8).poolContinue();
        server.pool(4,4).poolContinue();
        server.pool(5,3).poolContinue();
        server.pool(2,8).poolContinue();
        server.pool(4,4).poolContinue();
        server.stop();
    }

    public static void func(){
        App2 app1 = new App2("App-1");
        app1.start();
        try {
            System.err.println("status: " + app1.getState());
            Thread.sleep(800);
            System.err.println("status: " + app1.getState());
            Thread.sleep(2000);
            System.out.println("Ставим поток на паузу");
//            app1.threadPause();
            Thread.sleep(800);
            System.err.println("status: " + app1.getState());

            Thread.sleep(2000);
            System.out.println("Продолжаем работу потока");
            app1.poolContinue();
            Thread.sleep(800);
            System.err.println("status: " + app1.getState());

            Thread.sleep(2000);
            System.out.println("Останавливаем поток");
            app1.poolStop();
            Thread.sleep(800);
            System.err.println("status: " + app1.getState());
        } catch (InterruptedException ex){ System.err.println("-main-"); }
    }

}


class App2 extends Pool2{

    public App2(String name){
        super(name);
    }

    @Override
    public void run(){
        synchronized(this){
            while(!flagStop){
                try {
                    if(0<number && 0<degree){ //if(!getState().toString().equals("RUNNABLE")) {
                        System.out.println(" \"" + getName() + "\": ("+number+"+"+degree+") " + exponent(number, degree));
                    }
                    Thread.sleep(600);
                    wait(); // приостанавливает поток если выполняется условие
                } catch(InterruptedException ex){ System.err.println("-run-"); }
            }
        }
    }

    public int exponent(int number, int degree){
        return 1<degree ? number*exponent(number,degree-1) : number;
    }

    public int number;
    public int degree;
}

class Pool2 extends Thread {

    public Pool2(String name){
        super(name);
    }

    @Override
    public void run(){}

    public void poolContinue(){
        if(getState().toString().equals("WAITING")) {
            synchronized (this) { //synchronized(objectLock){
                notify(); // пробуждает приостановленый поток на обьекте lock
            }
        }
    }

    public void poolStop(){
        flagStop = true; // завершает поток естественно
        synchronized (this) {
            notify();
        }
    }

    public boolean flagStop = false;
}
