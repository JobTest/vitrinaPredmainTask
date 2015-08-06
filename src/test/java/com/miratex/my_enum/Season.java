package com.miratex.my_enum;

/**
 * Created by Саша on 05.08.2015.
 * {@link http://www.quizful.net/post/java_enums}
 * **********************************************
 * Перечисления в Java (java enum)
 */

public enum Season {
    WINTER, SPRING, SUMMER, AUTUMN, UP, DOWN;

    public Season opposite(){
        return this == UP ? DOWN : UP;
    }
}
