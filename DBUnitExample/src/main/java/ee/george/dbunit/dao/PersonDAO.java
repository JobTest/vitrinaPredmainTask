package ee.george.dbunit.dao;

import org.springframework.stereotype.Repository;

import ee.george.dbunit.model.Person;

@Repository
public class PersonDAO extends JpaDAO<Person> implements IPersonDAO {

	public PersonDAO() {
		setClazz(Person.class);
	}

}
