package com.miratex.synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Саша on 07.08.2015.
 ** {@link http://ru.stackoverflow.com/questions/229090/Стартуют-не-все-потоки-из-списка}
 */
public class StartClass {

    private static Logger logger;

    public static void main(String[] args) {
        List<MyClass> objList = new ArrayList<MyClass>(10);
        for (int i=0; i<10; ++i)
            objList.add(new MyClass());

        List<Thread> treads = new ArrayList<Thread>();
        for (final MyClass obj:objList)
            treads.add(new Thread(new Runnable(){
                @Override
                public void run() {
                    obj.work();
                }
            }));

        for (Thread thread:treads)
            thread.start();

        for (Thread thread:treads)
            try {
                thread.join();
            } catch (InterruptedException ex) { logger.log(Level.SEVERE, null, ex); }
    }
}

class MyClass {

    private static int i;

    public void work() {
        System.out.println(i++);
    }
}
