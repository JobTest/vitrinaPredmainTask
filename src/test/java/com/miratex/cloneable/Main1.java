package com.miratex.cloneable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Саша on 07.08.2015.
 */
public class Main1 {

    public static void main(String[] args) {
        // create two lists
        List<String> srclst = new ArrayList<>(5);
        List<String> destlst = new ArrayList<>(10);

        // populate two lists
        srclst.add("Java");
        srclst.add("is");
        srclst.add("best");

        destlst.add("C++");
        destlst.add("is");
        destlst.add("older");
        destlst.add("aaa");
        destlst.add("bbb");

        System.out.println("Value of source list: " + srclst + " (" + srclst.size() + ")");
        System.out.println("Value of destination list: " + destlst + " (" + destlst.size() + ")");

        // copy into dest list
        Collections.copy(destlst, srclst);
        destlst.remove(4);

        System.out.println("Value of source list: " + srclst + " (" + srclst.size() + ")");
        System.out.println("Value of destination list: " + destlst + " (" + destlst.size() + ")");
    }

}
