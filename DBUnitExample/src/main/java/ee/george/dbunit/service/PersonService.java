package ee.george.dbunit.service;

import ee.george.dbunit.dao.IAddressDAO;
import ee.george.dbunit.dao.IPersonDAO;
import ee.george.dbunit.model.Address;
import ee.george.dbunit.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService implements IPersonService {

	@Autowired
    IPersonDAO personDAO;

	@Autowired
    IAddressDAO addressDAO;

	@Override
	public void addPersonAddress(int personId, Address address) {
		Person person = personDAO.findById(personId);
		if (person != null) {
			address.setPerson(personId);
			addressDAO.save(address);
		}
	}

	@Override
	public List<Person> getAllPersons() {
		return personDAO.findAll();
	}

	@Override
	public Person getPerson(int personId) {
		return personDAO.findById(personId);
	}

	@Override
	public void save(Person person) {
		personDAO.save(person);
	}

}
