/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.puppyvm.Dao;

import com.sg.puppyvm.Dto.Puppy;
import java.util.List;

/**
 *
 * @author jamesbond
 */
public interface PuppyVMDao {

    Puppy addPuppy(String name, Puppy newPuppy);

    List<Puppy> getAllPuppies();

    Puppy getPuppy(String name);

    Puppy removePuppy(String name);
    
    void updatePuppy(Puppy changedPuppy);

    void writePuppy()
            throws PuppyVMDaoPersistanceException;

    void loadPuppy()
            throws PuppyVMDaoPersistanceException;
}
