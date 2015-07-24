package com.vitrina.controller;

import com.vitrina.service.ServiceIssue;

/**
 * Created by alexandr on 15.07.15.
 */
public class start {

    private static ServiceIssue service;

    public static void main(String[] args) {
        service = new ServiceIssue();

        System.out.println("\n********************************[ DB SELECT ]*********************************");
        service.map.put("db-select", service.toList("SELECT * FROM issue")); //service.map.put("db-select", service.toList());
//        print(service.map.get("db-select"));

        System.out.println("\n********************************[ SAXParser ]*********************************");
        String[] files = {"issue1.xml","issue2.xml","issue3.xml"};
        service.map.put("sax-upload", service.toList(files));
//        print(service.map.get("sax-upload"));

        System.out.println("\n********************************[ DB INSERT ]*********************************");
        String[] dueDates = {"0","10","100"};
        service.insert(service.map.get("db-select"), service.map.get("sax-upload"), dueDates);
    }
}
