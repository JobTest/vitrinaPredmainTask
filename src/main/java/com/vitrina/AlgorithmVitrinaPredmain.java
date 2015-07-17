package com.vitrina;

import com.vitrina.entity.Issue;
import com.vitrina.service.ServiceDb;
import com.vitrina.service.ServiceParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author dn200978lak
 * @version 2.0
 */
public class AlgorithmVitrinaPredmain {

    private String            LOG            = "";
    private String            attribut       = "";
    private ServiceParser     VITRINA_PARSER = null;
    private ServiceDb         VITRINA_DB     = null;
    private Collection<Issue> UPDATE_ISSUES  = new LinkedList<Issue>();

    public AlgorithmVitrinaPredmain(String url, String target) {
        init(url, target);

        /**
         * (2)
         */
        try{
            UPDATE_ISSUES = getCheckUpdateIssues(VITRINA_DB.getIssues(), VITRINA_PARSER.getIssues());
        } catch (NullPointerException npe){}

        getUpdateIssues( UPDATE_ISSUES );
        logOutput("vitrinaPredmainTask.log", LOG);
        System.out.println();
    }
    public AlgorithmVitrinaPredmain(String url, String target, String attribut) {
        this.attribut = attribut;
        init( url, target );

        /**
         * (2)
         */
        try{
            UPDATE_ISSUES = getCheckUpdateIssues( VITRINA_DB.getIssues(), VITRINA_PARSER.getIssues() );
        } catch (NullPointerException npe){}

        getUpdateIssues( UPDATE_ISSUES );
        logOutput("vitrinaPredmainTask.log", LOG);
        System.out.println();
    }

    /**
     *
     * @param url_xml
     */
    public void init(String url_xml, String target){
        VITRINA_PARSER = xmlParser(url_xml, target);
        VITRINA_DB     = new ServiceDb();
    }


    /**
     * SAXParser
     *
     * @param urlParser
     * @return ServiceParser
     */
    public ServiceParser xmlParser(String urlParser, String target) {
        System.out.print("Load XML ................. ");
        int                  fSize   = 0;
        boolean              isLoad  = false;
        ServiceParser vitrina = null;
        URL url;
        URLConnection conn     = null;
        try {
            url  = new URL(urlParser);
            conn = url.openConnection();
            fSize = conn.getContentLength();
            isLoad = true;
        } catch (IOException e) {
//            e.printStackTrace();
            isLoad = false;
        }
        if(isLoad){
            System.out.println( urlParser + " [" + fSize + "]");
            LOG += "   " + target + ".xml " + fSize + "Kb.";
        } else {
            System.out.println("ERROR");
            LOG += "   " + target + ".xml ERROR";
            return vitrina;
        }

        System.out.print("Parse XML ................ ");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(false);
        SAXParser saxparser;
        boolean isParse = false;
        try {
            vitrina    = new ServiceParser(target);
            saxparser  = factory.newSAXParser();
            saxparser.parse(conn.getInputStream(), vitrina);

            isParse = true;
        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
            isParse = false;
        } catch (SAXException e) {
//            e.printStackTrace();
            isParse = false;
        } catch (IOException e) {
//            e.printStackTrace();
            isParse = false;
        }
        if(isParse){
            System.out.println("YES");
            LOG += "   Parse-XML YES";
        } else {
            System.out.println("ERROR");
            LOG += "   Parse-XML ERROR";
        }

        return vitrina;
    }

    /**
     * Algorithm fiend update
     *
     * @param pasDbIssues
     * @param pasParserIssues
     * @return Collection<Issue>
     */
    public Collection<Issue> getCheckUpdateIssues(Collection<Issue> pasDbIssues, Collection<Issue> pasParserIssues) {
        System.out.print("Check for Updates ........ ");

        Collection<Issue> update_issues = new LinkedList<Issue>();
        int             countTotal      = 0;
        int             countMatchs     = 0;
        int             countUpdate     = 0;
        Issue           vitrinaIssue    = null;
        Iterator<Issue> vitrinaIssues   = pasParserIssues.iterator();

        while (vitrinaIssues.hasNext()) {
            vitrinaIssue             = vitrinaIssues.next();
            Issue           dbIssue  = null;
            Iterator<Issue> dbIssues = pasDbIssues.iterator();

            if (pasDbIssues.size() > 0) {
                boolean isUpdate = true;
                while (dbIssues.hasNext()) {
                    dbIssue = dbIssues.next();

                    if( vitrinaIssue.getId() == dbIssue.getId() ){  // vitrinaIssue.getStartDate().equals(dbIssue.getStartDate())
                        countMatchs++;
                        isUpdate = false;
                        break;
                    }
                }
                if(isUpdate) {
                    update_issues.add(vitrinaIssue);
                    countUpdate++;
                }
            } else {
                update_issues.add(vitrinaIssue);
                countUpdate++;
            }
            countTotal++;
        }
//        System.out.println("Total: " + countTotal + "; Match: " + countMatchs + "; Updated: " + countUpdate + ";");
        if(0 < countUpdate){
            System.out.println(countUpdate);
            LOG += "   Check for Updates " + countUpdate;
        } else {
            System.out.println("NO");
            LOG += "   Check for Updates NO";
        }

        return update_issues;
    }

    /**
     * Algorithm fiend update
     *
     * @param issues
     * @return Collection<Issue>
     */
    public void getUpdateIssues(Collection<Issue> issues) {
        if (UPDATE_ISSUES.size() > 0) {
            /*
             * 3.
             */
            if(attribut.equals(""))
                VITRINA_DB.clear();

            /*
             * 4.
             */
            System.out.print("DB Updated ............... ");
            int countUpdate = 0;
            Iterator iParser = UPDATE_ISSUES.iterator();
            while (iParser.hasNext()){
                VITRINA_DB.add( (Issue)iParser.next() );
                countUpdate++;
            }
            System.out.print( countUpdate );
            LOG += "   DB Updated " + countUpdate;
        }
    }


    /**
     *
     * @param file
     * @param log
     */
    public void logOutput(String file, String log) {
        DateFormat df    = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date       today = Calendar.getInstance().getTime();
        System.out.println("\nDate ..................... " + df.format(today));
        System.out.print("Log File ................. ");
        boolean    isLog     = false;

//        File script = new File(log);
//        FileWriter out = null;
//        try {
//            out = new FileWriter(script);
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        FileReader logReader = null;

        try {
            logReader               = new FileReader(file);

            BufferedReader buffered = new BufferedReader(logReader);
            String         strRead  = buffered.readLine();
            String         strWrite = log;
            int lines               = 0;
            while( strRead != null ){
                strWrite += "\n" + strRead;
                strRead   = buffered.readLine();
                lines++;
            }
            FileWriter logWriter = new FileWriter(file);
            logWriter.write("#" + lines + "   " + df.format(today) + strWrite);
            logWriter.close();
            logReader.close();
            buffered.close();

            isLog = true;
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            isLog = false;
        } catch (IOException e) {
//            e.printStackTrace();
            isLog = false;
        }

        if(isLog){
            System.out.println(file);
        } else {
            System.out.println("ERROR");
        }
    }
}
