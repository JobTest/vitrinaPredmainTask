package com.vitrina.service;

import com.vitrina.dao.IssueDao;
import com.vitrina.domain.Issue;
import org.xml.sax.SAXException;

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
 */
public class ServiceIssue {

    public Map<String, List<Issue>> map = Collections.synchronizedMap(new HashMap<>());

    public List<Issue> toList(String sql){
        List<Issue> issues = null;
        try {
            IssueDao db = new IssueDao();
            issues = db.select(sql);
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

    public void insert(List<Issue> db, List<Issue> sax, String[] dueDates){
        /* Из базы удаляю устаревшие по времени записи */
        List<Issue> deleteDB = new LinkedList<>();
        deleteDB.addAll(db);
        for (String strDueDate:dueDates){
            Predicate<Issue> dueDate = issue -> strDueDate.equals(issue.getDueDate());
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
            Predicate<Issue> id = issue -> Integer.valueOf(DB.getId()).equals(issue.getId());
            insertDB.removeIf(id);
        }
        map.put("db-insert",insertDB);

        /* Обновляю поля в базе для существующих записей */
        updateDB.removeAll(insertDB);
        map.put("db-update",updateDB);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("db-select:  << (== ) " + map.get("db-select").size());
        System.out.println("sax-upload: << (== ) " + map.get("sax-upload").size());
        System.out.println("db-delete:  >> (-" + (map.get("db-select").size()-map.get("db-delete").size()) + " ) " + map.get("db-delete").size());
        System.out.println("db-update:  >> (=" + map.get("db-update").size() + " ) " + map.get("db-delete").size());
        System.out.println("db-insert:  >> (+" + map.get("db-insert").size() + ") " + (map.get("db-delete").size()+map.get("db-insert").size()));
//        int insert = insert(map.get("db-select"), map.get("sax-upload"));
//        System.out.println("insert << " + insert);
    }

    public int insert(List<Issue> db, List<Issue> sax){
        Map<String, List<Issue>> map = Collections.synchronizedMap(new HashMap<>());
        map.put("sax", sax);
        map.put("db", db);

        map.get("sax").removeAll(map.get("db")); //map.get("sax").retainAll(map.get("db"));
        try {
            IssueDao dao = new IssueDao();
            for (Iterator iterator = map.get("sax").iterator(); iterator.hasNext();){
                Issue issue = (Issue)iterator.next();
                String sql = "INSERT INTO issue (id, parent_id, project_id, project_name, tracker_id, tracker_name, fixed_version_id, fixed_version_name, status_id, status_name, subject, start_date, due_date)VALUES (" + issue.getId() + "," + issue.getParentId() + "," + issue.getProjectId() + ",'" + issue.getProjectName() + "'," + issue.getTrackerId() + ",'" + issue.getTrackerName() + "'," + issue.getStatusId() + ",'" + issue.getStatusName() + "'," + issue.getFixedVersionId() + ",'" + issue.getFixedVersionName() + "','" + issue.getSubject().replace("'", "") + "','" + issue.getStartDate() + "','" + issue.getDueDate() + "');";
                dao.insert(sql);
            }
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }

        return map.get("sax").size();
    }

    public void print(List<Issue> issues){
        for (Issue issue : issues)
            System.out.println(issue);
    }

}
