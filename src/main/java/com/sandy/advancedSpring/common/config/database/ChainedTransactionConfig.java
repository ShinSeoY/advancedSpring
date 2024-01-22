package com.sandy.advancedSpring.common.config.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTransactionConfig {

    @Autowired
    @Bean
    @Primary    // 이걸 걸어줘야 밑에 두개 디비 모두 트랜잭션이 걸림 각 디비의 transactionManager에는 primary 속성 없어야함
    public PlatformTransactionManager chainedTransactionManager(
            @Qualifier("myAdminTransactionManager") PlatformTransactionManager myAdminTransactionManager,
            @Qualifier("myUserTransactionManager") PlatformTransactionManager myUserTransactionManager

    ) {
        return new ChainedTransactionManager(myUserTransactionManager, myAdminTransactionManager);
    }

}
