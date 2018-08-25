/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.puppyvm.Dao;

/**
 *
 * @author jamesbond
 */
public class PuppyVMDaoPersistanceException extends Exception {

    public PuppyVMDaoPersistanceException(String message) {
        super(message);
    }

    public PuppyVMDaoPersistanceException(String message, Throwable cause) {
        super(message, cause);
    }

}
