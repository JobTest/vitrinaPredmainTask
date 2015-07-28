package com.vitrina.service;

import com.vitrina.dao.IssueDao;
import com.vitrina.domain.Issue;
import com.vitrina.service.jaxb.IssueJAXB;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Predicate;

/**
 * Created by alexandr on 17.07.15.
 ** {@link http://www.javacodegeeks.com/2012/05/functional-style-in-java-with.html}
 * {@link http://stackoverflow.com/questions/18163328/jpa-utf-8-characters-not-persisted}
 * ****************************************
 * Functional style in Java with predicates
 */
public class dbService {

    public Map<String, List<Issue>> map;
    public IssueDao                 dao;
    public JaxbService              jaxb;

    public dbService(){
        map = Collections.synchronizedMap(new HashMap<>());
        //dao = FactoryDao.getIssue(DAO.JPA); //dao = FactoryDao.getIssue(DAO.HIBERNATE); //dao = FactoryDao.getIssue(DAO.JDBC);
        //jaxb = new JaxbService();
    }

    public List<Issue> toList(List<Issue> select){
        List<Issue> issues = null;
        try {
            issues = dao.getAll(select);
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }
        return issues;
    }
    public List<Issue> toList(String[] files){
        List<Issue>  issues = new LinkedList<>();
        try {
            for (String file:files)
                issues.addAll( jaxb.unMarshaling(file) );
        } catch(JAXBException e){ System.err.println(e.getMessage()); }
        return issues;
    }

    public void toFile(List<Issue> issues){

    }

    public void insert(List<Issue> db, List<Issue> sax, String[] dueDates){
        /* Из базы удаляю устаревшие по времени записи */
        List<Issue> deleteDB = new LinkedList<>();
        deleteDB.addAll(db);
        for (String strDueDate:dueDates){
//            Predicate<Issue> dueDate = issue -> strDueDate.equals(issue.getDueDate());
            Predicate<Issue> dueDate = new Predicate<Issue>(){
                @Override
                public boolean test(Issue issue){
                    return strDueDate.equals(issue.getDueDate());
                }
            };
            deleteDB.removeIf(dueDate);
        }
        map.put("db-delete",deleteDB);

        List<Issue> updateDB = new LinkedList<>();
        updateDB.addAll(sax);
        updateDB.removeAll(deleteDB);

        /* Добавляю новые записи в базу */
        List<Issue> insertDB = new LinkedList<>();
        insertDB.addAll(updateDB);
        for (Issue DB:deleteDB) {
//            Predicate<Issue> id = issue -> Integer.valueOf(DB.getId()).equals(issue.getId());
            Predicate<Issue> id = new Predicate<Issue>(){
                @Override
                public boolean test(Issue issue) {
                    return Integer.valueOf(DB.getId()).equals(issue.getId());
                }
            };
            insertDB.removeIf(id);
        }
        map.put("db-add",insertDB);

        /* Обновляю поля в базе для существующих записей */
        updateDB.removeAll(insertDB);
        map.put("db-update",updateDB);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("     db-getAll: |<< ( == ) " + map.get("db-getAll").size());
        System.out.println("   jaxb-upload: |<< ( == ) " + map.get("jaxb-upload").size());
        System.out.println("   ---------------------------");
        System.out.println("     db-delete: |>> (-" + (map.get("db-getAll").size()-map.get("db-delete").size()) + " ) " + map.get("db-delete").size());
        System.out.println("     db-update: |>> (=" + map.get("db-update").size() + " ) " + map.get("db-delete").size());
        System.out.println("        db-add: |>> (+" + map.get("db-add").size() + " ) " + (map.get("db-delete").size()+map.get("db-add").size()));
        int add = addDB(map.get("db-getAll"), map.get("jaxb-upload"));
//        System.out.println("add << " + add);
    }

    public int addDB(List<Issue> db, List<Issue> sax){
        Map<String, List<Issue>> map = Collections.synchronizedMap(new HashMap<>());
        map.put("sax", sax);
        map.put("db", db);

        map.get("sax").removeAll(map.get("db"));
        try {
            dao.add( map.get("sax") );
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }

        return map.get("sax").size();
    }

    public void print(List<Issue> issues){
        for (Issue issue:issues)
            System.out.println(issue);
    }

    public Map<String, List<Issue>> getMap() {
        return map;
    }
    public IssueDao getDao() {
        return dao;
    }
    public JaxbService getJaxb() {
        return jaxb;
    }
    public void setMap(Map<String, List<Issue>> map) {
        this.map = map;
    }
    public void setDao(IssueDao dao) {
        this.dao = dao;
    }
    public void setJaxb(JaxbService jaxb) {
        this.jaxb = jaxb;
    }
}
