/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.puppyvm.Dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author jamesbond
 */
public class PuppyVMAuditDaoFileImpl implements PuppyVMAuditDao {

    public static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws PuppyVMDaoPersistanceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException ex) {

            throw new PuppyVMDaoPersistanceException("Could not persist requested information.", ex);
        }

        LocalDateTime timeStamp = LocalDateTime.now();
        out.println(timeStamp.toString() + ": " + entry);
        out.flush();

    }

}
