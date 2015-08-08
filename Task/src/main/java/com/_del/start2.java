//package com._del;
//
//import com.vitrina.dao.IssueDao;
//import com.vitrina.domain.Issue;
//import com.vitrina.service.SaxParserService;
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.parsers.SAXParserFactory;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.*;
//import java.util.function.Predicate;
//
///**
// * Created by alexandr on 15.07.15.
// */
//public class start2 {
//
//    public static void main(String[] args) {
//        System.out.println("\n********************************[ DB SELECT ]*********************************");
//        List<Issue> listDB = toList("SELECT * FROM issue");
////        System.out.println("db << " + listDB.size());
////        print(listDB);
//
//        System.out.println("\n********************************[ SAXParser ]*********************************");
//        String[] files = {"issue1.xml", "issue2.xml", "issue3.xml"};
//        List<Issue> listSAX = toList(files);
////        System.out.println("sax << " + listSAX.size());
////        print(listSAX);
//
////        System.out.println("\n********************************[ DB INSERT ]*********************************");
////        int insert = insert(listSAX, listDB);
////        System.out.println("insert = " + insert);
//////        print(listDB);
//
//        ////////////////////////////////////////////////////////////////////////////////////////////////////////
////        System.out.println("\n-----------------------\nsax << " + listSAX.size());
////
////        listSAX.removeAll(listDB);
////        for (Issue DB:listDB) {
////            Predicate<Issue> id = issue -> Integer.valueOf(DB.getId()).equals(issue.getId());
////            listSAX.removeIf(id);
////        }
////
////        System.out.println( "sax >> " + listSAX.size() );
//        ////////////////////////////////////////////////////////////////////////////////////////////////////////
////        Map<String, List<Issue>> map = Collections.synchronizedMap(new HashMap<>());
////        map.put("db-select",listDB);
////        map.put("ax-upload",listSAX);
//
//        List<Issue> deleteDB = new LinkedList<>();
//        deleteDB.addAll(listDB);
//        Predicate<Issue> dueDate_0 = issue -> "0".equals(issue.getDueDate());
//        Predicate<Issue> dueDate_10 = issue -> "10".equals(issue.getDueDate());
//        Predicate<Issue> dueDate_100 = issue -> "100".equals(issue.getDueDate());
//        deleteDB.removeIf(dueDate_0.or(dueDate_10).or(dueDate_100));
////        map.put("db-delete",deleteDB);
//
//        List<Issue> updateDB = new LinkedList<>();
//        updateDB.addAll(listSAX);
//        updateDB.removeAll(deleteDB);
//
//        List<Issue> insertDB = new LinkedList<>();
//        insertDB.addAll(updateDB);
//        for (Issue DB:deleteDB) {
//            Predicate<Issue> id = issue -> Integer.valueOf(DB.getId()).equals(issue.getId());
//            insertDB.removeIf(id);
//        }
////        map.put("db-insert",insertDB);
//
//        updateDB.removeAll(insertDB);
////        map.put("db-update",updateDB);
//
//
//        System.out.println("-----------------------");
//        System.out.println("db-select:  <<      " + listDB.size());
//        System.out.println("sax-upload: <<      " + listSAX.size());
//        System.out.println("db-delete:  >> (" + (listDB.size()-deleteDB.size()) + ")  " + deleteDB.size());
//        System.out.println("db-update:  >> (" + updateDB.size() + ")  " + deleteDB.size());
//        System.out.println("db-insert:  >> (" + insertDB.size() + ") " + (deleteDB.size()+insertDB.size()));
//    }
//
//
//    public static List<Issue> toList(String sql){
//        List<Issue> issues = null;
//        try {
//            IssueDao db = new IssueDao();
//            issues = db.select(sql);
//        } catch (SQLException e) { System.err.println(e.getMessage());
//        } catch (Exception e) { System.err.println(e.getMessage()); }
//        return issues;
//    }
//
//    public static List<Issue> toList(String[] files){
//        List<Issue> issues = new LinkedList<>();
//        for(String file:files){
//            try {
//                SAXParserFactory factory = SAXParserFactory.newInstance();
//                factory.setValidating(true);
//                factory.setNamespaceAware(false);
//                javax.xml.parsers.SAXParser saxparser = factory.newSAXParser();
//
//                SaxParserService xmlIssues = new SaxParserService();
//                saxparser.parse(new File(file), xmlIssues);
//
//                issues.addAll(xmlIssues.getIssues());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace(); /* обработки ошибки, файл не найден */
//            } catch (ParserConfigurationException e) {
//                e.printStackTrace(); /* обработка ошибки Parser */
//            } catch (SAXException e) {
//                e.printStackTrace(); /* обработка ошибки SAX */
//            } catch (IOException e) {
//                e.printStackTrace(); /* обработка ошибок ввода */
//            }
//        }
//        return issues;
//    }
//
//    public static int insert(List<Issue> sax, List<Issue> db){
//        Map<String, List<Issue>> map = Collections.synchronizedMap(new HashMap<>());
//        map.put("sax", sax);
//        map.put("db", db);
//
//        map.get("sax").removeAll(map.get("db")); //map.get("sax").retainAll(map.get("db"));
//        try {
//            IssueDao dao = new IssueDao();
//            for (Iterator iterator = map.get("sax").iterator(); iterator.hasNext();){
//                Issue issue = (Issue)iterator.next();
//                String sql = "INSERT INTO issue (id, parent_id, project_id, project_name, tracker_id, tracker_name, fixed_version_id, fixed_version_name, status_id, status_name, subject, start_date, due_date)VALUES (" + issue.getId() + "," + issue.getParentId() + "," + issue.getProjectId() + ",'" + issue.getProjectName() + "'," + issue.getTrackerId() + ",'" + issue.getTrackerName() + "'," + issue.getStatusId() + ",'" + issue.getStatusName() + "'," + issue.getFixedVersionId() + ",'" + issue.getFixedVersionName() + "','" + issue.getSubject().replace("'", "") + "','" + issue.getStartDate() + "','" + issue.getDueDate() + "');";
//                dao.insert(sql);
//            }
//        } catch (SQLException e) { System.err.println(e.getMessage());
//        } catch (Exception e) { System.err.println(e.getMessage()); }
//
//        return map.get("sax").size();
//    }
//
//    public static void print(List<Issue> issues){
//        for (Issue issue : issues)
//            System.out.println(issue);
//    }
//}
