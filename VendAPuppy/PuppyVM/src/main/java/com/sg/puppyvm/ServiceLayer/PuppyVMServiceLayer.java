/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.puppyvm.ServiceLayer;

import com.sg.puppyvm.Dao.PuppyVMDaoPersistanceException;
import com.sg.puppyvm.Dto.Change;
import com.sg.puppyvm.Dto.Puppy;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jamesbond
 */
public interface PuppyVMServiceLayer {

    List<Puppy> getAllPuppies()
            throws PuppyVMDaoPersistanceException;

    Puppy getPuppy(String name)
            throws PuppyVMDaoPersistanceException;

    void writePuppy()
            throws PuppyVMDaoPersistanceException;

    void loadPuppy()
            throws PuppyVMDaoPersistanceException;

//----------------------passThroughMethods---------
//    boolean enoughPuppyMoney(String name);
//            throws InsufficientFundsException;

//    boolean enoughPuppyStock(String name);
//            throws NoItemInventoryException;

    BigDecimal storeMoney(BigDecimal moreMoney);

    Change returnMoney(BigDecimal piggyBank);

    public BigDecimal changeDue(BigDecimal moreMoney);

    public void sellPuppy(String name) 
            throws InsufficientFundsException,
            NoItemInventoryException;
//    throws both exceptions above

}
