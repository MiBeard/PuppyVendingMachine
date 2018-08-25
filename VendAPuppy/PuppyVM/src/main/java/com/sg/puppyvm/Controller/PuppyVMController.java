/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.puppyvm.Controller;

import com.sg.puppyvm.Dao.PuppyVMDaoPersistanceException;
import com.sg.puppyvm.Dto.Change;
import com.sg.puppyvm.Dto.Puppy;
import com.sg.puppyvm.ServiceLayer.NoItemInventoryException;
import com.sg.puppyvm.ServiceLayer.PuppyVMServiceLayer;
import com.sg.puppyvm.ServiceLayer.InsufficientFundsException;
import com.sg.puppyvm.View.PuppyVMView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jamesbond
 */
public class PuppyVMController {

    private PuppyVMView view;
    private PuppyVMServiceLayer service;

    public PuppyVMController(PuppyVMView view,
            PuppyVMServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        int mainMenuSelection = 0;
        try {

            while (keepGoing) {
                mainMenuSelection = menuPuppySelection();
                switch (mainMenuSelection) {
                    case 1:
                        menuOption();
                        break;
                    case 2:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            goodBye();
        } catch (PuppyVMDaoPersistanceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void menuOption()
            throws PuppyVMDaoPersistanceException //            InsufficientFundsException,
    {

        BigDecimal userMoney = view.userMoneyAmount();
        String name = puppyPicker();
        service.storeMoney(userMoney);

        try{ 
            Puppy pup = service.getPuppy(name);
            BigDecimal changeLeft = view.moneyBeingReturned(pup.getPrice(), userMoney);
            Change userChange = service.returnMoney(changeLeft);
            view.userChange(userChange);
            service.sellPuppy(name);
            service.writePuppy();
        } catch(InsufficientFundsException e){
            view.notEnoughMoney();
        } catch (NoItemInventoryException e) {
            view.outOfStock();
        }
    }

    private int menuPuppySelection()
            throws PuppyVMDaoPersistanceException {
        service.loadPuppy();
        List<Puppy> puppies = service.getAllPuppies();
        view.displayAllPuppies(puppies);
        return view.menuSelection();
    }

    private String puppyPicker()
            throws PuppyVMDaoPersistanceException {
        String pupName = "";
        boolean errorThrown = false;
        do {
            service.loadPuppy();

            pupName = view.getPuppyChoice();

            try {
                Puppy pup = service.getPuppy(pupName);
                view.displayPuppy(pup);
                errorThrown = false;
            } catch (PuppyVMDaoPersistanceException e) {
                view.displayErrorMessage(e.getMessage());
                errorThrown = true;
            }

        } while (service.getPuppy(pupName) == null || errorThrown);
        return pupName;
    }

    public void goodBye() {
        view.exitBanner();
    }

    private void unknownCommand() {
        view.unknownCommandBanner();
    }

}
