package com.sg.vendingMachine.dao;

import com.sg.vendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.Map;

public interface ClassVendingMachineDao {


    Map<String,Item> getAllItems()
            throws ClassVendingMachinePersistenceException;

    Map<Coins,BigDecimal>   sellItem(String itemID, BigDecimal currency)
           throws ClassVendingMachinePersistenceException;

    void writeItem()
            throws ClassVendingMachinePersistenceException;

}
