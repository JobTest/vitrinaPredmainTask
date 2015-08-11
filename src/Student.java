
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Student {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private int id;
    private String name;
    @ManyToOne private Department department;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public Department getDepartment() {
        return department;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override public String toString() {
        return "Student id: " + getId() + " name: " + getName() + " with " + getDepartment();
    }
}
