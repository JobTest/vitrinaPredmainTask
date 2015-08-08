package com.miratex.cloneable;

/**
 * Created by alexandr on 07.08.15.
 ** {@link http://echuprina.blogspot.com/2012/02/cloneable.html}
 * *************************************************************
 */
public class App {

    public static void main(String[] args) {
        /* При обыкновенном присваивание объектов (obj1 = obj2;) передаются ссылки на объект. В итоге два экземпляра ссылаются на один объект, и изменение одного приведет к изменению другого */
        User user = new User();
        user.setName("Иванов");
        user.setAge(25);
        System.out.println("              Данные до клонирования: " + user.getName() + " = " + user.getAge() + "(лет)");

        User clone = null;
        try {
            clone = user.clone();
            clone.setName("Петров");
            clone.setAge(30);
        } catch (CloneNotSupportedException e) { System.out.println("Объект не может быть клонированным."); }
        System.out.println("         Клон после изменения данные: " + clone.getName() + " = " + clone.getAge() + "(лет)");
        System.out.println("Оригинал, после манипуляций с клоном: " + user.getName() + " = " + user.getAge() + "(лет)");


        System.out.println();


        /* Существует так же второй вид клонирования объекта, который называется глубокое клонирование - его используют в тех случаях, когда в клонируемом классе есть изменяемые объекты */
        User2 user2 = new User2();
        user2.setName("Иванов");
        user2.setAge(25);
        user2.setBirthday(12,03,1975);
        System.out.println("              Данные до клонирования: " + user2.getName() + " = " + user2.getAge() + "(лет); День рождение '" + user2.getBirthday() + "';");

        User2 clone2 = null;
        try {
            clone2 = user2.clone();
            clone2.setName("Петров");
            clone2.setAge(30);
            clone2.setBirthday(15,11,1992);
        } catch (CloneNotSupportedException e) { System.out.println("Объект не может быть клонированным."); }
        System.out.println("         Клон после изменения данные: " + clone2.getName() + " = " + clone2.getAge() + "(лет); День рождение '" + clone2.getBirthday() + "';");
        System.out.println("Оригинал, после манипуляций с клоном: " + user2.getName() + " = " + user2.getAge() + "(лет); День рождение '" + user2.getBirthday() + "';");
    }
}
