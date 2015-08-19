package ee.george.dbunit.service;

import ee.george.dbunit.model.Address;
import ee.george.dbunit.model.Person;

import java.util.List;

public interface IPersonService {

	public void addPersonAddress(int personId, Address address);

	public List<Person> getAllPersons();

	Person getPerson(int personId);

	public void save(Person person);
}
