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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jamesbond
 */
public class PuppyVMDaoTest {

    PuppyVMDao dao = new PuppyVMDaoFileImpl();

    public PuppyVMDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
//For known good state
        List<Puppy> pupList = dao.getAllPuppies();

        for (Puppy pup : pupList) {
            dao.removePuppy(pup.getName());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addPuppy method, of class PuppyVMDao.
     */
    @Test
    public void testAddPuppy() throws Exception {

        Puppy pup = new Puppy();
        pup.setName("Horse Pup");
        pup.setPrice(new BigDecimal("2"));
        pup.setStock(1);

        dao.addPuppy(pup.getName(), pup);

        Puppy fromDao = dao.getPuppy(pup.getName());

        assertEquals(pup, fromDao);

    }

    /**
     * Test of getAllPuppies method, of class PuppyVMDao.
     */
    @Test
    public void testGetAllPuppiesNotEqual() throws Exception {

        Puppy pup1 = new Puppy();
        pup1.setName("Three Legged Dog");
        pup1.setPrice(new BigDecimal("4"));
        pup1.setStock(1);

        dao.addPuppy(pup1.getName(), pup1);

        Puppy pup2 = new Puppy();
        pup2.setName("Steve the Dog");
        pup2.setPrice(new BigDecimal("11"));
        pup2.setStock(2);

        dao.addPuppy(pup2.getName(), pup2);

        assertNotEquals(1, dao.getAllPuppies().size());

    }
    
    @Test
    public void testGetAllPuppiesAreEqual() throws Exception {

        Puppy pup1 = new Puppy();
        pup1.setName("Three Legged Dog");
        pup1.setPrice(new BigDecimal("4"));
        pup1.setStock(1);

        dao.addPuppy(pup1.getName(), pup1);

        Puppy pup2 = new Puppy();
        pup2.setName("Steve the Dog");
        pup2.setPrice(new BigDecimal("11"));
        pup2.setStock(2);

        dao.addPuppy(pup2.getName(), pup2);

        assertEquals(2, dao.getAllPuppies().size());

    }

    /**
     * Test of getPuppy method, of class PuppyVMDao.
     */
    @Test
    public void testCantGetPuppy() {

        Puppy spike = dao.getPuppy("Spike");
        
        Assert.assertNull(spike);
        
    }
    
    @Test
    public void testGetPuppy() {

        Puppy pup = new Puppy();
        pup.setName("Horse Pup");
        pup.setPrice(new BigDecimal("2"));
        pup.setStock(1);

        dao.addPuppy(pup.getName(), pup);

        Puppy fromDao = dao.getPuppy("Horse Pup");
        
        assertEquals(fromDao, pup);

    }
    
        @Test
    public void testNotGetPuppy() {

        Puppy pup = new Puppy();
        pup.setName("Horse");
        pup.setPrice(new BigDecimal("2"));
        pup.setStock(1);

        dao.addPuppy(pup.getName(), pup);

        Puppy fromDao = dao.getPuppy("Horse Pup");
        
        assertNotEquals(fromDao, pup);

    }

//    public Puppy getPuppy(String name) {
//        Puppy pup;
//
//        pup = labeledBoxesOfPuppies.get(name);
//
//        return pup;
//    }    
    /**
     * Test of removePuppy method, of class PuppyVMDao.
     */
    @Test
    public void testRemovePuppy() throws Exception {

        Puppy pup1 = new Puppy();
        pup1.setName("Three Legged Dog");
        pup1.setPrice(new BigDecimal("4"));
        pup1.setStock(1);

        dao.addPuppy(pup1.getName(), pup1);

        Puppy pup2 = new Puppy();
        pup2.setName("Steve the Dog");
        pup2.setPrice(new BigDecimal("11"));
        pup2.setStock(2);

        dao.addPuppy(pup2.getName(), pup2);

        dao.removePuppy(pup1.getName());
        assertEquals(1, dao.getAllPuppies().size());
        assertNull(dao.getPuppy(pup1.getName()));

        dao.removePuppy(pup2.getName());
        assertEquals(0, dao.getAllPuppies().size());
        assertNull(dao.getPuppy(pup2.getName()));

    }

    /**
     * Test of updatePuppy method, of class PuppyVMDao.
     */
    @Test
    public void testUpdatePuppy() throws Exception {
        
        
        
    }

    /**
     * Test of writePuppy method, of class PuppyVMDao.
     */
    @Test
    public void testWritePuppy() throws Exception {
    }

    /**
     * Test of loadPuppy method, of class PuppyVMDao.
     */
    @Test
    public void testLoadPuppy() throws Exception {
    }

}
