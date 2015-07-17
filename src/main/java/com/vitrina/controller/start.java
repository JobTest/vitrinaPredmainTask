package com.vitrina.controller;

import com.vitrina.dao.IssueDao;
import com.vitrina.domain.Issue;
import com.vitrina.service.SaxParserService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by alexandr on 15.07.15.
 */
public class start {

    public static void main(String[] args) {
        System.out.println("\n********************************[ SAXParser ]*********************************");
        String[] files = {"issue1.xml", "issue2.xml", "issue3.xml"};
        List<Issue> lSAX = toList(files);



        System.out.println("sax = " + lSAX.size());
//        print(lSAX);

        System.out.println("\n********************************[ DB SELECT ]*********************************");
        List<Issue> lDB = toList("SELECT * FROM issue");
        System.out.println("db = " + lDB.size());
//        print(lDB);

        System.out.println("\n********************************[ DB INSERT ]*********************************");
        int insert = insert(lSAX, lDB);
        System.out.println("insert = " + insert);
//        print(lDB);

        //////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\n-----------------------");

//        Map<String, List<Issue>> map = new HashMap<>();
//        map.put("sax", lSAX);
//        map.put("db", lDB);
//
//        System.out.println("sax = " + map.get("sax").size());
//        System.out.println("db = " + map.get("db").size());
//        map.get("sax").removeAll(map.get("db"));
////        map.get("sax").retainAll(map.get("db"));
//        System.out.println("add = " + map.get("sax").size());
//
//        System.out.println("\n-----------------------");
//        for (Iterator iterator = map.get("sax").iterator(); iterator.hasNext();)
//            System.out.println(iterator.next());
    }


    public static int insert(List<Issue> lSAX, List<Issue> lDB){
        Map<String, List<Issue>> map = Collections.synchronizedMap(new HashMap<String, List<Issue>>());
        map.put("sax", lSAX);
        map.put("db", lDB);

        map.get("sax").removeAll(map.get("db")); //map.get("sax").retainAll(map.get("db"));
        try {
            IssueDao db = new IssueDao();
            for (Iterator iterator = map.get("sax").iterator(); iterator.hasNext();){
                Issue issue = (Issue)iterator.next();
                String sql = "INSERT INTO issue (id, parent_id, project_id, project_name, tracker_id, tracker_name, fixed_version_id, fixed_version_name, status_id, status_name, subject, start_date, due_date)VALUES (" + issue.getId() + "," + issue.getParentId() + "," + issue.getProjectId() + ",'" + issue.getProjectName() + "'," + issue.getTrackerId() + ",'" + issue.getTrackerName() + "'," + issue.getStatusId() + ",'" + issue.getStatusName() + "'," + issue.getFixedVersionId() + ",'" + issue.getFixedVersionName() + "','" + issue.getSubject().replace("'", "") + "','" + issue.getStartDate() + "','" + issue.getDueDate() + "');";
                db.insert(sql);
            }
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }

        return map.get("sax").size();
    }

    private static List<Issue> toList(String[] files){
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

    private static List<Issue> toList(String sql){
        List<Issue> issues = null;
        try {
            IssueDao db = new IssueDao();
            issues = db.select(sql);
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }
        return issues;
    }

    public static void print(List<Issue> issues){
        for (Issue issue : issues)
            System.out.println(issue);
    }
}
