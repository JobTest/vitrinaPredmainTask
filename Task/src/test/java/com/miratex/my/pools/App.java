package com.miratex.my.pools;

/**
 * Created by alexandr on 12.08.15.
 */
public class App implements Runnable {

    public App(){
        status = Status.WORK;
    }
    public App(int number, int degree){
        this.number = number;
        this.degree = degree;
        status = Status.WORK;
    }

    @Override
    public void run() {
        synchronized (this){
            while (true){ //while (status.isWork()){
                System.out.println("(" + degree + ") " + exponent(number, degree));
                //status = Status.PAUSE;
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
        }
    }

    public int exponent(int number, int degree){
        try {
            Thread.sleep(500);
        } catch(InterruptedException e){}
        return 1<degree ? number*exponent(number,degree-1) : number;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public int getDegree() {
        return degree;
    }
    public void setDegree(int degree) {
        this.degree = degree;
    }
    public int getExponent() {
        return exponent;
    }
    public void setExponent(int exponent) {
        this.exponent = exponent;
    }
    public Status getState() {
        return status;
    }
    public void setState(Status state) {
        this.status = state;
    }

    private int number;
    private int degree;
    private int exponent;
    private Status status;
}


enum Status{
    WORK(true),PAUSE(false);

    Status(boolean status){
        this.status = status;
    }

    public boolean isWork(){
        return status;
    }

    private boolean status;
}