package com.miratex.my.pools;

/**
 * Created by alexandr on 13.08.15.
 */
class AppPool extends Thread{

    public AppPool(String name){
        super(name);
        app = new App();
    }

    @Override
    public void run(){
        synchronized(this){
            while(!flagStop){
                try {
                    if( 0<app.number && 0<app.degree ) //if(!getState().toString().equals("RUNNABLE")) {
                        System.out.println(" \"" + getName() + "\": ("+app.number+"+"+app.degree+") " + app.exponent(app.number, app.degree));
                    /* приостанавливает поток если выполняется условие */
                    wait();
                } catch(InterruptedException ex){ System.err.println("-run-"); }
            }
        }
    }

    public void poolContinue(){
        /* пробуждает приостановленый поток на обьекте lock */
        if(getState().toString().equals("WAITING"))
            synchronized (this) {
                notify();
            }
    }

    public void poolStop(){
        flagStop = true; // завершает поток естественно
        synchronized (this) {
            notify();
        }
    }

    public boolean flagStop = false;
    public App app;
}