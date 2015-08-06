package com.miratex.my_enum;

/**
 * Created by Саша on 06.08.2015.
 */
public enum Type {

    INT(true){
        public Object parse(String string){
            return Integer.valueOf(string);
        }
    },
    INTEGER(false){
        public Object parse(String string){
            return Integer.valueOf(string);
        }
    },
    STRING(false){
        public Object parse(String string){
            return string;
        }
    };

    private boolean primitive;

    Type(boolean primitive){
        this.primitive = primitive;
    }

    public boolean isPrimitive(){
        return primitive;
    }

    public abstract Object parse(String string);
}
