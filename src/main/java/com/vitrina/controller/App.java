package com.vitrina.controller;

import com.vitrina.service.dbService;

import java.util.LinkedList;

/**
 * Created by alexandr on 15.07.15.
 */
public class App {

    private dbService service;

    public void start() {
        service.map.put("old-db", service.toList(new LinkedList<>()));
        System.out.println("\n********************************[ Old << DB (" + service.map.get("old-db").size() + ") ]*******************************");
//        service.print(service.map.get("old-db"));

        String[] files = {"issue1.xml","issue2.xml","issue3.xml"};
//        String[] files = {"issues1.xml","issues2.xml","issues3.xml"};
        service.map.put("new-xml", service.toList(files));
        System.out.println("\n********************************[ New << XML (" + service.map.get("new-xml").size() + ") ]*****************************");
//        service.print(service.map.get("new-xml"));

        String[] dueDates = {"0","10","20","40","70","100"};
        service.prepareDB(service.map.get("old-db"), service.map.get("new-xml"), dueDates);
        System.out.println("\n********************[ Delete/Update/Add >> DB (-" + service.map.get("db-delete").size() + "/~" + service.map.get("db-update").size() + "/+" + service.map.get("db-add").size() + ") ]*********************");
//        System.out.println( "db-delete: |>> (" + (service.map.get("old-db").size()-service.map.get("db-delete").size()) + ")  -" + service.map.get("db-delete").size());
//        System.out.println( "db-update: |>> (" + (service.map.get("old-db").size()-service.map.get("db-delete").size()) + ")  ~" + service.map.get("db-update").size() );
//        System.out.println( "   db-add: |>> (" + (service.map.get("old-db").size()-service.map.get("db-delete").size()+service.map.get("db-add").size()) + ") +" + service.map.get("db-add").size() );
        service.executeDB(service.map.get("db-delete"), service.map.get("db-update"), service.map.get("db-add"));

//        System.out.println("-------------------------------------------");
//        service.print(service.map.get("db-add"));
    }

    public dbService getService() {
        return service;
    }
    public void setService(dbService service) {
        this.service = service;
    }
}
