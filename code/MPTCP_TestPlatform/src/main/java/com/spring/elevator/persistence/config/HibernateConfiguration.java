package com.spring.elevator.persistence.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by AlexJIANG on 12/25/15.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.spring.elevator.persistence.config"})
@PropertySource({"/WEB-INF/application.properties"})
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(environment.getProperty("hibernate.packageToScan"));
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.c3p0.min_size",environment.getRequiredProperty("hibernate.c3p0.min_size"));
        properties.put("hibernate.c3p0.max_size",environment.getProperty("hibernate.c3p0.max_size"));
        properties.put("hibernate.c3p0.timeout",environment.getProperty("hibernate.c3p0.timeout"));
        properties.put("hibernate.hbm2ddl.auto",environment.getProperty("hibernate.hbm2ddl.auto"));
       // properties.put("spring.jpa.show-sql",environment.getProperty("spring.jpa.show-sql"));
        properties.put("show_sql",environment.getProperty("show_sql"));

        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }


}
