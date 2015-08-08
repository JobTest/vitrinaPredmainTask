package com.source2;

/**
 * Created by Саша on 09.08.2015.
 * {@link http://habrahabr.ru/post/172239/}
 * ****************************************
 * наш класс
 */
public class UseStaticService {

    public String useStatic(final Object obj) {
        StaticService.doStatic();
        //
        return StaticService.doStaticWithParams(obj);
    }

}