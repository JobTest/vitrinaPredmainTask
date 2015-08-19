package ee.george.dbunit.tests;

import org.dbunit.JdbcBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ee.george.dbunit.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@ActiveProfiles("test")
abstract public class AbstractDBUnitTest extends JdbcBasedDBTestCase {

	static Logger LOG;

	public AbstractDBUnitTest() {
		LOG = LoggerFactory.getLogger(this.getClass());
	}

	@Override
	protected String getConnectionUrl() {
		return "jdbc:hsqldb:mem:testdb";
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream("/TestDataSet.xml"));
	}

	@Override
	protected String getDriverClass() {
		return "org.hsqldb.jdbcDriver";
	}

	@Override
	protected String getPassword() {
		return "";
	}

	@Override
	public String getUsername() {
		return "sa";
	}
}
