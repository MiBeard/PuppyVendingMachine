/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.puppyvm.ServiceLayer;

import com.sg.puppyvm.Dao.PuppyVMDao;
import com.sg.puppyvm.Dao.PuppyVMDaoPersistanceException;
import com.sg.puppyvm.Dao.PuppyVMDaoStubImpl;
import com.sg.puppyvm.Dto.Change;
import com.sg.puppyvm.Dto.Puppy;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jamesbond
 */
public class PuppyVMServiceLayerTest {

    private PuppyVMServiceLayer service;

    public PuppyVMServiceLayerTest() {
        PuppyVMDao dao = new PuppyVMDaoStubImpl();
        service = new PuppyVMServiceLayerImpl(dao);

//        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        service = ctx.getBean("serviceLayer", PuppyVMServiceLayer.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllPuppies method, of class PuppyVMServiceLayer.
     */
    @Test
    public void testGetAllPuppies() throws Exception {

        assertEquals(1, service.getAllPuppies().size());

    }

    /**
     * Test of getPuppy method, of class PuppyVMServiceLayer.
     */
    @Test
    public void testGetPuppy() throws Exception {

        Puppy pup = new Puppy();
        pup.getName();
        assertNotNull(pup);

        pup = service.getPuppy("Skippy");
        assertNotNull(pup);

    }

    @Test
    public void testCantGetPuppy() throws Exception {

        Puppy pup = new Puppy();
        pup.getName();
        assertNotNull(pup);

        pup = service.getPuppy("kippy");
        assertNull(pup);

    }

    @Test
    public void testEnoughPuppyMoney()
            throws InsufficientFundsException,
            NoItemInventoryException {

        boolean exceptionExecuted = false;

        try {
            service.sellPuppy("Skippy");
        } catch (InsufficientFundsException exception) {
            exceptionExecuted = true;
        }

        Assert.assertTrue("Exception worked!", exceptionExecuted);

    }

//    @Test
//    public void testInsufficientFundsException() throws Exception {
//
//    }
//    @Test
//    public void testNotEnoughPuppyMoney() throws Exception {
//        
//        boolean enufMuney = false;
//        
//        service.sellPuppy("Skippy");
//        
//        Assert.assertFalse("Tried to buy Skippy, w/ no money", enufMuney);
//        
//    }
//    @Test
//    public void testEnoughPuppyMoneyWhenPutInMoney() throws Exception {
//
//        BigDecimal money = new BigDecimal("5");
//
//       BigDecimal monies = service.storeMoney(money);
//
////        service.enoughPuppyMoney(money.toString());
//        boolean enufMuney = service.enoughPuppyMoney(monies.toString());
//
//        assertTrue(enufMuney);
//
//    }
    @Test
    public void testAnotherEnoughPuppyStock()
            throws NoItemInventoryException,
            PuppyVMDaoPersistanceException {

        Puppy pup = service.getPuppy("Skippy");

        assertEquals(1, pup.getStock());

    }

//    @Test
//    public void testNotEnoughPuppyStock()
//            throws NoItemInventoryException,
//            InsufficientFundsException,
//            PuppyVMDaoPersistanceException {
//
//        boolean NoItemInventoryException = false;
//
//        try {
//            service.sellPuppy("Skippy");
//
//            Puppy pup = service.getPuppy("Skippy");
//        } catch (NoItemInventoryException e) {
//            NoItemInventoryException = true;
//        }
//
//        assertFalse("Exception Occurred", NoItemInventoryException);
//
//    }
    /**
     * Test of enoughPuppyStock method, of class PuppyVMServiceLayer.
     */
    @Test
    public void testEnoughPuppyStock() throws Exception {

        Puppy pup = service.getPuppy("Skippy");

        assertEquals(1, pup.getStock());

        int stock = pup.getStock();

        int stockDecrement = stock - 1;

        pup.setStock(stockDecrement);

        Puppy pup2 = service.getPuppy("Skippy");

        assertEquals(0, pup2.getStock());

    }

    /**
     * Test of storeMoney method, of class PuppyVMServiceLayer.
     */
    @Test
    public void testStoreMoney() {

        Puppy pup = new Puppy();

        BigDecimal money = service.storeMoney(new BigDecimal("5"));

        assertEquals(new BigDecimal("5"), money);

    }

    /**
     * Test of returnMoney method, of class PuppyVMServiceLayer.
     */
    @Test
    public void testReturnMoney() {

        BigDecimal money = new BigDecimal("5.16");

        Change change = service.returnMoney(money);

        int quarters = change.getQuarters();
        assertEquals(20, quarters);
        int dimes = change.getDimes();
        assertEquals(1, dimes);
        int nickles = change.getNickles();
        assertEquals(1, nickles);
        int pennies = change.getPennies();
        assertEquals(1, pennies);

    }

    /**
     * Test of changeDue method, of class PuppyVMServiceLayer.
     */
    @Test
    public void testChangeDue() {

        BigDecimal userMoney = new BigDecimal("6");

        service.storeMoney(userMoney);

        BigDecimal change = service.changeDue(userMoney);

        assertNotEquals(change, new BigDecimal("0.00"));
    }

    /**
     * Test of sellPuppy method, of class PuppyVMServiceLayer.
     */
    @Test
    public void testUpdateInventory() throws Exception {

        Puppy pup = new Puppy();

//        String name = "Skippy";
        pup = service.getPuppy("Skippy");

        int stock = pup.getStock();

        int stockDecrement = stock - 1;

        pup.setStock(stockDecrement);

        int updatedStock = pup.getStock();

        assertEquals(0, updatedStock);

    }

    /**
     * Test of writePuppy method, of class PuppyVMServiceLayer.
     */
    @Test
    public void testWritePuppy() throws Exception {
    }

    /**
     * Test of loadPuppy method, of class PuppyVMServiceLayer.
     */
    @Test
    public void testLoadPuppy() throws Exception {
    }

    /**
     * Test of enoughPuppyMoney method, of class PuppyVMServiceLayer.
     */
}
