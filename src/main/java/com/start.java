package com;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dn200978lak
 */
public class start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        insertDB();
        selectDB();
    }

    public static void selectDB(){
        try {
            VitrinaDbUpdateSosTask dao = new VitrinaDbUpdateSosTask();
            dao.readDataBase();
        } catch (SQLException e) { System.err.println(e.getMessage());
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }

    public static void insertDB(){
        System.out.println("********************************[ SAXParser ]*********************************");
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(false);
            SAXParser saxparser = factory.newSAXParser();

            Parser    xmlIssues = new Parser();
            saxparser.parse(new File("issues.xml"), xmlIssues);

            Map<Integer, Issue> map = new HashMap<>();

            List<Issue> issues = xmlIssues.getIssues();
            for(Issue issue : issues)
                map.put(issue.getId(), issue);

            for (Map.Entry entry : map.entrySet())
                System.out.println(entry);


            System.out.println("********************************[ Connection ]*********************************");
            try {
                VitrinaDbUpdateSosTask dao = new VitrinaDbUpdateSosTask();
                for (Map.Entry entry : map.entrySet()){
                    Issue issue = (Issue) entry.getValue();
                    String sql = "INSERT INTO issue (id, parent_id, project_id, project_name, tracker_id, tracker_name, fixed_version_id, fixed_version_name, status_id, status_name, subject, start_date, due_date)VALUES (" + issue.getId() + "," + issue.getParentId() + "," + issue.getProjectId() + ",'" + issue.getProjectName() + "'," + issue.getTrackerId() + ",'" + issue.getTrackerName() + "'," + issue.getStatusId() + ",'" + issue.getStatusName() + "'," + issue.getFixedVersionId() + ",'" + issue.getFixedVersionName() + "','" + issue.getSubject().replace("'", "") + "','" + issue.getStartDate() + "','" + issue.getDueDate() + "');";
                    dao.writeDataBase(sql);
                }
            } catch (SQLException e) { System.err.println(e.getMessage());
            } catch (Exception e) { System.err.println(e.getMessage()); }

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

}

