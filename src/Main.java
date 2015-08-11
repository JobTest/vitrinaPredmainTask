import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Lazarchuk A.K.
 * @version 1.0
 * Created by alexandr on 11.08.15.
 ** {@link http://www.java2s.com/Tutorial/Java/0355__JPA/OneToManyListCollection.htm}
 * https://en.wikibooks.org/wiki/Java_Persistence/ElementCollection
 * http://www.avaje.org/manydatatypes.html
 * http://www.thejavageek.com/2014/09/24/jpa-ordercolumn-example/
 */

public class Main {

    public static void main(String[] a) throws Exception {
        em.getTransaction().begin();


        Student student = new Student();
        student.setName("Joe");
        em.persist(student);

        Department dept = new Department();
        dept.addStudent(student);
        em.persist(dept);

        em.flush();

        Query query = em.createQuery("SELECT e FROM Student e");
        List<Student> list = (List<Student>) query.getResultList();
        System.out.println(list);

        query = em.createQuery("SELECT d FROM Department d");
        List<Department> dList = (List<Department>) query.getResultList();
        System.out.println(dList);


        em.getTransaction().commit();
        em.close();
        emf.close();

        Helper.checkData();
    }

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAService");
    static EntityManager em = emf.createEntityManager();
}
