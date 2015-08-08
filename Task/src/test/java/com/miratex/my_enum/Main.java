package com.miratex.my_enum;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Саша on 06.08.2015.
 */
public class Main {

    public static void main(String[] args) {
        /* ****************************[ Конструкция enum ]******************************* */
        Season season = Season.SPRING;
        System.out.println("season: " + season);

        if (season == Season.SPRING)
            season = Season.SUMMER;
        System.out.println("season: " + season);

        /* ****************************[ Перечисление - это класс ]******************************* */
        System.out.println("class: " + Season.class.getSuperclass());

        /* ****************************[ Название и порядковый номер элемента enum ]******************************* */
        System.out.println("season.name(): " + season.name() + "; season.toString(): " + season.toString() + "; season.ordinal(): " + season.ordinal() + ";");

        /* ****************************[ Получение елемента enum по строковому представлению его имени ]******************************* */
        String name = "WINTER";
        season = Season.valueOf(name);
        System.out.println("season: " + season);
        System.out.println( Arrays.toString(Season.values()) );

        /* ****************************[ Добавляем свои методы в enum-класс ]******************************* */
        System.out.println("opposite: " + season.opposite());
        Season2 season2 = Season2.UP.opposite(); //Season2 season2 = Season2.UP;
        System.out.println("opposite2: " + season2);

        /* ****************************[ Наследование в enum ]******************************* */
        System.out.println(Type.class);
        System.out.println("'" + Type.INT.getClass() + "', '" + Type.INT.getClass().getSuperclass() + "'");
        System.out.println("parse: " + Type.INT.parse("123") + "; primitive: " + Type.INT.isPrimitive() + ";");
        Type type = Type.INT;
        System.out.println("(INT) parse: " + type.parse("123") + "; (INT) primitive: " + type.isPrimitive() + ";");
        type = Type.STRING;
        System.out.println("(STRING) parse: " + type.parse("123") + "; (STRING) primitive: " + type.isPrimitive() + ";");

        /* ****************************[ ... ]******************************* */
        Direction father = Direction.FATHER;
        Direction moother = Direction.MOTHER;
        Direction soon = Direction.SOON;
        for (Direction direction:Direction.values())
            System.out.print(direction + " ");
        System.out.print('\n');

        List<Direction> directions = Arrays.asList(moother,soon,father);
        Collections.sort(directions);
        Iterator iterator = directions.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());

        /* ****************************[ Декомпилированный enum-class с наследованием ]******************************* */

    }

}
