/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.puppyvm.Dao;

import com.sg.puppyvm.Dto.Puppy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jamesbond
 */
public class PuppyVMDaoStubImpl implements PuppyVMDao {

    Puppy onlyPuppy;
    List<Puppy> puppyList = new ArrayList<>();

    public PuppyVMDaoStubImpl() {
        onlyPuppy = new Puppy();
        onlyPuppy.setName("Skippy");
        onlyPuppy.setPrice(new BigDecimal("5"));
        onlyPuppy.setStock(1);

        puppyList.add(onlyPuppy);
    }

    @Override
    public Puppy addPuppy(String name, Puppy newPuppy){

        if (name.equals(onlyPuppy.getName())){
            return onlyPuppy;
        } else {
            return null;
        }
    }

    @Override
    public List<Puppy> getAllPuppies() {
        return puppyList;
    }

    @Override
    public Puppy getPuppy(String name) {

        if (name.equals(onlyPuppy.getName())) {
            return onlyPuppy;
        } else {
            return null;
        }
    }

    @Override
    public Puppy removePuppy(String name) {

        if (name.equals(onlyPuppy.getName())) {
            return onlyPuppy;
        } else {
            return null;
        }
    }

    @Override
    public void updatePuppy(Puppy changedPuppy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writePuppy() throws PuppyVMDaoPersistanceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadPuppy() throws PuppyVMDaoPersistanceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
