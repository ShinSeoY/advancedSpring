package com.sandy.advancedSpring.common.config.jta;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Configuration
@EnableTransactionManagement
public class XaDataSourceConfig {
    public static final String TRANSACTION_MANAGER_BEAN_NAME = "jtaTransactionManager";

    @Bean
    public UserTransactionManager userTransactionManager() throws SystemException {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setTransactionTimeout(1000);
        userTransactionManager.setForceShutdown(true);

        return userTransactionManager;
    }

    @Bean
    public UserTransaction userTransaction() throws SystemException {
        var userTransaction = new UserTransactionImp();
        userTransaction.setTransactionTimeout(60000);

        return userTransaction;
    }

    @Primary
    @Bean(name = TRANSACTION_MANAGER_BEAN_NAME)
    public JtaTransactionManager jtaTransactionManager(UserTransactionManager userTransactionManager, UserTransaction userTransaction) {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(userTransactionManager);
        jtaTransactionManager.setUserTransaction(userTransaction);

        return jtaTransactionManager;
    }
}
