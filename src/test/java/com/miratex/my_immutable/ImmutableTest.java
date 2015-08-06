package com.miratex.my_immutable;

import java.util.Date;

/**
 * Created by Саша on 06.08.2015.
 ** {@link http://sh2533.blogspot.com/2011/08/immutable.html}
 * {@link http://www.ibm.com/developerworks/ru/library/j-jtp02183/}
 * {@link http://www.quizful.net/interview/java/why-string-immutable-in-java}
 * **************************************************************************
 * У такого ненаследуемого класса все поля 'final' и сам кдас тоже 'final', а все его поля являются приватными.
 * Преимущества неизменяемости:
 * - Возможность кэширования
 * - Внутренняя безопасность потоков
 * - Безопасность при работе с "плохим" кодом
 * - Хорошие ключи
 * ~ (Безопасность, Hashcode, Многопоточность)
 * Неизменяемые классы идеальны для представления значения абстрактных типов данных, таких как числа, перечислимые типы или цвета.
 * (Так например: класс 'String' - является Immutable; а вот объекты Spring-а - являются Mutable)
 */
public final class ImmutableTest {

    public ImmutableTest(String name, Date dateOfBirth, String[] skills) { //public ImmutableTest(String name, Date dateOfBirth, String... skills) {
        this.name = name;
        /* при конструировании объекта, полям присваиваются новые объекты, вместо ссылок */
        this.dateOfBirth = new Date(dateOfBirth.getTime());
        this.skills      = skills.clone();
    }


    public String getName() {
        return name;
    }

    /* Возвращаем новый объект Date, вместо передачи ссылки на текущий */
    public Date getDateOfBirth() {
        return new Date(dateOfBirth.getTime());
    }

    /* Возвращаем клонированный массив, вместо ссылки на объект массива */
    public String[] getSkills() {
        return skills.clone();
    }

    private final String      name;
    private final Date dateOfBirth;
    private final String[]  skills;
}
