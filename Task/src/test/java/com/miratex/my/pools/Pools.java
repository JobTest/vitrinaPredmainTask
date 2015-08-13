package com.miratex.my.pools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Саша on 12.08.2015.
 */
public class Pools {

    private int max;

    public Pools(int max){
        this.max = max;
    }

    public List<AppPool> pools = new ArrayList<>();
    public List<App> queue = new ArrayList<>();

    public void init(){
        for (int pool=0; pool<max; pool++)
            pools.add(new AppPool("app-" + pool));

        for (int pool=0; pool<max; pool++)
            pools.get(pool).start();
    }

    public void put(int number, int degree){
        App    app = new App();
        app.number = number;
        app.degree = degree;
        queue.add(app);
    }

    public void stop(){
        for (int pool=0; pool<max; pool++)
            pools.get(pool).poolStop();
    }

//    public AppPool getPool(int number, int degree){
//        AppPool app1 = null;
//        while (app1 == null){
//            for (int p=0; p<max; p++){
//                if( pools.get(p).getState().toString().equals("WAITING") ){
//                    AppPool app2 = pools.get(p);
//                    app2.app.number = number;
//                    app2.app.degree = degree;
//                    pools.set(p, app2);
//                    app1 = pools.get(p);
//                    p     = 9;
//                    return app1;
//                }
//            }
//        }
//        return app1;
//    }
    public AppPool getPool2(){
        AppPool app1 = null;
        while (app1 == null){
            for (int p=0; p<max; p++){
                if( pools.get(p).getState().toString().equals("WAITING") ){
                    AppPool app2 = pools.get(p);
                    app2.app.number = queue.get(count).number;
                    app2.app.degree = queue.get(count).degree;
                    pools.set(p, app2);
                    app1 = pools.get(p);
                    p     = 9;
                    count++;
                    return app1;
                }
            }
        }
        return app1;
    }

    public static int count = 0;
}


