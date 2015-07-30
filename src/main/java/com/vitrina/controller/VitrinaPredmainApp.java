package com.vitrina.controller;

import com.vitrina.domain.Issue;
import com.vitrina.service.dbService;

import java.io.File;
import java.util.*;

/**
 * Created by alexandr on 15.07.15.
 */
public class VitrinaPredmainApp {

    private dbService service;
    private Map<String, List<Issue>> serviceData;
    private final String[]              dueDates = {"0","10","20","40","70","100"};
    private final File[]                   files = {new File("issue1.xml"),new File("issue2.xml"),new File("issue3.xml")};
    //private final String[] files = {"issues1.xml","issues2.xml","issues3.xml"};

    public VitrinaPredmainApp(){
        serviceData = Collections.synchronizedMap( new HashMap<>() );
    }

    public void start() {
        serviceData.putAll(service.loadData(new LinkedList<>(), files));
        System.out.println("\n********************************[ Old << DB (" + serviceData.get("old-db").size() + ") ]******************************");
//        service.print(serviceData.get("old-db"));
        System.out.println("\n********************************[ New << XML (" + serviceData.get("new-xml").size() + ") ]*****************************");
//        service.print(serviceData.get("new-xml"));

        serviceData.putAll( service.parseData(serviceData.get("old-db"), serviceData.get("new-xml"), dueDates) );
        System.out.println("\n********************[ Delete/Update/Add >> DB (-" + serviceData.get("db-delete").size() + "/~" + serviceData.get("db-update").size() + "/+" + serviceData.get("db-add").size() + ") ]*********************");
//        System.out.println( "db-delete: |>> (" + (serviceData.get("old-db").size()-serviceData.get("db-delete").size()) + ")  -" + serviceData.get("db-delete").size());
//        System.out.println( "db-update: |>> (" + (serviceData.get("old-db").size()-serviceData.get("db-delete").size()) + ")  ~" + serviceData.get("db-update").size() );
//        System.out.println( "   db-add: |>> (" + (serviceData.get("old-db").size()-serviceData.get("db-delete").size()+serviceData.get("db-add").size()) + ") +" + serviceData.get("db-add").size() );
        service.updateData( serviceData.get("db-delete"), serviceData.get("db-update"), serviceData.get("db-add") );

//        System.out.println("-------------------------------------------");
//        service.print(serviceData.get("db-add"));
    }

    public dbService getService() {
        return service;
    }
    public void setService(dbService service) {
        this.service = service;
    }
}
