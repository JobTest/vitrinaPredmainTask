package com.domain;

/**
 * Created by Саша on 14.08.2015.
 */
import java.util.HashSet;
import java.util.Set;

public class Employee {

    private Long employeeId;
    private String firstname;
    private String lastname;
    private Set<Meeting> meetings = new HashSet<>();

    public Employee() {}
    public Employee(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname  = lastname;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Set<Meeting> getMeetings() {
        return meetings;
    }
    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", meetings=" + meetings +
                '}';
    }
}
