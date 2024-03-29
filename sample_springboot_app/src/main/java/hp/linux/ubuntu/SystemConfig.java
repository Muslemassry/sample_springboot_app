package hp.linux.ubuntu;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import hp.linux.ubuntu.aspect.SystemAspect;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class SystemConfig {
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		// datasource should either be configured by a prperties file or a JNDI lookup
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test_db");
		dataSource.setUsername("root");
		dataSource.setPassword("password");
		return dataSource;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		
		// in order to make sure all validations are set correctly so to create the session factory
		factoryBean.afterPropertiesSet();
		return (SessionFactory) factoryBean.getObject();
	}
	
	@Autowired
	@Bean(name = "transactionManager") 
	HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
	
//	@Bean 
//	SystemAspect getSystemAspect(SessionFactory sessionFactory) {
//		return new SystemAspect();
//	}
}
