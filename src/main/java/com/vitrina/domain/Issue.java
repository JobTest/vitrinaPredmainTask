package com.vitrina.domain;

/**
* @author dn200978lak
* @version 1.0
* {@link http://www.skipy.ru/technics/objCompTh.html}
* {@link http://2rrr.ru/?Operaciya_sozdaniya_obtzekta:Operator_instanceof}
* **************************
* Сравнение объектов: теория
* Оператор instanceof
*/

public class Issue {

    private Integer Id;
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

    public Issue(){}
    public Issue(Integer Id, int ParentId, int ProjectId, String ProjectName, int TrackerId, String TrackerName, int StatusId, String StatusName, int FixedVersionId, String FixedVersionName, String Subject, String StartDate, String DueDate){
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
        if (Id != issue.Id)
            return false;
        if (ParentId != issue.ParentId)
            return false;
        if (ProjectId != issue.ProjectId)
            return false;
        if (TrackerId != issue.TrackerId)
            return false;
        if (DueDate != null ? !DueDate.equals(issue.DueDate) : issue.DueDate != null)
            return false;
        if (ProjectName != null ? !ProjectName.equals(issue.ProjectName) : issue.ProjectName != null)
            return false;
        if (StartDate != null ? !StartDate.equals(issue.StartDate) : issue.StartDate != null)
            return false;
        if (TrackerName != null ? !TrackerName.equals(issue.TrackerName) : issue.TrackerName != null)
            return false;
//        if (FixedVersionId != issue.FixedVersionId) return false;
//        if (StatusId != issue.StatusId) return false;
//        if (FixedVersionName != null ? !FixedVersionName.equals(issue.FixedVersionName) : issue.FixedVersionName != null) return false;
//        if (StatusName != null ? !StatusName.equals(issue.StatusName) : issue.StatusName != null) return false;
//        if (Subject != null ? !Subject.equals(issue.Subject) : issue.Subject != null) return false;

        return true;
    }

}
