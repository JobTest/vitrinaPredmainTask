package com.vitrina.service;

import com.vitrina.domain.Issue;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dn200978lak
 */
public class SaxParserService extends DefaultHandler {

    private boolean bIssueID    = false;
    private boolean bSubject    = false;
    private boolean bStart_date = false;
    private boolean bDue_date   = false;
    private Issue issue;
    private List<Issue> issues;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        /* Tag 'Parent' */
        if (qName.equals("parent"))
            issue.setParentId(Integer.parseInt(attributes.getValue("id")));
        /* Tag 'Subject' */
        if (qName.equalsIgnoreCase("subject"))
            bSubject = true;
        /* Tag 'Start-Date' */
        if (qName.equalsIgnoreCase("start_date"))
            bStart_date = true;
        /* Tag 'Due-Date' */
        if (qName.equalsIgnoreCase("due_date"))
            bDue_date = true;

        /* Tag 'Issue-ID' */
        if (qName.equalsIgnoreCase("id")) {
            bIssueID = true;
            issue = new Issue();
        }
        /* Tag 'Project' */
        if (qName.equals("project")) {
            issue.setProjectId( Integer.parseInt(attributes.getValue("id")) );
            issue.setProjectName( attributes.getValue("name") );
        }
        /* Tag 'Tracker' */
        if (qName.equals("tracker")) {
            issue.setTrackerId(Integer.parseInt(attributes.getValue("id")));
            issue.setTrackerName(attributes.getValue("name"));
        }
        /* Tag 'Status' */
        if (qName.equals("status")) {
            issue.setStatusId(Integer.parseInt(attributes.getValue("id")));
            issue.setStatusName(attributes.getValue("name"));
        }
        /* Tag 'Fixed-Version' */
        if (qName.equals("fixed_version")) {
            issue.setFixedVersionId(Integer.parseInt(attributes.getValue("id")));
            issue.setFixedVersionName(attributes.getValue("name"));
        }

        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        /* Tag 'Issue' */
        if (bIssueID) {
            issue.setId(Integer.parseInt(new String(ch, start, length)));
            bIssueID = false;
        }
        /* Tag 'Subject' */
        if (bSubject) {
            issue.setSubject(new String(ch, start, length));
            bSubject = false;
        }
        /* Tag 'Subject' */
        if (bStart_date) {
            issue.setStartDate(new String(ch, start, length));
            bStart_date = false;
        }
        /* Tag 'Subject' */
        if (bDue_date) {
            issue.setDueDate(new String(ch, start, length));
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
        issues = new LinkedList<>();

        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }


    public List<Issue> getIssues(){
        return issues;
    }
}
