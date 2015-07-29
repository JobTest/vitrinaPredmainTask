package com.vitrina.controller;

import com.vitrina.service.dbService;

import java.util.LinkedList;

/**
 * Created by alexandr on 15.07.15.
 */
public class App {

    private dbService service;

    public void start() {
        service.map.put("db-getAll", service.toList(new LinkedList<>()));
        System.out.println("\n********************************[ getAll << DB (" + service.map.get("db-getAll").size() + ") ]******************************");
//        service.print(service.map.get("db-getAll"));

        String[] files = {"issue1.xml","issue2.xml"}; //String[] files = {"issue1.xml","issue2.xml","issue3.xml"};
        service.map.put("jaxb-upload", service.toList(files));
        System.out.println("\n********************************[ Parser << JAXB (" + service.map.get("jaxb-upload").size() + ") ]****************************");
//        service.print(service.map.get("jaxb-upload"));

        String[] dueDates = {"0","10","20","40","70","100"};
        service.prepareDB(service.map.get("db-getAll"), service.map.get("jaxb-upload"), dueDates);
        System.out.println("\n********************************[ Add >> DB (" + service.map.get("db-add").size() + ") ]*********************************");
        System.out.println("--------------------------------");
        System.out.println( "db-delete: |>> (" + (service.map.get("db-getAll").size()-service.map.get("db-delete").size()) + ")  -" + service.map.get("db-delete").size());
        System.out.println( "db-update: |>> (" + (service.map.get("db-getAll").size()-service.map.get("db-delete").size()) + ")  ~" + service.map.get("db-update").size() );
        System.out.println( "   db-add: |>> (" + (service.map.get("db-getAll").size()-service.map.get("db-delete").size()+service.map.get("db-add").size()) + ") +" + service.map.get("db-add").size() );
    }

    public dbService getService() {
        return service;
    }
    public void setService(dbService service) {
        this.service = service;
    }
}
