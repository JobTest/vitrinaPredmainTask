package com.vitrina.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author dn200978lak
 * @version 2.0
 */
@Entity
@Table(name = "issue")
@NamedQuery(name = "Issue.getAll", query = "SELECT c from Issue c")
public class Issue implements Serializable{

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", length = 6, nullable = false)
    private long Id;

    /**
     * ParentId
     */
    @Column(name="parent_id", length = 6)
    private long ParentId;

    /**
     * ProjectId
     */
    @Column(name="project_id", length = 6, nullable = false)
    private long ProjectId;

    /**
     * ProjectName
     */
    @Column(name = "project_name", length = 64)
    private String ProjectName;

    /**
     * TrackerId
     */
    @Column(name="tracker_id", length = 6, nullable = false)
    private int TrackerId;

    /**
     * TrackerName
     */
    @Column(name = "tracker_name", length = 16)
    private String TrackerName;

    /**
     * StatusId
     */
    @Column(name="status_id", length = 6, nullable = false)
    private int StatusId;

    /**
     * StatusName
     */
    @Column(name = "status_name", length = 32)
    private String StatusName;

    /**
     * FixedVersionId
     */
    @Column(name="fixed_version_id", length = 6, nullable = false)
    private int FixedVersionId;

    /**
     * FixedVersionName
     */
    @Column(name = "fixed_version_name", length = 16)
    private String FixedVersionName;

    /**
     * Subject
     */
    @Column(name = "subject", length = 256)
    private String Subject;

    /**
     * StartDate
     */
    @Column(name = "start_date", length = 10, nullable = false)
    private String StartDate;

    /**
     * DueDate
     */
    @Column(name = "due_date", length = 10, nullable = false)
    private String DueDate;

    /**
     * Target
     */
    @Column(name = "target")
    @Enumerated(EnumType.STRING)
    private Target target;

    public Issue(long ParentId, String ProjectName, int TrackerId, String TrackerName, int StatusId, String StatusName, int FixedVersionId, String FixedVersionName, String Subject, String StartDate, String DueDate, Target target) {
        this.ParentId         = ParentId;
        this.ProjectName      = ProjectName;
        this.TrackerId        = TrackerId;
        this.TrackerName      = TrackerName;
        this.FixedVersionId   = FixedVersionId;
        this.FixedVersionName = FixedVersionName;
        this.Subject          = Subject;
        this.StartDate        = StartDate;
        this.DueDate          = DueDate;
        this.target           = target;
    }

    public Issue() {}

    /**
     * @return Id
     */
    public long getId() {
        return Id;
    }
    /**
     * @param Id
     */
    public void setId(long Id) {
        this.Id = Id;
    }

    /**
     * @return ParentId
     */
    public long getParentId() {
        return ParentId;
    }
    /**
     * @param ParentId
     */
    public void setParentId(long ParentId) {
        this.ParentId = ParentId;
    }

    /**
     * @return ProjectId
     */
    public long getProjectId() {
        return ProjectId;
    }
    /**
     * @param ProjectId
     */
    public void setProjectId(long ProjectId) {
        this.ProjectId = ProjectId;
    }

    /**
     * @return ProjectName
     */
    public String getProjectName() {
        return ProjectName;
    }
    /**
     * @param ProjectName
     */
    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    /**
     * @return TrackerId
     */
    public int getTrackerId() {
        return TrackerId;
    }
    /**
     * @param TrackerId
     */
    public void setTrackerId(int TrackerId) {
        this.TrackerId = TrackerId;
    }

    /**
     * @return TrackerName
     */
    public String getTrackerName() {
        return TrackerName;
    }
    /**
     * @param TrackerName
     */
    public void setTrackerName(String TrackerName) {
        this.TrackerName = TrackerName;
    }

    /**
     * @return StatusId
     */
    public int getStatusId() {
        return StatusId;
    }
    /**
     * @param StatusId
     */
    public void setStatusId(int StatusId) {
        this.StatusId = StatusId;
    }

    /**
     * @return StatusName
     */
    public String getStatusName() {
        return StatusName;
    }
    /**
     * @param StatusName
     */
    public void setStatusName(String StatusName) {
        this.StatusName = StatusName;
    }

    /**
     * @return FixedVersionId
     */
    public int getFixedVersionId() {
        return FixedVersionId;
    }
    /**
     * @param FixedVersionId
     */
    public void setFixedVersionId(int FixedVersionId) {
        this.StatusId = FixedVersionId;
    }

    /**
     * @return FixedVersionName
     */
    public String getFixedVersionName() {
        return FixedVersionName;
    }
    /**
     * @param FixedVersionName
     */
    public void setFixedVersionName(String FixedVersionName) {
        this.FixedVersionName = FixedVersionName;
    }

    /**
     * @return Subject
     */
    public String getSubject() {
        return Subject;
    }
    /**
     * @param Subject
     */
    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    /**
     * @return StartDate
     */
    public String getStartDate() {
        return StartDate;
    }
    /**
     * @param StartDate
     */
    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    /**
     * @return DueDate
     */
    public String getDueDate() {
        return DueDate;
    }
    /**
     * @param DueDate
     */
    public void setDueDate(String DueDate) {
        this.DueDate = DueDate;
    }

    /**
     * @return target
     */
    public Target getTarget() {
        return target;
    }
    /**
     * @param target
     */
    public void setTarget(Target target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "Id=" + Id +
                ", ParentId=" + ParentId +
                ", ProjectId=" + ProjectId +
                ", ProjectName='" + ProjectName + '\'' +
                ", TrackerId=" + TrackerId +
                ", TrackerName='" + TrackerName + '\'' +
                ", StatusId=" + StatusId +
                ", StatusName='" + StatusName + '\'' +
                ", FixedVersionId=" + FixedVersionId +
                ", FixedVersionName='" + FixedVersionName + '\'' +
                ", Subject='" + Subject + '\'' +
                ", StartDate='" + StartDate + '\'' +
                ", DueDate='" + DueDate + '\'' +
                ", Target='" + target + '\'' +
                '}';
    }
}
