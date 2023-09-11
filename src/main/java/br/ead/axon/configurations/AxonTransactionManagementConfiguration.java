package br.ead.axon.configurations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

//@Aspect
//@Component
public class AxonTransactionManagementConfiguration {

//    @Autowired
//    private PlatformTransactionManager txManager;

//    @Around("@annotation(org.axonframework.commandhandling.CommandHandler)")
//    public Object aroundCommandHandler(ProceedingJoinPoint pjp) throws Throwable {
//        TransactionStatus txStatus = txManager.getTransaction(new DefaultTransactionDefinition());
//        try {
//            Object result = pjp.proceed();
//            txManager.commit(txStatus);
//            return result;
//        } catch (Exception e) {
//            txManager.rollback(txStatus);
//            throw e;
//        }
//    }
}
