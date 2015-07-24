package com.vitrina.controller;

import com.vitrina.service.ServiceIssue;

import java.util.LinkedList;

/**
 * Created by alexandr on 15.07.15.
 */
public class start {

    private static ServiceIssue service;

    public static void main(String[] args) {
        service = new ServiceIssue();

        System.out.println("\n********************************[ DB-getAll ]*********************************");
        service.map.put("db-getAll", service.toList(new LinkedList<>()));
        service.print(service.map.get("db-getAll"));

        System.out.println("\n********************************[ SAX-Parser ]*********************************");
        String[] files = {"issue1.xml","issue2.xml","issue3.xml"};
        service.map.put("sax-upload", service.toList(files));
//        service.print(service.map.get("sax-upload"));

        System.out.println("\n********************************[ DB-Add ]*********************************");
        String[] dueDates = {"0","10","100"};
//        service.insert(service.map.get("db-getAll"), service.map.get("sax-upload"), dueDates);
    }
}
