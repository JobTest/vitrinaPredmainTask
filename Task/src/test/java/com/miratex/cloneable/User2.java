package com.miratex.cloneable;

/**
 * Created by alexandr on 07.08.15.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;

class User2 implements Cloneable {

    @Override
    public User2 clone() throws CloneNotSupportedException {
        User2    clone = (User2) super.clone();
        clone.birthday = (GregorianCalendar) birthday.clone();
        return clone;
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
    public String getBirthday() {
        return birthday.get(Calendar.DAY_OF_MONTH) + "" + birthday.get(Calendar.MONTH) + "" + birthday.get(Calendar.YEAR);
    }
    public void setBirthday(int day, int month, int year) {
        birthday = new GregorianCalendar();
        birthday.set(Calendar.DAY_OF_MONTH, day);
        birthday.set(Calendar.MONTH, month);
        birthday.set(Calendar.YEAR, year);
    }

    private String name;
    private int age;
    private GregorianCalendar birthday;
}