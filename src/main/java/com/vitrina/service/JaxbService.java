package com.vitrina.service;

import com.vitrina.domain.Issue;
import com.vitrina.service.jaxb.IssueJAXB;
import com.vitrina.service.jaxb.Issues;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandr on 28.07.15.
 */
public class JaxbService {

//    public static void main(String[] args) throws JAXBException {
//        JaxbService jaxb = new JaxbService();
//
////        File f = new File("issues.xml");
////        ServiceIssue service = new ServiceIssue();
////        Issues issues = new Issues();
////
////        List<IssueJDBC> listIssue = jaxb.convertIssues(service.toList(new LinkedList<>()));
////        issues.setIssues(listIssue);
////
////        System.out.println("************************************************[ Marshaling ]");
////        jaxb.marshaling(issues, f);
//
//        ///////////////////////////////////////////////////////////////////////////////////////
//        String[] files = {"issues1.xml","issues2.xml","issues3.xml"};
//
//        System.out.println("************************************************[ UnMarshaling ]");
//        List<IssueJAXB> issues = new LinkedList<>();
//        for (String file:files)
//            issues.addAll(jaxb.unMarshaling(file));
//        jaxb.print(issues);
//    }


    public List<IssueJAXB> convertIssues(List<IssueJAXB> issues){
        List<IssueJAXB> jaxbIssues = new ArrayList<>();
        for(Issue issue:issues)
            jaxbIssues.add(new IssueJAXB(issue.getId(), issue.getParentId(), issue.getProjectId(), issue.getProjectName(), issue.getTrackerId(), issue.getTrackerName(), issue.getStatusId(), issue.getStatusName(), issue.getFixedVersionId(), issue.getFixedVersionName(), issue.getSubject(), issue.getStartDate(), issue.getDueDate()));

        return jaxbIssues;
    }

    public void marshaling(Issues issues, String file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Issues.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(issues, new File(file));
    }

    public List<Issue> unMarshaling(String file) throws JAXBException { //public List<IssueJAXB> unMarshaling(String file) throws JAXBException {
        JAXBContext       context = JAXBContext.newInstance(Issues.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Issues issues = (Issues) unmarshaller.unmarshal(new File(file));
        return issues.getIssues();
    }

    public void print(List<IssueJAXB> issues){
        for (Issue issue:issues)
            System.out.println(issue);
    }
}
