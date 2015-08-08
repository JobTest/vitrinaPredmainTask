package com.miratex.cloneable;

/**
 * Created by alexandr on 07.08.15.
 */
class User implements Cloneable {

    /* Данный метод объявлен как 'protected' это значит что метод защищен и может быть доступен только при наследовании объекта */
    /* Чтобы клонировать другие объекты, метод clone() необходимо расширить до public */
    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private int age;
}
