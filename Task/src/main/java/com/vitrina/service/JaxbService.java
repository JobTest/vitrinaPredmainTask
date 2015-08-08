package com.vitrina.service;

import com.vitrina.domain.Issue;
import com.vitrina.service.jaxb.IssueJAXB;
import com.vitrina.service.jaxb.IssuesJAXB;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexandr on 28.07.15.
 */
public class JaxbService {

//    public static void main(String[] args) throws JAXBException {
//        JaxbService jaxb = new JaxbService();
//
//        File f = new File("issues.xml");
//        ServiceIssue service = new ServiceIssue();
//        Issues issues = new Issues();
//
//        List<IssueJDBC> listIssue = service.toList(new LinkedList<>());
//        issues.setIssues(listIssue);
//
//        System.out.println("************************************************[ Marshaling ]");
//        jaxb.marshaling(issues, f);

        /////////////////////////////////////////////////////////////////////////////////////
//        String[] files = {"issues1.xml","issues2.xml","issues3.xml"};
//
//        System.out.println("************************************************[ UnMarshaling ]");
//        List<IssueJAXB> issues = new LinkedList<>();
//        for (String file:files)
//            issues.addAll(jaxb.unMarshaling(file));
//        jaxb.print(issues);
//    }

    public void marshaling(List<Issue> issues, String file) throws JAXBException { //public void marshaling(IssuesJAXB issues, String file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(IssuesJAXB.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(convert(issues), new File(file));
    }
    public <X> IssuesJAXB convert(List<Issue> issues){
        List<X> jaxbIssues = new ArrayList<>();
        for (Issue issue:issues)
            jaxbIssues.add( (X)issue );
        return (IssuesJAXB)jaxbIssues;
    }

    public List<Issue> unMarshaling(String file) throws JAXBException {
        JAXBContext       context = JAXBContext.newInstance(IssuesJAXB.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        IssuesJAXB issues = (IssuesJAXB) unmarshaller.unmarshal(new File(file));
        return unConvert(issues.getIssues());
    }
    public <X> List<Issue> unConvert(List<X> xIssues){
        List<Issue> issues = new LinkedList<>();
        for (X xIssue:xIssues)
            issues.add( (Issue)xIssue );
        return issues;
    }

    public void print(List<IssueJAXB> issues){
        for (Issue issue:issues)
            System.out.println(issue);
    }
}
