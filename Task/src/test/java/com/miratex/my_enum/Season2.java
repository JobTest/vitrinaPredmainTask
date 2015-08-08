package com.miratex.my_enum;

/**
 * Created by Саша on 06.08.2015.
 */
public enum Season2 {
    UP {
        public Season2 opposite(){
            return DOWN;
        }
    },
    DOWN {
        public Season2 opposite(){
            return UP;
        }
    };

    public abstract Season2 opposite();
}
