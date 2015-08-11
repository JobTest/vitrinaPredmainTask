package com.miratex.my;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandr on 11.08.15.
 */
public class Source2 {

    public static void main(String[] args) {

    }

}


class Pool {

    public Pool(int max){
        actives = 0;
        in = new ArrayList<>();
        reserv = new ArrayList<>(max);
        active = new ArrayList<>(max);
    }

//    public List get() {
//    }

    private int actives;
    private List in;
    private List reserv;
    private List active;

}