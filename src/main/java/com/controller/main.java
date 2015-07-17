package com.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by alexandr on 17.07.15.
 ** {@link http://www.ibm.com/developerworks/ru/library/j-java8lambdas/}
 * {@link http://www.quizful.net/post/functors-and-adapters-in-STL}
 */
public class main {

    public static void main(String[] args) {
        /* Предикаты используются в алгоритмах сортировок, поиска, а также во всех остальных, имеющих на конце _if. Смысл в том, что объект-функция в случае использования предиката возвращает истину или ложь в зависимости от выполнения необходимого условия */
        Name[] arrNames = {new Name("Sally","Sally",1000),new Name("Queue","Queue",2000),new Name("Sasha","Lazarchuk",1978), new Name("Dima","Borisenko",1995), new Name("Victor","Burobik",2000), new Name("Ruslan","Mushinsky",1995), new Name("Vladimir","Chumak",1978)};
        List<Name> names = new ArrayList(Arrays.asList(arrNames));

        Predicate<Name> pred1 = name -> "Sally".equals(name.firstName);
        Predicate<Name> pred2 = name -> "Queue".equals(name.lastName);
        Predicate<Name> pred3 = name -> Integer.valueOf(1978).equals(name.year);
//        names.removeIf(pred1);
//        names.removeIf(pred2);
//        names.removeIf(pred3);
        names.removeIf(pred1.or(pred2).or(pred3));

        names.forEach(System.out::println);
    }

}


class Name {

    public String firstName;
    public String lastName;
    public Integer year;

    public Name(){}
    public Name(String firstName, String lastName, Integer year){
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}