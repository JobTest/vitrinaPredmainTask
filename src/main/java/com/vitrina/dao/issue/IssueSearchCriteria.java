package com.vitrina.dao.issue;

import java.util.Date;

/**
 * Created by Саша on 30.07.2015.
 */
public class IssueSearchCriteria {
    private Date minDateRelease;
    private Date maxDateRelease;
    private String title;
    private String comment;
    private Integer authorId;

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getComment() {
        return comment;
    }
    public void setMinDateRelease(Date minDateRelease) {
        this.minDateRelease = minDateRelease;
    }
    public Date getMinDateRelease() {
        return minDateRelease;
    }
    public void setMaxDateRelease(Date maxDateRelease) {
        this.maxDateRelease = maxDateRelease;
    }
    public Date getMaxDateRelease() {
        return maxDateRelease;
    }
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
    public Integer getAuthorId() {
        return authorId;
    }
    public boolean isEmpty() {
        return minDateRelease == null && maxDateRelease == null
                && title == null && comment == null
                && authorId == null;
    }

//    public boolean accept(Issue issue) {
//        return acceptMinDateRelease(issue.getDateRelease())
//                && acceptMaxDateRelease(issue.getDateRelease())
//                && acceptTitle(issue.getTitle())
//                && acceptComment(issue.getComment())
//                && acceptAuthorId(issue.getAuthorId());
//    }

    private boolean acceptAuthorId(Integer authorId) {
        return this.authorId == null || authorId.equals(authorId);
    }

    private boolean acceptComment(String comment) {
        return this.comment == null || this.comment.equals(comment);
    }

    private boolean acceptTitle(String title) {
        return this.title == null || this.title.equals(title);
    }

    private boolean acceptMaxDateRelease(Date dateRelease) {
        return maxDateRelease == null || maxDateRelease.before(dateRelease);
    }

    private boolean acceptMinDateRelease(Date dateRelease) {
        return minDateRelease == null || minDateRelease.before(dateRelease);
    }

}
