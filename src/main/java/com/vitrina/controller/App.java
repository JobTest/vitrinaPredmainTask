package com.vitrina.controller;

import com.vitrina.service.dbService;

import java.util.LinkedList;

/**
 * Created by alexandr on 15.07.15.
 */
public class App {

    private dbService service;

    public void start() {
        System.out.println("\n********************************[  DB-getAll  ]*********************************");
        service.map.put("db-getAll", service.toList(new LinkedList<>()));
//        service.print(service.map.get("db-getAll"));

        System.out.println("\n********************************[ JAXB-Parser ]*********************************");
        String[] files = {"issues1.xml","issues2.xml","issues3.xml"};
        service.map.put("jaxb-upload", service.toList(files));
//        service.print(service.map.get("jaxb-upload"));

        System.out.println("\n********************************[   DB-Add    ]*********************************");
        String[] dueDates = {"0","10","100"};
        service.insert(service.map.get("db-getAll"), service.map.get("jaxb-upload"), dueDates);
    }

    public dbService getService() {
        return service;
    }
    public void setService(dbService service) {
        this.service = service;
    }
}
