package com.sandy.advancedSpring.common.config.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTransactionConfig {

    @Autowired
    @Bean
    public PlatformTransactionManager chainedTransactionManager(
            @Qualifier("myAdminTransactionManager") PlatformTransactionManager myAdminTransactionManager,
            @Qualifier("myUserTransactionManager") PlatformTransactionManager myUserTransactionManager
    ) {
        return new ChainedTransactionManager(myAdminTransactionManager, myUserTransactionManager);
    }

}
