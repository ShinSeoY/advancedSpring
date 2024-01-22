//package com.sandy.advancedSpring.common.config.jta;
//
//import com.atomikos.jdbc.AtomikosDataSourceBean;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableConfigurationProperties(DatabaseProperties.class)
//@EnableJpaRepositories(
//        basePackages = {"com.sandy.advancedSpring.repository.admin"},
//        entityManagerFactoryRef = PersistenceDBTwoConfiguration.ENTITY_MANAGER_BEAN_NAME,
//        transactionManagerRef = XaDataSourceConfig.TRANSACTION_MANAGER_BEAN_NAME)
//public class PersistenceDBTwoConfiguration {
//    public static final String ENTITY_MANAGER_BEAN_NAME = "twoDBEntityManager";
//    private static final String DATASOURCE_BEAN_NAME = "twoDataSource";
//    private static final String DATASOURCE_PROPERTIES_PREFIX = "spring.datasource.two";
//    private static final String HIBERNATE_PROPERTIES = "twoHibernateProperties";
//
//    @Bean(name = ENTITY_MANAGER_BEAN_NAME)
//    public LocalContainerEntityManagerFactoryBean entityManager(EntityManagerFactoryBuilder builder, @Qualifier(DATASOURCE_BEAN_NAME) DataSource dataSource
//    ) { //@Qualifier(HIBERNATE_PROPERTIES) DatabaseProperties.Hibernate hibernateProperties
//
//        return builder.dataSource(dataSource).packages("com.sandy.advancedSpring.domain.admin").persistenceUnit(ENTITY_MANAGER_BEAN_NAME)
////                .properties(DatabaseProperties.Hibernate.propertiesToMap(hibernateProperties))
//                .build();
//    }
//
////    @Bean(name = HIBERNATE_PROPERTIES)
////    @ConfigurationProperties(DATASOURCE_PROPERTIES_PREFIX + ".hibernate")
////    public DatabaseProperties.Hibernate hibernateProperties() {
////        return new DatabaseProperties.Hibernate();
////    }
//
//    @Bean(name = DATASOURCE_BEAN_NAME)
//    @ConfigurationProperties(prefix = DATASOURCE_PROPERTIES_PREFIX)
//    public DataSource dataSource() {
//        return new AtomikosDataSourceBean();
//    }
//}