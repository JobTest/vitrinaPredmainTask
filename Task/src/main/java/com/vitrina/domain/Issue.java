package com.vitrina.domain;

/**
 * Created by alexandr on 24.07.15.
 */
public interface Issue {
    int getId();
    int getParentId();
    int getProjectId();
    String getProjectName();
    int getTrackerId();
    String getTrackerName();
    int getStatusId();
    String getStatusName();
    int getFixedVersionId();
    String getFixedVersionName();
    String getSubject();
    String getStartDate();
    String getDueDate();
    void setId(int Id);
    void setParentId(int ParentId);
    void setProjectId(int ProjectId);
    void setProjectName(String ProjectName);
    void setTrackerId(int TrackerId);
    void setTrackerName(String TrackerName);
    void setStatusId(int StatusId);
    void setStatusName(String StatusName);
    void setFixedVersionId(int FixedVersionId);
    void setFixedVersionName(String FixedVersionName);
    void setSubject(String Subject);
    void setStartDate(String StartDate);
    void setDueDate(String DueDate);
}
