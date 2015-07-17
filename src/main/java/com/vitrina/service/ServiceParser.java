package com.vitrina.service;

import com.vitrina.entity.Issue;
import com.vitrina.entity.Target;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Collection;
import java.util.LinkedList;

// import SAX

/**
 *
 * @author dn200978lak
 * @version 2.0
 */
public class ServiceParser extends DefaultHandler {

    public ServiceParser(String target){
        this.target = target;
    }

    public String  target      = "debt";
    public boolean bIssueID    = false;
    public boolean bSubject    = false;
    public boolean bStart_date = false;
    public boolean bDue_date   = false;

    public Issue issue;
    public Collection<Issue> issues;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        /**
         * Tag 'Issue-ID'
         */
        if (qName.equalsIgnoreCase("id")) {
            issue    = new Issue();
            bIssueID = true;
        }

        /**
         * Tag 'Project'
         */
        if (qName.equals("project")) {
            issue.setProjectId( Integer.parseInt(attributes.getValue("id")) );
            issue.setProjectName( attributes.getValue("name") );
        }

        /**
         * Tag 'Tracker'
         */
        if (qName.equals("tracker")) {
            issue.setTrackerId(Integer.parseInt(attributes.getValue("id")));
            issue.setTrackerName(attributes.getValue("name"));
        }

        /**
         * Tag 'Status'
         */
        if (qName.equals("status")) {
            issue.setStatusId(Integer.parseInt(attributes.getValue("id")));
            issue.setStatusName(attributes.getValue("name"));
        }

        /**
         * Tag 'Fixed-Version'
         */
        if (qName.equals("fixed_version")) {
            issue.setFixedVersionId(Integer.parseInt(attributes.getValue("id")));

//            issue.setFixedVersionName(attributes.getValue("name"));
            String fixedVersionName    = attributes.getValue("name");
            String getFixedVersionName = attributes.getValue("name");
            if( getFixedVersionName != null ){
                if( !getFixedVersionName.equals("null") ){
                    String[] aFixedVersionName = getFixedVersionName.split(" ");
                    fixedVersionName = aFixedVersionName[0];
                }
            }
            issue.setFixedVersionName(fixedVersionName);
        }

        /**
         * Tag 'Parent'
         */
        if (qName.equals("parent")) {
            issue.setParentId(Integer.parseInt(attributes.getValue("id")));
        }

        /**
         * Tag 'Subject'
         */
        if (qName.equalsIgnoreCase("subject")) {
            bSubject = true;
        }

        /**
         * Tag 'Start-Date'
         */
        if (qName.equalsIgnoreCase("start_date")) {
            bStart_date = true;
        }

        /**
         * Tag 'Due-Date'
         */
        if (qName.equalsIgnoreCase("due_date")) {
            bDue_date = true;
        }

        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        /**
         * Tag 'Issue'
         */
        if (bIssueID) {
            issue.setId(Integer.parseInt(new String(ch, start, length)));
            bIssueID = false;
        }

        /**
         * Tag 'Subject'
         */
        if (bSubject) {
            issue.setSubject(new String(ch, start, length));
            bSubject = false;
        }

        /**
         * Tag 'Subject'
         */
        if (bStart_date) {
//            issue.setStartDate(new String(ch, start, length));
            String startDate = new String(ch, start, length);
            if( startDate.length() > 10 )
                startDate = startDate.substring(0, 10);
            issue.setStartDate(startDate);
            bStart_date = false;
        }

        /**
         * Tag 'Subject'
         */
        if (bDue_date) {
//            issue.setDueDate(new String(ch, start, length));
            String dueDate = new String(ch, start, length);
            if( dueDate.length() > 10 )
                dueDate = dueDate.substring(0, 10);
            issue.setDueDate(dueDate);
            bDue_date = false;
        }

        super.characters(ch, start,  length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        /**
         * Tag 'Issue'
         */
        if (qName.equalsIgnoreCase("issue")) {
            issue.setTarget(Target.debt);
            if( target.equals("biplane_web") )
                issue.setTarget(Target.biplane_web);
            if( target.equals("irbis") )
                issue.setTarget(Target.irbis);

            issues.add(issue);
        }

        super.endElement(uri,localName, qName);
    }

    @Override
    public void startDocument() throws SAXException {
        issues = new LinkedList<Issue>();

        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }


    public Collection<Issue> getIssues(){
        return issues;
    }
}
