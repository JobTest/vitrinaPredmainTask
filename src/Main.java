import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
  public static void main(String[] a) throws Exception {
    JPAUtil util = new JPAUtil();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProfessorService");
    EntityManager em = emf.createEntityManager();
    ProfessorService service = new ProfessorService(em);

    em.getTransaction().begin();

    long l = service.findSalaryForNameAndDepartment("deptName", "empName");

    
    
    util.checkData("select * from Professor");
    util.checkData("select * from Department");

    
    em.getTransaction().commit();
    em.close();
    emf.close();
  }
}