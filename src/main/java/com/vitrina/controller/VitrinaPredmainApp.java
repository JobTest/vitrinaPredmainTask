package com.vitrina.controller;

import com.vitrina.domain.Issue;
import com.vitrina.service.DBService;

import java.io.File;
import java.util.*;

/**
 * Created by alexandr on 15.07.15.
 */
public class VitrinaPredmainApp {

    public VitrinaPredmainApp(){
//        configFile = "jdbc_postgresql.properties"; //jdbc_postgresql.properties//jdbc_mysql.properties
//        configFile  = "hibernate_postgresql.cft.xml"; //hibernate_postgresql.cft.xml//hibernate_mysql.cft.xml
//        configFile = "PostgresSQLIssueJPA"; //MySQLIssueJPA//PostgresSQLIssueJPA//OracleIssueJPA
//        configFile = "spring.properties";
        dueDates    = new String[]{"0","10","20","40","70","100"};
        folder.put(".xml", new File("data"));
//        files = new String[]{"issues1.xml","issues2.xml","issues3.xml"};
        serviceData = Collections.synchronizedMap(new HashMap<>());
    }

    public void start() {
        serviceData.putAll(service.loadData(configFile, folder));
        System.out.println("\n********************************[ Old << DB (" + serviceData.get("old-db").size() + ") ]******************************");
//        print(serviceData.get("old-db"));
        System.out.println("\n********************************[ New << XML (" + serviceData.get("new-xml").size() + ") ]*****************************");
//        print(serviceData.get("new-xml"));

        serviceData.putAll( service.parseData(serviceData.get("old-db"), serviceData.get("new-xml"), dueDates) );
        System.out.println("\n********************[ Delete/Update/Add >> DB (-" + serviceData.get("db-delete").size() + "/~" + serviceData.get("db-update").size() + "/+" + serviceData.get("db-add").size() + ") ]*********************");
//        System.out.println( "db-delete: |>> (" + (serviceData.get("old-db").size()-serviceData.get("db-delete").size()) + ")  -" + serviceData.get("db-delete").size());
//        System.out.println( "db-update: |>> (" + (serviceData.get("old-db").size()-serviceData.get("db-delete").size()) + ")  ~" + serviceData.get("db-update").size() );
//        System.out.println( "   db-add: |>> (" + (serviceData.get("old-db").size()-serviceData.get("db-delete").size()+serviceData.get("db-add").size()) + ") +" + serviceData.get("db-add").size() );
        service.updateData( serviceData.get("db-delete"), serviceData.get("db-update"), serviceData.get("db-add") );

//        System.out.println("-------------------------------------------");
//        print(serviceData.get("db-add"));
    }

    public void print(List<Issue> issues){
        for (Issue issue:issues)
            System.out.println(issue);
    }


    public DBService getService() {
        return service;
    }
    public void setService(DBService service) {
        this.service = service;
    }

    private DBService service;
    private final String              configFile = null;
    private final String[]              dueDates;
    private final Map<String,File>        folder = new HashMap<>();
    //private final String[]                 files = {"issues1.xml","issues2.xml","issues3.xml"};
    private Map<String, List<Issue>> serviceData;
}
