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

    public void prepareDB(List<Issue> oldData, List<Issue> newData, String[] oldDates){
        List<Issue> DELETE_OLD = new LinkedList<>();
        List<Issue> REMAIN_OLD = new LinkedList<>();
        List<Issue>    ADD_NEW = new LinkedList<>();
        List<Issue> REMAIN_NEW = new LinkedList<>();
        REMAIN_OLD.addAll(oldData);
        DELETE_OLD.addAll(oldData);
        REMAIN_NEW.addAll(newData);
        ADD_NEW.addAll(newData);

        /* (1) Из базы удаляю устаревшие по времени записи */
        for (String oldDate:oldDates){
//            Predicate<Issue> dueDate = issue -> strDueDate.equals(issue.getDueDate());
            Predicate<Issue> dueDate = new Predicate<Issue>(){
                @Override
                public boolean test(Issue issue){
                    return oldDate.equals(issue.getDueDate());
                }
            };
            REMAIN_OLD.removeIf(dueDate);
        }
        DELETE_OLD.removeAll(REMAIN_OLD);

        /* (2) Добавляю новые записи в базу */
        for (Issue remain_old:REMAIN_OLD) {
            Predicate<Issue> id = new Predicate<Issue>() {
                @Override
                public boolean test(Issue issue) {
                    return Integer.valueOf(remain_old.getId()).equals(issue.getId());
                }
            };
            ADD_NEW.removeIf(id);
        }

        /* (2) Обновляю поля в базе для существующих записей */
        REMAIN_NEW.removeAll(ADD_NEW);
        REMAIN_NEW.removeAll(REMAIN_OLD);

        map.put("db-delete",DELETE_OLD);
        map.put("db-update",REMAIN_NEW);
        map.put("db-add",ADD_NEW);
    }
    public void executeDB(List<Issue> delete, List<Issue> update, List<Issue> add){
        deleteDB(delete);
        updateDB(update);
        addDB(add);
    }
    private void deleteDB(List<Issue> issues){
        try {
            for (Issue issue:issues)
                dao.delete(issue.getId());
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }
    private void addDB(List<Issue> issues){
        try {
            dao.add( issues );
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }
    private void updateDB(List<Issue> issues){
        try {
            for (Issue issue:issues)
                dao.update(issue);
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }
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

