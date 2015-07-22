
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name="findSalaryForNameAndDepartment",
        query="SELECT e.salary " +
              "FROM Professor e " +
              "WHERE e.department.name = :deptName AND " +
              "      e.name = :empName")
              
              

public class Professor {
    @Id
    private int id;
    private String name;
    private long salary;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @ManyToOne
    private Professor manager;
    
    @OneToMany(mappedBy="manager")
    private Collection<Professor> directs;

    @ManyToOne
    private Department department;
    
    @ManyToMany 
    private Collection<Project> projects;

    public Professor() {
        projects = new ArrayList<Project>();
        directs = new ArrayList<Professor>();
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public long getSalary() {
        return salary;
    }

    public Date getStartDate() {
        return startDate;
    }
    
    public Department getDepartment() {
        return department;
    }
    
    public Collection<Professor> getDirects() {
        return directs;
    }
    
    public Professor getManager() {
        return manager;
    }

    public Collection<Project> getProjects() {
        return projects;
    }
    
    public String toString() {
        return "Professor " + getId() + 
               ": name: " + getName() +
               ", salary: " + getSalary() +
               ", dept: " + ((getDepartment() == null) ? null : getDepartment().getName());
    }
}
