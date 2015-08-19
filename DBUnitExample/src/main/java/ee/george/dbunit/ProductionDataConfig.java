package ee.george.dbunit;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@PropertySource("classpath:/application-prod.properties")
@Profile("prod")
@ComponentScan(basePackageClasses = ApplicationConfig.class)
public class ProductionDataConfig implements DataConfig {

	@Autowired
	private Environment env;

	@Override
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}

	@Override
	@Bean
	@Autowired
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		jpaProperties.put("hibernate.show_sql", false);
		jpaProperties.put("hibernate.format_sql", false);

		bean.setPersistenceProviderClass(HibernatePersistence.class);
		bean.setJpaProperties(jpaProperties);
		bean.setDataSource(dataSource);
		bean.setPackagesToScan("ee.george.dbunit.model");
		return bean;
	}

	@Override
	@Bean
	@Autowired
	public JpaTransactionManager transactionManager(EntityManagerFactory emf, DataSource dataSource) {
		JpaTransactionManager bean = new JpaTransactionManager();
		bean.setDataSource(dataSource);
		bean.setEntityManagerFactory(emf);
		return bean;
	}

}
