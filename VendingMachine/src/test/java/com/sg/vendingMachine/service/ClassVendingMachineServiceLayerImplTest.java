package com.sg.vendingMachine.service;

import com.sg.vendingMachine.dao.*;
import com.sg.vendingMachine.dto.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ClassVendingMachineServiceLayerImplTest {

    private ClassVendingMachineServiceLayer service;

//    public ClassVendingMachineServiceLayerImplTest() {
//        ClassVendingMachineDao dao = new ClassVendingMachineDaoStubImpl();
//        ClassVendingMachineAuditDao auditDao = new ClassVendingMachineAuditDaoImpl();
//        service = new ClassVendingMachineServiceLayerImpl(dao, auditDao);
//    }


    //constructor
    public ClassVendingMachineServiceLayerImplTest() {

        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        service =
                ctx.getBean("serviceLayer", ClassVendingMachineServiceLayer.class);
    }


    @BeforeEach
    void setUp() {
        ClassVendingMachineDao dao = new ClassVendingMachineDaoStubImpl();
        ClassVendingMachineAuditDao auditDao = new ClassVendingMachineAuditDaoImpl();
        service = new ClassVendingMachineServiceLayerImpl(dao, auditDao);


    }


    @Test
    void getAllItems() throws ClassVendingMachinePersistenceException, ClassNoItemInventoryException, ClassInsufficientFundsException {

        Map<String, Item> items = service.getAllItems();
        Item item1 = new Item("1");
        item1.setItemName("ChocolateA");
        item1.setItemPrice("1.70");
        item1.setItemQuantity(10);

        assertEquals(1, items.size(),
                "Should have one item.");
    }


    @Test
    void sellItem() throws ClassVendingMachinePersistenceException, ClassNoItemInventoryException, ClassInsufficientFundsException {
        //check quantity
        Map<String,Item> items = service.getAllItems();
        service.sellItem("1",BigDecimal.valueOf(80));
        assertEquals(items.get("1").getItemQuantity(),14);

    }

    @Test
    void writeItem() {
        assertDoesNotThrow(() -> service.writeItem());
    }
}