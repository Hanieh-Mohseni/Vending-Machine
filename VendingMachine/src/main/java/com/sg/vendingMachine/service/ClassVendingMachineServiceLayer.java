package com.sg.vendingMachine.service;

import com.sg.vendingMachine.dao.ClassVendingMachinePersistenceException;
import com.sg.vendingMachine.dao.Coins;
import com.sg.vendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.Map;

public interface ClassVendingMachineServiceLayer {

    Map<String,Item> getAllItems() throws
            ClassNoItemInventoryException,
            ClassInsufficientFundsException,
            ClassVendingMachinePersistenceException;


    Map<Coins, BigDecimal> sellItem(String itemID, BigDecimal currency) throws
            ClassNoItemInventoryException,
            ClassInsufficientFundsException,
            ClassVendingMachinePersistenceException;

    void writeItem() throws
            ClassVendingMachinePersistenceException;

}

