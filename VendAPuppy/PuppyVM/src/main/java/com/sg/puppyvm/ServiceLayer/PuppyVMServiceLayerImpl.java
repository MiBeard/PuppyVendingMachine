/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.puppyvm.ServiceLayer;

import com.sg.puppyvm.Dao.PuppyVMDao;
import com.sg.puppyvm.Dao.PuppyVMDaoPersistanceException;
import com.sg.puppyvm.Dto.Change;
import com.sg.puppyvm.Dto.Puppy;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jamesbond
 */
public class PuppyVMServiceLayerImpl
        implements PuppyVMServiceLayer {

    private PuppyVMDao dao;
    private BigDecimal piggyBank;

    public PuppyVMServiceLayerImpl(PuppyVMDao dao) {
        this.dao = dao;
        this.piggyBank = new BigDecimal("0.00");
    }

    @Override
    public List<Puppy> getAllPuppies()
            throws PuppyVMDaoPersistanceException {
        return dao.getAllPuppies();
    }

    @Override
    public Puppy getPuppy(String name) {
        return dao.getPuppy(name);
    }

    @Override
    public void writePuppy()
            throws PuppyVMDaoPersistanceException {
        dao.writePuppy();
    }

    @Override
    public void loadPuppy()
            throws PuppyVMDaoPersistanceException {
        dao.loadPuppy();
    }

//^^^-----------PassThroughMethods----------^^^
    @Override
    public void sellPuppy(String name)
            throws InsufficientFundsException,
            NoItemInventoryException {

        if (!this.enoughPuppyMoney(name)) {
            throw new InsufficientFundsException("Sorry, you need more money homes");
        }

        if (!this.enoughPuppyStock(name)) {
            throw new NoItemInventoryException("Sorry, we got hungry");
        }

        Puppy thePup = dao.getPuppy(name);
        int stock = thePup.getStock();
        int updatedStock = stock - 1;
        thePup.setStock(updatedStock);
        dao.updatePuppy(thePup);

    }

    @Override
    public BigDecimal changeDue(BigDecimal changeDue) {
        
        this.piggyBank = changeDue.subtract(piggyBank);
        return piggyBank;
    }

    @Override
    public BigDecimal storeMoney(BigDecimal moreMoney) {

        this.piggyBank = moreMoney;
        return piggyBank;
    }

    private boolean enoughPuppyStock(String pupName) {

        Puppy pup = dao.getPuppy(pupName);

        if (pup.getStock() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Change returnMoney(BigDecimal piggyBank) {

        BigDecimal hundred = new BigDecimal(100);

        //multiply usermoney by 100
        BigDecimal pennies = piggyBank.multiply(hundred);
        //convert pennies to an integer

        int change = pennies.intValue();

        Change changeGivenToUser = new Change();
        //calculate the number of each coin to be returned

        int quarters = Math.floorDiv(change, 25);
        changeGivenToUser.setQuarters(quarters);
        int remainingChange = change - (quarters * 25);

        int dimes = Math.floorDiv(remainingChange, 10);
        changeGivenToUser.setDimes(dimes);
        remainingChange = remainingChange - (dimes * 10);

        int nickles = Math.floorDiv(remainingChange, 5);
        changeGivenToUser.setNickles(nickles);
        remainingChange = remainingChange - (nickles * 5);

        int penniesLeft = remainingChange;
        changeGivenToUser.setPennies(penniesLeft);

        piggyBank = new BigDecimal("0.00");

        return changeGivenToUser;

    }

    private boolean enoughPuppyMoney(String name) {

        Puppy pup = dao.getPuppy(name);
        BigDecimal pupPrice = pup.getPrice();
        int compareValue
                = this.piggyBank.compareTo(pupPrice);

        if (compareValue >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private void validatePuppyData(Puppy pup) throws
            DataValidationException {

        if (pup.getName() == null
                || pup.getName().trim().length() == 0
                || pup.getName() == null
                || pup.getPrice() == null
                || pup.getStock() <= 0) {

            throw new DataValidationException(
                    "ERROR: Enter the correct info, Jabronie.");
        }
    }
}
