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
            @Qualifier("myUserTransactionManager") PlatformTransactionManager myUserTransactionManager,
            @Qualifier("myAdminTransactionManager") PlatformTransactionManager myAdminTransactionManager
    ) {
        return new ChainedTransactionManager(myUserTransactionManager, myAdminTransactionManager);  // 여기 순서가 중요함 오류가 많이 날것 같은 manager를 생성자 파라미터 뒤에 써주기 여기서는 myAdminTransactionManager가 오류날 가능성이 높다고 가정
    }

}
