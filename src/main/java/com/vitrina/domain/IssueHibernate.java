package com.vitrina.domain;

import javax.persistence.*;

/**
* @author dn200978lak
* @version 1.0
* {@link http://www.skipy.ru/technics/objCompTh.html}
* {@link http://2rrr.ru/?Operaciya_sozdaniya_obtzekta:Operator_instanceof}
* **************************
* Сравнение объектов: теория
* Оператор instanceof
*/

@Entity
@Table(name = "issue")
@NamedQueries({
        @NamedQuery(name = "Issue.findAll", query = "SELECT issue FROM IssueHibernate issue"),
        @NamedQuery(name = "Issue.findByProject", query = "SELECT issue FROM IssueHibernate issue WHERE issue.ProjectName = :ProjectName"),
        @NamedQuery(name = "Issue.findByTracker", query = "SELECT issue FROM IssueHibernate issue WHERE issue.TrackerName = :TrackerName")
})
public class IssueHibernate implements Issue {

    @Id
    @Column(name = "id")
    private Integer Id;

    @Column(name = "parent_id")
    private int ParentId;

    @Column(name = "project_id")
    private int ProjectId;

    @Column(name = "project_name")
    private String ProjectName;

    @Column(name = "tracker_id")
    private int TrackerId;

    @Column(name = "tracker_name")
    private String TrackerName;

    @Column(name = "status_id")
    private int StatusId;

    @Column(name = "status_name")
    private String StatusName;

    @Column(name = "fixed_version_id")
    private int FixedVersionId;

    @Column(name = "fixed_version_name")
    private String FixedVersionName;

    @Column(name = "subject")
    private String Subject;

    @Column(name = "start_date")
    private String StartDate;

    @Column(name = "due_date")
    private String DueDate;

    public IssueHibernate(){}
    public IssueHibernate(Integer Id, int ParentId, int ProjectId, String ProjectName, int TrackerId, String TrackerName, int StatusId, String StatusName, int FixedVersionId, String FixedVersionName, String Subject, String StartDate, String DueDate){
        this.Id = Id;
        this.ParentId = ParentId;
        this.ProjectId = ProjectId;
        this.ProjectName = ProjectName;
        this.TrackerId = TrackerId;
        this.TrackerName = TrackerName;
        this.StatusId = StatusId;
        this.StatusName = StatusName;
        this.FixedVersionId = FixedVersionId;
        this.FixedVersionName = FixedVersionName;
        this.Subject = Subject;
        this.StartDate = StartDate;
        this.DueDate = DueDate;
    }

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

    /**
     *      'правило симметрии' - (если А==В, то В==А) для любых x!=null и y!=null вызов x.equals(y) должен вернуть true тогда и только тогда, когда вызов y.equals(x) возвращает true
     * 'правило транзитивности' - (если А==В и В==С, то А==С) для любых x, y и z, не равных null, таких, что x.equals(y)==true и y.equals(z)==true, выполняется также и x.equals(z)==true
     * 'правило рефлексивности' - (объект всегда равен самому себе) для любого x!=null вызов x.equals(x) должен вернуть true
     */
    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
        if (!(o instanceof Issue))
            return false;

        Issue issue = (Issue) o;
        if (Id != issue.getId())
            return false;
        if (ParentId != issue.getParentId())
            return false;
        if (ProjectId != issue.getProjectId())
            return false;
        if (TrackerId != issue.getTrackerId())
            return false;
        if (DueDate != null ? !DueDate.equals(issue.getDueDate()) : issue.getDueDate() != null)
            return false;
        if (ProjectName != null ? !ProjectName.equals(issue.getProjectName()) : issue.getProjectName() != null)
            return false;
        if (StartDate != null ? !StartDate.equals(issue.getStartDate()) : issue.getStartDate() != null)
            return false;
        if (TrackerName != null ? !TrackerName.equals(issue.getTrackerName()) : issue.getTrackerName() != null)
            return false;
//        if (FixedVersionId != issue.FixedVersionId) return false;
//        if (StatusId != issue.StatusId) return false;
//        if (FixedVersionName != null ? !FixedVersionName.equals(issue.FixedVersionName) : issue.FixedVersionName != null) return false;
//        if (StatusName != null ? !StatusName.equals(issue.StatusName) : issue.StatusName != null) return false;
//        if (Subject != null ? !Subject.equals(issue.Subject) : issue.Subject != null) return false;

        return true;
    }

}
