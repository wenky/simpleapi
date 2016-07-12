package com.wenky.app.framework.config;

import java.util.Properties;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath://properties/database.properties" })
public class HibernateConfig {
	@Inject
	Environment environment;

	

    private  DriverManagerDataSource dataSource() throws NamingException {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(environment.getProperty("database.driver"));
      dataSource.setUrl(environment.getProperty("database.url"));
      dataSource.setUsername(environment.getProperty("database.username"));
      dataSource.setPassword(environment.getProperty("database.password"));
      return dataSource;
  }
    
	public @Bean LocalSessionFactoryBean sessionFactory() throws NamingException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		Properties prop = new Properties();
		// Be extremely careful before changing this value to Update or Create
		prop.put("hibernate.hbm2ddl.auto", "update");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.dialect", environment.getProperty("database.dialect"));
		 sessionFactory.setDataSource(this.dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.wenky.app.business.entity" });
		sessionFactory.setHibernateProperties(prop);
		return sessionFactory;
	}
	
	/**
     * @return
     * @throws javax.naming.NamingException
     */
    @Bean
    public HibernateTransactionManager transactionManager() throws NamingException {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setDataSource(this.dataSource());
        txManager.setSessionFactory(this.sessionFactory().getObject());
        return txManager;
    }
}