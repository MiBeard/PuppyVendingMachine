/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.puppyvm.View;

import com.sg.puppyvm.Dto.Change;
import com.sg.puppyvm.Dto.Puppy;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jamesbond
 */
public class PuppyVMView {

    private UserIO io;

    public PuppyVMView(UserIO io) {
        this.io = io;
    }

    public int menuSelection() {
        io.print("\n1) Pick from the menu");
        io.print("2) Exit");

        return io.readInt("", 1, 2);
    }

    public void displayAllPuppies(List<Puppy> puppyList) {
        int itemNum = 0;
        for (Puppy currentPuppy : puppyList) {
            itemNum++;
            io.print(itemNum + ") " + currentPuppy.getName() + ": $"
                    + currentPuppy.getPrice() + ", "
                    + currentPuppy.getStock());
        }
    }

    public void displayAllPuppiesBanner() {
        io.print("All available puppies:\n");
    }

    public String getPuppyChoice() {
        String puppyName;
        puppyName = io.readString("What puppy would you \n"
                + "like to purchase?");
        return puppyName;
    }

    public void outOfStock() {
        io.print("\nSorry, \n"
                + "we're out of that breed, yo!\n"
                + "----------------------------");
    }

    public void displayPuppy(Puppy pup) {

        if (pup != null && pup.getStock() > 0) {
            io.print("\nPuppy: " + pup.getName());
            io.print("Price: " + pup.getPrice());
            io.print("In stock: " + pup.getStock());
        }
    }

    public void exitBanner() {
        io.print("\nThank you for your time!");
    }

    public Puppy getNewPuppyInfo() {

        String name = io.readString("Enter the name of your puppy");
        BigDecimal cost = io.readBigDecimal("Enter the cost of your puppy");
        int inventory = io.readInt("Enter the number of puppies left");

        Puppy pup = new Puppy();
        pup.setName(name);
        pup.setPrice(cost);
        pup.setStock(inventory);

        return pup;
    }

    public void createPuppy() {
        io.print("===Create Puppy===");
    }

    public void addPuppySuccess() {
        io.print("Successfully created puppy");
    }

    public void removePuppy() {
        io.print("===Remove puppy===");
    }

    public void removeOption() {
        io.readString("Which puppy do you want to remove?");
    }

    public void removedPuppySuccess() {
        io.print("Remove complete. Hit enter to continue");
    }

    public void unknownCommandBanner() {
        io.print("Unknown Command");
    }

    public void displayErrorMessage(String errMsg) {
        io.print("===Error===");
        io.print(errMsg);
    }

    public BigDecimal userMoneyAmount() {
        BigDecimal userMoney = io.readBigDecimal("\nHow much money do you plan\n"
                + "on using for your purchase?\n");

        return userMoney;
    }

    public void notEnoughMoney() {
        io.print("\nInsufficient funds for purchase\n");
    }

    public BigDecimal moneyBeingReturned(BigDecimal userMoney, BigDecimal pupPrice) {

        BigDecimal total = pupPrice.subtract(userMoney);

        if (pupPrice.compareTo(userMoney) > 0) {
            io.print("\nYour receive $" + total + " back.\n");
        }

        return total;

    }

    public void userChange(Change userChange) {

        io.print("Here is your change:");
        io.print("Quarters: " + userChange.getQuarters());
        io.print("Dimes: " + userChange.getDimes());
        io.print("Nickles: " + userChange.getNickles());
        io.print("Pennies: " + userChange.getPennies());
        io.print("\n");

    }
}
