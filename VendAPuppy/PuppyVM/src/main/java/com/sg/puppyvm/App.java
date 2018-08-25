/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.puppyvm;

import com.sg.puppyvm.Controller.PuppyVMController;
import com.sg.puppyvm.Dao.PuppyVMDaoPersistanceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jamesbond
 */
public class App {

    public static void main(String[] args) 
            throws PuppyVMDaoPersistanceException {
//
//        UserIO io = new PuppyVMUserIOImpl();
//        PuppyVMView view = new PuppyVMView(io);
//        PuppyVMDao dao = new PuppyVMDaoFileImpl();
//        PuppyVMAuditDao myAuditDao = new PuppyVMAuditDaoFileImpl();
//        PuppyVMServiceLayer service = new PuppyVMServiceLayerImpl(dao, myAuditDao);
//        PuppyVMController controller = new PuppyVMController(view, service);
//        controller.run();
//    }
//--------------------------

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        PuppyVMController controller
                = ctx.getBean("controller", PuppyVMController.class);
        controller.run();
    }

}
