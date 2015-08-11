
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Department {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private int id;
    private String name;
    @OneToMany(mappedBy="department") @OrderBy("name ASC") private List<Student> students;

    public Department() {
        students = new ArrayList<Student>();
    }


    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Collection<Student> getStudents() {
        return students;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String deptName) {
        this.name = deptName;
    }

    public void addStudent(Student student) {
        if (!getStudents().contains(student)) {
            getStudents().add(student);
            if (student.getDepartment() != null) {
                student.getDepartment().getStudents().remove(student);
            }
            student.setDepartment(this);
        }
    }

    @Override public String toString() {
        return "Department id: " + getId() + ", name: " + getName();
    }
}
