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


class Pools {

    public Pools(int max){
        actives = 0;
        in = new ArrayList<>();
        available = new ArrayList<>(max);
        using = new ArrayList<>();

        available.add(new Pool(1, "1"));
        available.add(new Pool(2, "2"));
        available.add(new Pool(3, "3"));
        available.add(new Pool(4, "4"));
        available.add(new Pool(5, "5"));
        available.add(new Pool(6, "6"));
    }

    public Pool getPool() {
        using.add(available.get(actives));
        available.remove(actives);
        actives++;
        return (Pool) using.get(actives-1);
    }
    public void returnPool() {
        available.add(using.get(actives));
        using.remove(actives);
        actives--;
    }

    private int actives;
    private List in;
    private List available;
    private List using;
    private Pool pool;
}

class Pool {
    public Pool(int number, String value){
        this.number = number;
        this.value = value;
    }
    private int number;
    private String value;
}