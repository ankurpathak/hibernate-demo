package com.github.ankurpathak.hibernatedemo;

import com.github.ankurpathak.springframework.orm.jpa.ExtendedLocalContainerEntityManagerFactoryBean;
import com.github.ankurpathak.springframework.orm.jpa.hibernate.ExtendedHibernateJpaVendorAdaptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.util.Properties;

@Configuration
public class JpaConfig {

    private final  JdbcTemplate jdbcTemplate;

    public JpaConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JdbcTemplate jdbcTemplate){
        LocalContainerEntityManagerFactoryBean emf = new ExtendedLocalContainerEntityManagerFactoryBean(jdbcTemplate.getExceptionTranslator());
        emf.setDataSource(jdbcTemplate.getDataSource());
        emf.setPackagesToScan("com.github.ankurpathak.hibernatedemo");
        emf.setJpaVendorAdapter(new ExtendedHibernateJpaVendorAdaptor(jdbcTemplate.getExceptionTranslator()));
        Properties jpaProperties =  new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        jpaProperties.setProperty("hibernate.show_sql", String.valueOf(Boolean.TRUE));
        jpaProperties.setProperty("hibernate.format_sql", String.valueOf(Boolean.TRUE));
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "validate");
        emf.setJpaProperties(jpaProperties);
        return emf;
    }
}
