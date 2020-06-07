package com.finder.ecoshop;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

	@Autowired
	DataSource datasource;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory  = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(datasource);
		sessionFactory.setPackagesToScan("com.finder.ecoshop.core.domain");
		
		Properties hibernateProperties  = new Properties();
		hibernateProperties.setProperty("hibernate.jdbc.fetch_size", "50");
		hibernateProperties.setProperty("hibernate.jdbc.batch_size", "10");

		sessionFactory.setHibernateProperties(hibernateProperties);
		
		return sessionFactory;
	}
	
}
