/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.puppyvm.ServiceLayer;

/**
 *
 * @author jamesbond
 */
public class PuppyVMDuplicateIdException extends Exception {

    public PuppyVMDuplicateIdException(String message) {
        super(message);
    }

    public PuppyVMDuplicateIdException(String message,
            Throwable cause) {
        super(message, cause);
    }

}
