package com;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by alexandr on 15.07.15.
 */
public class start {

    public static void main(String[] args) {
        System.out.println("\n********************************[ SAXParser ]*********************************");
        Map<Integer, Issue> mapSAX = toMap(new File("issues.xml"));
//        print(mapSAX);

        System.out.println("\n********************************[ INSERT ]*********************************");
//        toDB(mapSAX);

        System.out.println("\n********************************[ SELECT ]*********************************");
        Map<Integer, Issue> mapDB = toMap("SELECT * FROM issue");
//        print(mapDB);

        //////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("\n-----------------------");
        Map<Issue, Integer> map = new HashMap<>();

        System.out.println("mapSAX = " + mapSAX.size());
        System.out.println("mapDB = " + mapDB.size());
        System.out.println("map = " + map.size());

//        List l = null;
//        l.retainAll()



//        for (Entry entry : map.entrySet()) {
//            if (Integer.valueOf(147444).equals(entry.getKey())){
//                System.out.println(entry.getValue());
//            }
////            else
////                System.out.println("{" + entry.getKey() + "}");
//        }
    }

    public static void print(Map<Integer, Issue> map){
        for (Map.Entry entry : map.entrySet())
            System.out.println(entry);
    }

    private static Map<Integer, Issue> toMap(File f){
        Map<Integer, Issue> map = new HashMap<>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(false);
            javax.xml.parsers.SAXParser saxparser = factory.newSAXParser();

            SaxParser xmlIssues = new SaxParser();
            saxparser.parse(f, xmlIssues);

            List<Issue> issues = xmlIssues.getIssues();
            for(Issue issue : issues)
                map.put(issue.getId(), issue);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); /* обработки ошибки, файл не найден */
        } catch (ParserConfigurationException e) {
            e.printStackTrace(); /* обработка ошибки Parser */
        } catch (SAXException e) {
            e.printStackTrace(); /* обработка ошибки SAX */
        } catch (IOException e) {
            e.printStackTrace(); /* обработка ошибок ввода */
        }
        return map;
    }

    private static Map<Integer, Issue> toMap(String sql){
        Map<Integer, Issue> map = null;
        try {
            DB db = new DB();
            map = db.select(sql);
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }
        return map;
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
