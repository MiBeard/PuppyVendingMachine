/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.puppyvm.Advice;

import com.sg.puppyvm.Dao.PuppyVMAuditDao;
import com.sg.puppyvm.Dao.PuppyVMDaoPersistanceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author jamesbond
 */
public class LoggingAdvice {

    private PuppyVMAuditDao auditDao;

    public LoggingAdvice(PuppyVMAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp, Throwable e) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (PuppyVMDaoPersistanceException ex) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

}
