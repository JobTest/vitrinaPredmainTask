package com.miratex.synchronization;

/**
 * Created by Саша on 07.08.2015.
 * ******************************
 * main:: Sleeping
 * thread:: Thread started
 * thread:: Waiting
 * main:: setting value to '1'
 * main:: notifying thread
 * main:: Thread notified
 * thread:: Running again
 * thread:: value=0
 * ******************************
 * Синхронизация бывает:
 * 1. системная (wait/notify) - используется уже именно для определения конкретного порядка выполнения блоков программного кода...
 * 2. программная (synchronized) - просто применяется для критических блоков... для синхронизации в многопоточном приложении
 */
public class SyncImpl {

    public static void main(String[] args) {
        /* Для 'wait/notify' нужен (относительный) объект, который будет останавливать/запускать потоки */
        /* А 'wait/notify' всегда должны быть обвернуты во внутрь 'synchronized' */
        Object sync = new Object();
        int   value = 0;
        new Thread(new WaitingRunnable(sync,value)).start();

        try {
            System.out.println("main:: Sleeping");
            Thread.sleep(1500);
        } catch(InterruptedException ex){ System.err.println("main:: Interrupted: "+ex.getMessage()); }

        synchronized (sync){
            System.out.println("main:: setting value to '1'");
            value = 1;
            System.out.println("main:: notifying thread");
            sync.notify();
            System.out.println("main:: Thread notified");
        }
    }

    static class WaitingRunnable implements Runnable {
        public WaitingRunnable(Object sync, int value) {
            this.sync  = sync;
            this.value = value;
        }

        @Override
        public void run() {
            System.out.println("thread:: Started");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e){}
            synchronized(sync) {
                if (value==0)
                    try {
                        System.out.println("thread:: Waiting");
                        sync.wait();
                        System.out.println("thread:: Running again");
                    } catch (InterruptedException ex) { System.err.println("thread:: Interrupted: "+ex.getMessage()); }
                System.out.println("thread:: value="+value);
            }
        }

        private Object sync;
        private int   value;
    }
}
