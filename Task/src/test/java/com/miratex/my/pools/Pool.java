package com.miratex.my.pools;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexandr on 12.08.15.
 * ********************************
 * {@link http://www.golovachcourses.com/threads-monitor/}
 */
public class Pool{

    public static void main(String[] args) {
        int[][] exponent = {{2,5,4},{8,3,4}};
        List<Thread> threads = new LinkedList<>();

        for (int n=0; n<3; n++)
            threads.add( new Thread(new App(exponent[0][n],exponent[1][n])) );

        for (int n=0; n<3; n++)
            System.out.println( threads.get(n).getState() );

        for (int n=0; n<3; n++)
            threads.get(n).start();

        synchronized (new Object()){
            try{
                Thread.sleep(8000);
                for (int n=0; n<3; n++)
                    System.out.println( threads.get(n).getState() );
                for (int n=0; n<3; n++)
                    threads.get(n).notify();
            } catch(InterruptedException e){ System.err.println("-getState-");
            } catch(IllegalMonitorStateException e){ System.err.println("-notify-"); }
        }
    }

}
