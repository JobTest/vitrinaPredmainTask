package com.vitrina.service.jaxb;

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
public class IssuesJAXB {
    @XmlElement(name = "issue")
    private List<IssueJAXB> issues = null;

    public List<IssueJAXB> getIssues() {
        return issues;
    }
    public void setIssues(List<IssueJAXB> issues) { //public void setIssues(List<IssueJAXB> issues) {
        this.issues = issues;
    }
}
