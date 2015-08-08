package com.miratex.synchronization;

/**
 * Created by alexandr on 06.08.15.
 * @author Eugene Matyushkin
 * @version 1.0
 ** {@link http://www.skipy.ru/technics/synchronization.html}
 * {@link http://initialize.ru/vzaimodeistvie-mejdu-potikami-java}
 * {@link http://info.javarush.ru/translation/2014/10/27/Синхронизация-потоков-блокировка-объекта-и-блокировка-класса.html}
 * {@link http://habrahabr.ru/company/luxoft/blog/157273/}
 * {@link http://habrahabr.ru/post/164487/}
 * {@link http://www.ibm.com/developerworks/ru/library/j-jtp10264/}
 * {@link http://crypto.pp.ua/2010/06/instrukciya-synchronized-v-java/}
 * ********************************
 * DeadLockTest
 *
 * На самом же деле различие между ними вплотную касается обсуждаемой темы. Дело в том, что Vector синхронизирован, а ArrayList – нет. Практически все методы класса Vector объявлены как synchronized, за исключением разве что iterator(). Однако, возвращаемый итератор опирается на те же синхронизированные методы вектора. Т.е. можно сказать, что класс Vector – thread-safe.
 * В случае с ArrayList применен кардинально другой подход. Для него поддерживается счетчик обновлений. Если в каком-нибудь критичном к обновлениям методе (вроде next итератора) ожидаемое значение счетчика не совпадает с реальным – инициируется исключение java.util.ConcurrentModificationException.
 * Что дают эти два подхода. Как мы видели выше, синхронизация способна приостановить поток, пока не будет отпущен монитор. В ситуации, когда данные изменяются нечасто, синхронизация их чтения может дать падение производительности. В этом случае лучше использовать ArrayList. Т.е., скажем, два потока могут одновременно пройтись по одному и тому же ArrayList-у, в то время как по Vector-у – нет. Потому – использовать Vector стоит лишь там, где синхронизация действительно необходима. Он способен решить многие проблемы, но добавить может не меньше.
 * Точно такая же разница и между ассоциативными коллекциями – java.util.Hashtable и java.util.HashMap. Hashtable синхронизирован (thread-safe), HashMap – нет. Преимущества и недостатки абсолютно те же.
 * Ну ладно, у ArrayList есть синхронизированный аналог, у HashMap тоже. А как быть с остальными? Есть еще, например, java.util.LinkedList. Есть java.util.LinkedHashMap и java.util.TreeMap. Есть, наконец, целый класс множеств, унаследованных от java.util.Set – java.util.HashSet, java.util.TreeSet, java.util.LinkedHashSet. Как быть с ними, если очень хочется? Писать самостоятельно?
 */
public class DeadLockImpl {

    public static void main(String[] args){
        A      a1 = new A();
        A      a2 = new A();
        Thread t1 = new Thread( new Tester(a1,a2) );
        Thread t2 = new Thread( new Tester(a2,a1) );
        t1.start();
        t2.start();
    }


    public static class Tester implements Runnable {
        static int nextId = 1;
        private A      a1;
        private A      a2;
        private int    id = 0;

        public Tester(A obj1, A obj2){
            this.a1 = obj1;
            this.a2 = obj2;
            id        = nextId++;
        }

        public void run(){
            a1.setValue(id);
            print("Setting value to 'a1' = " + a1.getValue()); //print("Setting value to 'a1' = " + a1.getValue(a2));
            print("done.");
            print("Comparing objects... ");
            print( "Done. Result: " + ((a1.equals(a2)) ? "equal" : "not equal") );
        }

        /**
         * В класе 'A' присутствуют три синхронизированных метода.
         * В одном из этих методов 'equals' одновременно используются два-объекта. Для пущей надежности внутри метода стоит задержка по времени...
         * Проблема в том, когда этот метод захватит монитор объект-1, тогда второй объект сюда уже зайти несможет, тобиш, сюда нельзя передать параметр второго объекта.
         * И на этом состоянии программа останавливается, ждет пока освободится объект-2 (но объект-2 неможет сюда зайти) - такое состояние блокирует программу 'dedlock'
         * @param msg
         */
        private void print(String msg){
            System.out.println("Thread #" + id + ": " + msg);
        }
    }

    public static class A {
        private int value = 0;

        synchronized void setValue(int value){
            this.value = value;
        }

        synchronized int getValue(){
            return value;
        }

//        synchronized int getValue(Object o){
//            A a = (A) o;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e){System.err.println(e.getMessage());}
//            return a.getValue();
//        }

        public synchronized boolean equals(Object o){
            A a = (A) o;
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex){ System.err.println("Interrupted!"); }
            return value == a.getValue();
        }
    }

}
