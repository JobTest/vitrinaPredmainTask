package com.vitrina.service.jaxb;

import com.vitrina.domain.Issue;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by alexandr on 28.07.15.
 */

@XmlRootElement(name = "issues")
@XmlAccessorType (XmlAccessType.FIELD)
public class Issues {
    @XmlElement(name = "issue")
    private List<Issue> issues = null; //private List<IssueJAXB> issues = null;

    public List<Issue> getIssues() {   //public List<IssueJAXB> getIssues() {
        return issues;
    }
    public void setIssues(List<Issue> issues) { //public void setIssues(List<IssueJAXB> issues) {
        this.issues = issues;
    }
}
