package com;

/**
 *
 * @author dn200978lak
 * @version 1.0
 */

public class Issue {

    private int Id;
    private int ParentId;
    private int ProjectId;
    private String ProjectName;
    private int TrackerId;
    private String TrackerName;
    private int StatusId;
    private String StatusName;
    private int FixedVersionId;
    private String FixedVersionName;
    private String Subject;
    private String StartDate;
    private String DueDate;


    public int getId(){
        return Id;
    }
    public int getParentId(){
        return ParentId;
    }
    public int getProjectId(){
        return ProjectId;
    }
    public  String getProjectName(){
        return ProjectName;
    }
    public int getTrackerId(){
        return TrackerId;
    }
    public String getTrackerName(){
        return TrackerName;
    }
    public int getStatusId(){
        return StatusId;
    }
    public String getStatusName(){
        return StatusName;
    }
    public int getFixedVersionId(){
        return FixedVersionId;
    }
    public String getFixedVersionName(){
        return FixedVersionName;
    }
    public String getSubject(){
        return Subject;
    }
    public String getStartDate(){
        return StartDate;
    }
    public String getDueDate(){
        return DueDate;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    public void setParentId(int ParentId){
        this.ParentId = ParentId;
    }
    public void setProjectId(int ProjectId){
        this.ProjectId = ProjectId;
    }
    public void setProjectName(String ProjectName){
        this.ProjectName = ProjectName;
    }
    public void setTrackerId(int TrackerId){
        this.TrackerId = TrackerId;
    }
    public void setTrackerName(String TrackerName){
        this.TrackerName = TrackerName;
    }
    public void setStatusId(int StatusId){
        this.StatusId = StatusId;
    }
    public void setStatusName(String StatusName){
        this.StatusName = StatusName;
    }
    public void setFixedVersionId(int FixedVersionId){
        this.FixedVersionId = FixedVersionId;
    }
    public void setFixedVersionName(String FixedVersionName){
        this.FixedVersionName = FixedVersionName;
    }
    public void setSubject(String Subject){
        this.Subject = Subject;
    }
    public void setStartDate(String StartDate){
        this.StartDate = StartDate;
    }
    public void setDueDate(String DueDate){
        this.DueDate = DueDate;
    }

    @Override
    public String toString() {
        return "{" +
                Id +
                ", " + ParentId +
                ", " + ProjectId +
                ", '" + ProjectName + '\'' +
                ", " + TrackerId +
                ", '" + TrackerName + '\'' +
                ", " + StatusId +
                ", '" + StatusName + '\'' +
                ", " + FixedVersionId +
                ", '" + FixedVersionName + '\'' +
                ", '" + Subject + '\'' +
                ", '" + StartDate + '\'' +
                ", '" + DueDate + '\'' +
                '}';
    }
}
