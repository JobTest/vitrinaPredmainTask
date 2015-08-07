package com.miratex.synchronization;

import java.util.LinkedList;

/**
 * Created by Саша on 07.08.2015.
 ** {@link http://www.ibm.com/developerworks/ru/library/j-jtp0730/}
 * {@link http://ru.stackoverflow.com/questions/179158/Потоки-и-коллекции}
 */
public class WorkQueue {

    private final int          nThreads;
    private final PoolWorker[] threads;
    private final LinkedList   queue;

    public WorkQueue(int nThreads){
        this.nThreads = nThreads;
        queue         = new LinkedList();
        threads       = new PoolWorker[nThreads];

        for (int i=0; i<nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    public void execute(Runnable r) {
        synchronized(queue) {
            queue.addLast(r);
            queue.notify();
        }
    }

    private class PoolWorker extends Thread {
        @Override
        public void run() {
            Runnable r;
            while (true) {
                synchronized(queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException ignored){}
                    }
                    r = (Runnable) queue.removeFirst();
                }

                /* If we don't catch RuntimeException, the pool could leak threads */
                try {
                    r.run();
                } catch (RuntimeException e) {}
            }
        }
    }
}
