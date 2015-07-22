
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {
    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy="department")
    private Collection<Professor> employees;

    public Department() {
        employees = new ArrayList<Professor>();
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Collection<Professor> getProfessors() {
        return employees;
    }

    public String toString() {
        return "Department no: " + getId() + 
               ", name: " + getName();
    }
}
