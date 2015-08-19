package ee.george.dbunit.tests;

import ee.george.dbunit.model.Person;
import ee.george.dbunit.service.IPersonService;
import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class SomeDBUnitTest extends AbstractDBUnitTest {

	@Autowired
    IPersonService personService;

	@After
	public void after() throws DatabaseUnitException, SQLException, Exception {
		DatabaseOperation.DELETE_ALL.execute(getConnection(), getDataSet());
	}

	@Before
	public void before() throws DatabaseUnitException, SQLException, Exception {
		DatabaseOperation.DELETE_ALL.execute(getConnection(), getDataSet());
	}

	@Test
	public void someTest() throws DataSetException, SQLException, Exception {
		Person person = new Person();
		person.setFirstname("Georgii");
		personService.save(person);

		IDataSet actualDataSet = getConnection().createDataSet();

// 		Export dataset into the file
//		FlatXmlWriter writer = new FlatXmlWriter(new FileOutputStream("actualDataSet.xml"));
//		writer.setIncludeEmptyTable(false);
//		writer.write(actualDataSet);

//		FlatXmlDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream(
//				"/expectedDataSet.xml"));

		XmlDataSet expectedDataSet = new XmlDataSet(getClass().getResourceAsStream("/expectedDataSet.xml"));

		Assertion.assertEquals(expectedDataSet, actualDataSet);
	}
}
