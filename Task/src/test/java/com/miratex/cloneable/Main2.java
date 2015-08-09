package com.miratex.cloneable;

import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

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
        demo3();
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
            @Override
            public boolean matches(Object arg) {
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
}
