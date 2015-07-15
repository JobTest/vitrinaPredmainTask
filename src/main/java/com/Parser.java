package com;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dn200978lak
 */
public class Parser extends DefaultHandler {

    public boolean bIssueID    = false;
    public boolean bSubject    = false;
    public boolean bStart_date = false;
    public boolean bDue_date   = false;

    public Issue issue;
    public List<Issue> issues;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        /* Tag 'Issue-ID' */
        if (qName.equalsIgnoreCase("id")) {
            issue    = new Issue();
            bIssueID = true;
        }

        /* Tag 'Project' */
        if (qName.equals("project")) {
            issue.setProjectId( Integer.parseInt(attributes.getValue("id")) );
            issue.setProjectName( attributes.getValue("name") );
//            System.out.println(">> Project-ID: " + attributes.getValue("id"));
//            System.out.println(">> Project-NAME: " + attributes.getValue("name"));
        }

        /* Tag 'Tracker' */
        if (qName.equals("tracker")) {
            issue.setTrackerId(Integer.parseInt(attributes.getValue("id")));
            issue.setTrackerName(attributes.getValue("name"));
//            System.out.println(">> Tracker-ID: " + attributes.getValue("id"));
//            System.out.println(">> Tracker-NAME: " + attributes.getValue("name"));
        }

        /* Tag 'Status' */
        if (qName.equals("status")) {
            issue.setStatusId(Integer.parseInt(attributes.getValue("id")));
            issue.setStatusName(attributes.getValue("name"));
//            System.out.println(">> Status-ID: " + attributes.getValue("id"));
//            System.out.println(">> Status-NAME: " + attributes.getValue("name"));
        }

        /* Tag 'Fixed-Version' */
        if (qName.equals("fixed_version")) {
            issue.setFixedVersionId(Integer.parseInt(attributes.getValue("id")));
            issue.setFixedVersionName(attributes.getValue("name"));
//            System.out.println(">> Fixed-Version-ID: " + attributes.getValue("id"));
//            System.out.println(">> Fixed-Version-NAME: " + attributes.getValue("name"));
        }

        /* Tag 'Parent' */
        if (qName.equals("parent")) {
            issue.setParentId(Integer.parseInt(attributes.getValue("id")));
//            System.out.println(">> Parent-ID: " + attributes.getValue("id"));
        }

        /* Tag 'Subject' */
        if (qName.equalsIgnoreCase("subject")) {
            bSubject = true;
        }

        /* Tag 'Start-Date' */
        if (qName.equalsIgnoreCase("start_date")) {
            bStart_date = true;
        }

        /* Tag 'Due-Date' */
        if (qName.equalsIgnoreCase("due_date")) {
            bDue_date = true;
        }

        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        /* Tag 'Issue' */
        if (bIssueID) {
            issue.setId(Integer.parseInt(new String(ch, start, length)));
//            System.out.println("> Issue-ID: " + new String(ch, start, length));
            bIssueID = false;
        }

        /* Tag 'Subject' */
        if (bSubject) {
            issue.setSubject(new String(ch, start, length));
//            System.out.println(">> Subject: " + new String(ch, start, length));
            bSubject = false;
        }

        /* Tag 'Subject' */
        if (bStart_date) {
            issue.setStartDate(new String(ch, start, length));
//            System.out.println(">> Start-Date: " + new String(ch, start, length));
            bStart_date = false;
        }

        /* Tag 'Subject' */
        if (bDue_date) {
            issue.setDueDate(new String(ch, start, length));
//            System.out.println(">> Due-Date: " + new String(ch, start, length));
            bDue_date = false;
        }

        super.characters(ch, start,  length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        /* Tag 'Issue' */
        if (qName.equalsIgnoreCase("issue")) {
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
//        Iterator<Issue> iissues = issues.iterator();
//        Issue iissue;
//        while (iissues.hasNext()){
//            iissue = iissues.next();
//            System.out.println("ID: " + iissue.getId());
//            System.out.println("ID (Parent): " + iissue.getParentId());
//            System.out.println("Project-ID: " + iissue.getProjectId());
//            System.out.println("Project-NAME: " + iissue.getProjectName());
//            System.out.println("Tracker-ID: " + iissue.getTrackerId());
//            System.out.println("Tracker-NAME: " + iissue.getTrackerName());
//            System.out.println("Status-ID: " + iissue.getStatusId());
//            System.out.println("Status-NAME: " + iissue.getStatusName());
//            System.out.println("Fixed-Version-ID: " + iissue.getFixedVersionId());
//            System.out.println("Fixed-Version-NAME: " + iissue.getFixedVersionName());
//            System.out.println("Subject: " + iissue.getSubject());
//            System.out.println("Start-Date: " + iissue.getStartDate());
//            System.out.println("Due-Date: " + iissue.getDueDate());
//
//            System.out.println();
//        }

        super.endDocument();
    }


    public List<Issue> getIssues(){
        return issues;
    }
}
