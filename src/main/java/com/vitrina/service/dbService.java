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
        List<Issue> issues = new LinkedList<>();
        for(String file:files){
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                factory.setValidating(true);
                factory.setNamespaceAware(false);
                javax.xml.parsers.SAXParser saxparser = factory.newSAXParser();

                SaxParserService xmlIssues = new SaxParserService();
                saxparser.parse(new File(file), xmlIssues);

                issues.addAll(xmlIssues.getIssues());
            } catch (FileNotFoundException e) {
                e.printStackTrace(); /* обработки ошибки, файл не найден */
            } catch (ParserConfigurationException e) {
                e.printStackTrace(); /* обработка ошибки Parser */
            } catch (SAXException e) {
                e.printStackTrace(); /* обработка ошибки SAX */
            } catch (IOException e) {
                e.printStackTrace(); /* обработка ошибок ввода */
            }
        }
        return issues;
    }
//    public List<Issue> toList(String[] files){
//        List<Issue>  issues = new LinkedList<>();
//        try {
//            for (String file:files)
//                issues.addAll( jaxb.unMarshaling(file) );
//        } catch(JAXBException e){ System.err.println(e.getMessage()); }
//        return issues;
//    }

    public void toFile(List<Issue> issues){

    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    public void prepareDB(List<Issue> db, List<Issue> xml, String[] dueDates){
        List<Issue> DELETE_DB = new LinkedList<>();
        List<Issue> UPDATE_DB = new LinkedList<>();
        List<Issue>    ADD_DB = new LinkedList<>();

        /* (1) Из базы удаляю устаревшие по времени записи */
        List<Issue> remainDB = new LinkedList<>();
        remainDB.addAll(db);
        for (String strDueDate:dueDates){
//            Predicate<Issue> dueDate = issue -> strDueDate.equals(issue.getDueDate());
            Predicate<Issue> dueDate = new Predicate<Issue>(){
                @Override
                public boolean test(Issue issue){
                    return strDueDate.equals(issue.getDueDate());
                }
            };
            remainDB.removeIf(dueDate);
        }
        DELETE_DB.addAll(db);
        DELETE_DB.removeAll(remainDB);

        /* (2) Обновляю поля в базе для существующих записей */
        List<Issue> update = new LinkedList<>();

        List<Issue> update1 = new LinkedList<>();
        List<Issue> update2 = new LinkedList<>();
        List<Issue> update3 = new LinkedList<>();
        update1.addAll(xml);
        update2.addAll(xml);

        update1.retainAll(remainDB);
        for (Issue remainID2:remainDB){
            Predicate<Issue> id2 = new Predicate<Issue>() {
                @Override
                public boolean test(Issue issue) {
                    return Integer.valueOf(remainID2.getId()).equals(issue.getId());
                }
            };
            update2.removeIf(id2);
        }
        update3.addAll(remainDB);
        update3.removeAll(update1);

        System.out.println("   update1 = " + update1.size());
        System.out.println("   update2 = " + update2.size());
        System.out.println("   update3 = " + update3.size());

        System.out.println("   xml = " + xml.size());
        System.out.println("remain = " + remainDB.size());
        System.out.println("update = " + update.size() + " (" + (remainDB.size()-update.size()) + ")");
        System.out.println("----------------------------");
//        for (Issue issue:update)
//            System.out.println("(" + issue.getId() + ")   DueDate: '" + issue.getDueDate() + "';   FixedVersionName: '" + issue.getFixedVersionName() + "';   ProjectName: '" + issue.getProjectName() + "';");
//        System.out.println("(ID) Update | Remain");
//        for (int remainIndex=0; remainIndex<remainDB.size(); remainIndex++) //for (int updateIndex=0; updateIndex<update.size(); updateIndex++)
//            for (int updateIndex=0; updateIndex<update.size(); updateIndex++) //for (int remainIndex=0; remainIndex<remainDB.size(); remainIndex++)
//                if (remainDB.get(remainIndex).getId() == update.get(updateIndex).getId())
//                    System.out.println("(" + update.get(updateIndex).getId() + ") " + update.get(updateIndex).equals(remainDB.get(remainIndex)) + "  " + updateIndex + " | " + remainIndex);
        for (Issue issue:update)
            for (int remainIndex=0; remainIndex<remainDB.size(); remainIndex++)
                if (remainDB.get(remainIndex).getId() == issue.getId())
                    System.out.println(issue.equals(remainDB.get(remainIndex)) + "\n" + issue + "\n" + remainDB.get(remainIndex) + "\n");

        UPDATE_DB.addAll(remainDB);
        UPDATE_DB.removeAll(update);
        /////////////////////////////////
//        List<Issue> updateDB1 = new LinkedList<>();
//        updateDB1.addAll(remainDB);
//        List<Issue> updateDB2 = new LinkedList<>();
//        updateDB2.addAll(remainDB);
//        for (Issue update:xml){
//            Predicate<Issue> id = new Predicate<Issue>(){
//                public boolean test(Issue issue){
//                    return Integer.valueOf(update.getId()).equals(issue.getId());
//                }
//            };
//            updateDB1.removeIf(id);
//        }
//        updateDB2.retainAll(updateDB1);
////        UPDATE_DB.removeAll(updateDB1);

        /* (3) Добавляю новые записи в базу */
        ADD_DB.addAll(xml);
        for (Issue remain:remainDB) {
            Predicate<Issue> id = new Predicate<Issue>() {
                @Override
                public boolean test(Issue issue) {
                    return Integer.valueOf(remain.getId()).equals(issue.getId());
                }
            };
            ADD_DB.removeIf(id);
        }

        map.put("db-delete",DELETE_DB);
        map.put("db-update",UPDATE_DB);
        map.put("db-add",ADD_DB);
    }
    public void executeDB(List<Issue> deletes, List<Issue> updates, List<Issue> adds){
        deleteDB(deletes);
        updateDB(updates);
        addDB(adds);
    }
    public void deleteDB(List<Issue> issues){
        try {
            for (Issue issue:issues)
                dao.delete(issue.getId());
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }
    public void addDB(List<Issue> issues){
        try {
            dao.add( issues );
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }
    public void updateDB(List<Issue> issues){
        try {
            for (Issue issue:issues)
                dao.update(issue);
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////

//    public void insert(){
////        System.out.println("     db-getAll: |<< ( == ) " + map.get("db-getAll").size());
////        System.out.println("   jaxb-upload: |<< ( == ) " + map.get("jaxb-upload").size());
////        System.out.println("   ---------------------------");
////        System.out.println("     db-delete: |>> (-" + (map.get("db-getAll").size()-map.get("db-delete").size()) + " ) " + map.get("db-delete").size());
////        System.out.println("     db-update: |>> (=" + map.get("db-update").size() + " ) " + map.get("db-delete").size());
////        System.out.println("        db-add: |>> (+" + map.get("db-add").size() + " ) " + (map.get("db-delete").size()+map.get("db-add").size()));
//        ////////////////////////////////////////////////////////////////////////////////////////////////////////
//        int add = addDB(map.get("db-getAll"), map.get("jaxb-upload"));
////        System.out.println("add << " + add);
//    }
//    public int addDB(List<Issue> db, List<Issue> jaxb){
//        Map<String, List<Issue>> map = Collections.synchronizedMap(new HashMap<>());
//        map.put("jaxb", jaxb);
//        map.put("db", db);
//        map.get("jaxb").removeAll(map.get("db"));
//        try {
//            dao.add( map.get("jaxb") );
//        } catch (SQLException e) { System.err.println(e.getMessage());
//        } catch (Exception e) { System.err.println(e.getMessage()); }
//
//        return map.get("jaxb").size();
//    }

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

