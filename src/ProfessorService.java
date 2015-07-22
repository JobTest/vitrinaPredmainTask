import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class ProfessorService {
  protected EntityManager em;

  public ProfessorService(EntityManager em) {
    this.em = em;
  }


  public long findSalaryForNameAndDepartment(String deptName, String empName) {
    try {
      return (Long) em.createNamedQuery("findSalaryForNameAndDepartment").setParameter("deptName", deptName).setParameter("empName", empName).getSingleResult();
    } catch (NoResultException e) {
      return 0;
    }
  }

}
