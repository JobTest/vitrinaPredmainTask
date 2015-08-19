package ee.george.dbunit;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:/application-test.properties")
@Profile("test")
@EnableTransactionManagement
public class TestDataConfig implements DataConfig {

	@Autowired
	private Environment env;

	@Override
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().build();
	}

	@Override
	@Bean
	@Autowired
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		jpaProperties.put("hibernate.show_sql", false); // avoid double logging
		jpaProperties.put("hibernate.format_sql", true);
		jpaProperties.put("hibernate.hbm2ddl.auto", "create"); // auto initialization schema in database, based on JPA Entity classes

		bean.setPersistenceProviderClass(HibernatePersistence.class);
		bean.setDataSource(dataSource);
		bean.setJpaProperties(jpaProperties);
		bean.setPackagesToScan("ee.george.dbunit.model");

		return bean;
	}

	@Override
	@Bean
	@Autowired
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory, DataSource dataSource) {
		JpaTransactionManager bean = new JpaTransactionManager(entityManagerFactory);
		bean.setJpaDialect(new HibernateJpaDialect());
		bean.setDataSource(dataSource);
		return bean;
	}

}
