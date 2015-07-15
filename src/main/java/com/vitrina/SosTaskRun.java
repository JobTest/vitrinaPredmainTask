package com.vitrina;

import com.vitrina.service.ServiceParser;
import com.vitrina.service.ServiceParserAnalize;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author dn200978lak
 * @version 2.0
 */
public class SosTaskRun {

    public static void main(String[] args) {

        System.out.print("Passed config params ..... ");
        if (1 == args.length) {
            System.out.println(args.length);




            /**
             * Build URLs
             *
             * http://10.1.99.58/predmine/projects/debt/issues.xml?page=1&per_page=100&limit=100&key=46a4f326da314ddcb707ed41429450122446f786
             * http://10.1.99.58/predmine/projects/biplane-web/issues.xml?page=1&per_page=100&limit=100&key=46a4f326da314ddcb707ed41429450122446f786
             * http://10.1.99.58/predmine/projects/irbis/issues.xml?page=1&per_page=100&limit=100&key=46a4f326da314ddcb707ed41429450122446f786
             */
            String[] params = new String[3];
            params[0] = "http://" + args[0] + "/predmine/projects/debt/issues.xml?page=1&per_page=100&limit=100&key=46a4f326da314ddcb707ed41429450122446f786";        // (IP-xml) debt
            params[1] = "http://" + args[0] + "/predmine/projects/biplane-web/issues.xml?page=1&per_page=100&limit=100&key=46a4f326da314ddcb707ed41429450122446f786"; // (IP-xml) biplane-web
            params[2] = "http://" + args[0] + "/predmine/projects/irbis/issues.xml?page=1&per_page=100&limit=100&key=46a4f326da314ddcb707ed41429450122446f786";       // (IP-xml) irbis

//            /**
//             *  Exec..
//             */
//            new AlgorithmVitrinaPredmain(params[0], "debt");
//            new AlgorithmVitrinaPredmain(params[1], "biplane_web", "add");
//            new AlgorithmVitrinaPredmain(params[2], "irbis", "add");

            /*
             * page=1&per_page=100&limit=100
             * page=2&per_page=32&limit=100
             *
             * page=1&per_page=100&limit=100
             * page=2&per_page=87&limit=100
             *
             */



//            System.out.println( "Total Count .............. " + xmlParser(1, 100, 100) );
            System.out.println();
            System.out.println( "Total Count: " + xmlAnalyse("debt", "46a4f326da314ddcb707ed41429450122446f786") );
            System.out.println();
            System.out.println( "Total Count: " + xmlAnalyse("biplane_web", "46a4f326da314ddcb707ed41429450122446f786") );
            System.out.println();
            System.out.println( "Total Count: " + xmlAnalyse("irbis", "46a4f326da314ddcb707ed41429450122446f786") );



            return;
        } else {
            System.out.println("ERROR");
            return;
        }
    }




    /**
     * SAXParser
     *
     * @param xml
     * @return ServiceParserAnalize
     */
    public static int xmlAnalyse(String xml,String key) {
        Date startDate               = new Date();
        SimpleDateFormat baseFormat  = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        SimpleDateFormat parseFormat = new SimpleDateFormat("HH:mm:ss");
        String           baseDate    = "09/26/2013 00:00:00";

        System.out.println("Analyse-START ............ " + baseFormat.format(startDate) + " ("+startDate.getTime()+")");

//        String urlParser = "http://10.1.99.58/predmine/projects/debt/issues.xml?page=" + page + "&per_page=" + per_page + "&limit=" + limit + "&key=46a4f326da314ddcb707ed41429450122446f786";
        String urlParser = "http://10.1.99.58/predmine/projects/" + xml + "/issues.xml?page=1&per_page=1&limit=1&key=" + key;


        System.out.println("Name XML ................. " + xml);
        System.out.print("Load XML ................. ");
        int                  totalCount = 0;
        int                  fSize      = 0;
        boolean              isLoad     = false;
        ServiceParserAnalize vitrina    = null;
        URL                  url;
        URLConnection        conn       = null;
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
            System.out.println( String.format("%,d Kb", fSize) );
        } else {
            System.out.println("ERROR");
            return totalCount;
        }

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(false);
        SAXParser saxparser;
        boolean isParse = false;
        try {
            vitrina    = new ServiceParserAnalize();
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
            totalCount = vitrina.getTotalCount();
        } else {
            totalCount = -1;
        }

        Date stopDate = new Date();
        long delta = stopDate.getTime() - startDate.getTime();

        Calendar         calendar = Calendar.getInstance();
        try {
            calendar.setTime(baseFormat.parse(baseDate));
        } catch (ParseException e) {
//                e.printStackTrace();
        }
        long deltaTime = calendar.getTimeInMillis() + delta;
        System.out.println( "Differnce Time ........... " +parseFormat.format(new Date(deltaTime)) + " (" + delta + ")");
        System.out.println("Analyse-STOP ............. " + baseFormat.format(stopDate) + " ("+stopDate.getTime()+")");

        return totalCount;
    }
}
