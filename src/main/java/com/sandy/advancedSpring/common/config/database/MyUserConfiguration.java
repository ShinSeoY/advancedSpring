package com.sandy.advancedSpring.common.config.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.sandy.advancedSpring.repository.member",
        entityManagerFactoryRef = "myUserEntityManager",
        transactionManagerRef = "myUserTransactionManager"
)
public class MyUserConfiguration {

    @Bean(name = "myUserEntityManager")
    public LocalContainerEntityManagerFactoryBean myUserEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(myUserDataSource());
        em.setPackagesToScan("com.sandy.advancedSpring.domain.member");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.member")
    public DataSource myUserDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "myUserTransactionManager")
    public PlatformTransactionManager myUserTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(myUserEntityManager().getObject());
        return transactionManager;
    }
}