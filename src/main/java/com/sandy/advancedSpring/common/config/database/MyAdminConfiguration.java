package com.sandy.advancedSpring.common.config.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages = "com.sandy.advancedSpring.repository.admin",
        entityManagerFactoryRef = "myAdminEntityManager",
        transactionManagerRef = "myAdminTransactionManager"
)
public class MyAdminConfiguration {

    @Bean(name = "myAdminEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean myAdminEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(myAdminDataSource());
        em.setPackagesToScan("com.sandy.advancedSpring.domain.admin");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.admin")
    public DataSource myAdminDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "myAdminTransactionManager")
    public PlatformTransactionManager myAdminTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(myAdminEntityManager().getObject());
        return transactionManager;
    }
}