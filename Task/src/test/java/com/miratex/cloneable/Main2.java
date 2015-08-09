package com.miratex.cloneable;

import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

/**
 * Created by Саша on 08.08.2015.
 * {@link http://kharkovitcourses.blogspot.com/2012/08/testing-mockito.html}
 * *************************************************************************
 */
public class Main2 {

    public static void main(String[] args) {
//        demo1();
//        demo2();
        /* http://habrahabr.ru/post/72617/ */
//        demo3();
//        demo4();
        demo5();
    }

    public static void demo1(){
        /* arrange */
        List<String> listMock = Mockito.mock(List.class);
        /* act-1 */
        System.out.println(listMock.getClass());
        System.out.println(listMock.get(0));

        Mockito.when(listMock.get(0)).thenReturn("stub");
        /* act-2 */
        System.out.println(listMock.get(0));
        System.out.println(listMock.get(1));

        Mockito.when(listMock.get(Mockito.anyInt())).thenReturn("stub");
        /* act-3 */
        System.out.println(listMock.get(0));
        System.out.println(listMock.get(1));
    }

    public static void demo2(){
        /* arrange */
        List<String> listMock = Mockito.mock(List.class);
        Mockito.when(listMock.get(Mockito.anyInt())).thenReturn("odd");
        Mockito.when(listMock.get(evenInt())).thenReturn("even");
        /* act-1 */
        System.out.println(listMock.get(0));
        System.out.println(listMock.get(1));

        when(listMock.get(anyInt())).thenReturn("odd");
        when(listMock.get(evenInt())).thenReturn("even");
        /* act-2 */
        System.out.println(listMock.get(0));
        System.out.println(listMock.get(1));
    }
    private static int evenInt() {
        return Mockito.intThat(new ArgumentMatcher<Integer>() {
            @Override public boolean matches(Object arg) {
                return ((Integer) arg) % 2 == 0;
            }
        });
    }

    public static void demo3(){
        List mockedList = mock(List.class);

        /* используем его */
        mockedList.add("one");
        mockedList.clear();

        /* проверяем, были ли вызваны методы add с параметром "one" и clear */
        System.out.println("verify-add: " + verify(mockedList).add("one"));
        verify(mockedList).clear();
    }

    public static void demo4(){
        /* Вы можете создавать mock для конкретного класса, не только для интерфейса */
        LinkedList mockedList = mock(LinkedList.class);

        /* stub'инг */
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        /* получим "first" || получим RuntimeException || получим "null" ибо get(999) не был определен*/
        System.out.println( mockedList.get(0) );
//        System.out.println( mockedList.get(1) );
        System.out.println( mockedList.get(999) );

        /* используем mock-объект */
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //по умолчанию проверка, что вызывался 1 раз ~ times(1)
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //точное число вызовов
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //никогда ~ never() ~ times(0)
        verify(mockedList, never()).add("never happened");

        //как минимум, как максимум
        verify(mockedList, atLeastOnce()).add("three times");
//        verify(mockedList, atLeast(2)).add("five times");
        verify(mockedList, atMost(5)).add("three times");
    }

    public static void demo5(){
        List list = new LinkedList();
        List spy = spy(list);

        //опционально, определяем лишь метод size()
        when(spy.size()).thenReturn(100);

        //используем реальные методы
        spy.add("one");
        spy.add("two");

        //получим "one"
        System.out.println(spy.get(0));

        //метод size() нами переопределён - получим 100
        System.out.println(spy.size());

        //можем проверить
        verify(spy).add("one");
        verify(spy).add("two");
    }
}
