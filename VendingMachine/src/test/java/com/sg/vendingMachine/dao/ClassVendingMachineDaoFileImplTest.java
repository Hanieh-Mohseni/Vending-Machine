package com.sg.vendingMachine.dao;

import com.sg.vendingMachine.dto.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ClassVendingMachineDaoFileImplTest {

    ClassVendingMachineDao testDao;

//    public ClassVendingMachineDaoFileImplTest() {
//    }

    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testVendingMachine.txt";
        // Use the FileWriter to quickly blank the file
        testDao = new ClassVendingMachineDaoFileImpl(testFile);
    }

//
//
    @Test
    void getAllItems()throws ClassVendingMachinePersistenceException {

        Map<String,Item> items = testDao.getAllItems();

        Item item1 = new Item("1");
        item1.setItemName("ChocolateA");
        item1.setItemPrice("1.70");
        item1.setItemQuantity(10);

        Item item2 = new Item("2");
        item2.setItemName("ChocolateB");
        item2.setItemPrice("2");
        item2.setItemQuantity(19);

       // assertTrue(items.containsValue(item1));
        assertTrue(item1.getItemID().equals(items.get("1").getItemID()) );

    }
//
    @Test
    void sellItem()throws ClassVendingMachinePersistenceException {

        Map<String,Item> items = testDao.getAllItems();
        int quantityBeforeSell = items.get("2").getItemQuantity()-1;
        testDao.sellItem("2", new BigDecimal("80"));
        int quantityAfterSell = items.get("2").getItemQuantity();
        assertEquals(quantityBeforeSell,quantityAfterSell);

    }

    @Test
    void writeItem()throws ClassVendingMachinePersistenceException {

        Map<String,Item> items = testDao.getAllItems();
        testDao.writeItem();


        // open file

    }
}