package com.miratex.my_enum;

import java.util.Comparator;

/**
 * Created by Саша on 06.08.2015.
 * ******************************
 * [*] Тип 'enum' это  наследник от класа java.lang.Enum
 * [+] Внутри такого типа 'enum' можно создавать поля, методы и конструкторы, даже 'static' и 'final' доступом...
 * [+] Даже можно сюда имплементить интерфейсы...
 * [-] Но здесь нельзя наследовать и наследоваться - это иммутайбл класс.
 * [-] Также нельзя применять здесь дженерики.
 */
public enum Direction implements Comparator<Direction> {
    FATHER(30), MOTHER(29), SOON(10);

    @Override
    public int compare(Direction direction1, Direction direction2) {
        if (direction1.getAge() == direction2.getAge())
            return 0;
        if (direction1.getAge() < direction2.getAge())
            return 1;
        else
            return -1;
    }

//    public int compareTo(Direction direction){
//        return direction.
//    }

    Direction(int age){
        this.age = age;
    }

    public int getAge(){
        return age;
    }

    private int age;
    public static final String data = "aaaaaaa";

}
