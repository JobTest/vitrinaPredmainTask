package com.miratex.my_immutable;

import java.util.Date;

/**
 * Created by Саша on 06.08.2015.
 * {@link http://www.quizful.net/interview/java/string-stringbuffer-difference}
 * ****************************************************************************
 * Класс 'StringBuffer' является Mutable - использовать StringBuffer или StringBuilder следует тогда, когда вы хотите модифицировать содержимое
 * StringBuilder был добавлен в Java-5 (он во всем идентичен классу StringBuffer) за исключением того, что он не синхронизирован - что делает его значительно шустрее
 * (но цена скорости - небезопасное поведение в мультипоточной среде)
 */
public class Main {

    public static void main(String[] args) {
        /* ******************************************[ Immutable ]***************************************** */
        ImmutableTest immutableTest1 = new ImmutableTest("Alexandr", new Date(), "HostingMax,Ignite,PrivatBank".split(","));
        ImmutableDateHolder immutableDateHolder = new ImmutableDateHolder(new Date());

        /* ******************************************[ Разница между String/StringBuffer/StringBuilder ]***************************************** */
        // Inefficient version using immutable String. В примере выше создаст 99 новых объектов String, 98 из которых будут тут же откинуты
        String output1 = "Some text";
        for(int i=0; i<100; i++)
            output1 += i;
        System.out.println( output1 );

        // More efficient version using mutable StringBuffer set an initial size of 110
        // код создает только два новых объекта, StringBuffer и строковую константу, которая возвращается. StringBuffer расширяется по мере надобности, что, в свою очередь дороговато, так что лучше инициализировать StringBuffer корректным значением размера
        StringBuffer output2 = new StringBuffer(110);
        output2.append("Some text");
        for(int i =0; i<100; i++)
            output2.append(i);
        System.out.println( output2.toString() );
    }

}

//class MyImmutableTest extends ImmutableTest {}

//class MyImmutableDateHolder extends ImmutableDateHolder{}