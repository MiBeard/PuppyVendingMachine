/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.puppyvm.Dao;

import com.sg.puppyvm.Dto.Puppy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jamesbond
 */
public class PuppyVMDaoFileImpl implements PuppyVMDao {

    private Map<String, Puppy> labeledBoxesOfPuppies = new HashMap<>();
    public static final String InventoryFile = "puppyChoice.txt";
    public static final String DELIMITER = "::";
    
    @Override
    public Puppy addPuppy(String name, Puppy price) {
        Puppy newPuppy = labeledBoxesOfPuppies.put(name, price);
        return newPuppy;
    }

    @Override
    public List<Puppy> getAllPuppies() {
        return new ArrayList<Puppy>(labeledBoxesOfPuppies.values());
    }

    @Override
    public Puppy getPuppy(String name) {
        Puppy pup;

        pup = labeledBoxesOfPuppies.get(name);

        return pup;
    }

    @Override
    public void updatePuppy(Puppy puppyToUpdate) {
        labeledBoxesOfPuppies.replace(puppyToUpdate.getName(), puppyToUpdate);
    }

    @Override
    public Puppy removePuppy(String name) {
        Puppy pup = labeledBoxesOfPuppies.remove(name);
        return pup;
    }

    @Override
    public void loadPuppy()
            throws PuppyVMDaoPersistanceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(InventoryFile)));
        } catch (FileNotFoundException e) {
            throw new PuppyVMDaoPersistanceException(
                    "-_- There was an error while attempting to read the file.", e);
        }
        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);
            Puppy currentItem = new Puppy();
            currentItem.setName(currentTokens[0]);
            currentItem.setPrice(new BigDecimal(currentTokens[1]));
            currentItem.setStock(Integer.parseInt(currentTokens[2]));
            labeledBoxesOfPuppies.put(currentItem.getName(), currentItem);
        }
        scanner.close();
    }

    @Override
    public void writePuppy()
            throws PuppyVMDaoPersistanceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(InventoryFile));
        } catch (IOException e) {
            throw new PuppyVMDaoPersistanceException(
                    "-_- There was an error while attempting to read the file.", e);
        }

        List<Puppy> itemList = this.getAllPuppies();
        for (Puppy currentItem : itemList) {
            out.println(currentItem.getName() + DELIMITER
                    + currentItem.getPrice() + DELIMITER
                    + currentItem.getStock());

            out.flush();
        }
        out.close();

    }

}
