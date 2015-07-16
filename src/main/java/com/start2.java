package com;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by alexandr on 15.07.15.
 */
public class start2 {

    public static void main(String[] args) {
        System.out.println("\n********************************[ SAXParser ]*********************************");
        List<Issue> lSAX = toList(new File("issues.xml"));
//        print(lSAX);

        System.out.println("\n********************************[ INSERT ]*********************************");
//        toDB(lSax);

        System.out.println("\n********************************[ SELECT ]*********************************");
        List<Issue> lDB = toList("SELECT * FROM issue");
//        print(lDB);

        //////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\n-----------------------");

        Map<String, List<Issue>> map = new HashMap<>();
        map.put("sax", lSAX);
        map.put("db", lDB);

        System.out.println("sax = " + map.get("sax").size());
        System.out.println("db = " + map.get("db").size());

//        List<Issue> add = map.get("sax");
//        List<Issue> remove = map.get("db");
        System.out.println("add = " + lSAX.size());
        System.out.println("remove = " + lDB.size());
        lSAX.removeAll(lDB);
//        add.retainAll(remove);
        System.out.println("add = " + lSAX.size());

//        System.out.println("\n-----------------------");
//        for (Iterator iterator = lSAX.iterator(); iterator.hasNext();)
//            System.out.println(iterator.next());
    }



    private static List<Issue> toList(File f){
        List<Issue> issues = null;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(false);
            javax.xml.parsers.SAXParser saxparser = factory.newSAXParser();

            SaxParser xmlIssues = new SaxParser();
            saxparser.parse(f, xmlIssues);

            issues = xmlIssues.getIssues();
        } catch (FileNotFoundException e) {
            e.printStackTrace(); /* обработки ошибки, файл не найден */
        } catch (ParserConfigurationException e) {
            e.printStackTrace(); /* обработка ошибки Parser */
        } catch (SAXException e) {
            e.printStackTrace(); /* обработка ошибки SAX */
        } catch (IOException e) {
            e.printStackTrace(); /* обработка ошибок ввода */
        }
        return issues;
    }

    private static List<Issue> toList(String sql){
        List<Issue> issues = null;
        try {
            DB db = new DB();
            issues = db.select2(sql);
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }
        return issues;
    }

    public static void print(List<Issue> issues){
        for (Issue issue : issues)
            System.out.println(issue);
    }


    private static void toDB(Map<Integer, Issue> map){
        try {
            DB db = new DB();
            for (Entry entry : map.entrySet()){
                Issue issue = (Issue) entry.getValue();
                String sql = "INSERT INTO issue (id, parent_id, project_id, project_name, tracker_id, tracker_name, fixed_version_id, fixed_version_name, status_id, status_name, subject, start_date, due_date)VALUES (" + issue.getId() + "," + issue.getParentId() + "," + issue.getProjectId() + ",'" + issue.getProjectName() + "'," + issue.getTrackerId() + ",'" + issue.getTrackerName() + "'," + issue.getStatusId() + ",'" + issue.getStatusName() + "'," + issue.getFixedVersionId() + ",'" + issue.getFixedVersionName() + "','" + issue.getSubject().replace("'", "") + "','" + issue.getStartDate() + "','" + issue.getDueDate() + "');";
                db.insert(sql);
            }
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }
}
