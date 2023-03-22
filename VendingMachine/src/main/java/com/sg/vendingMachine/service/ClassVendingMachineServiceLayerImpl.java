package com.sg.vendingMachine.service;

import com.sg.vendingMachine.dao.ClassVendingMachineAuditDao;
import com.sg.vendingMachine.dao.ClassVendingMachineDao;
import com.sg.vendingMachine.dao.ClassVendingMachinePersistenceException;
import com.sg.vendingMachine.dao.Coins;
import com.sg.vendingMachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class ClassVendingMachineServiceLayerImpl implements ClassVendingMachineServiceLayer {

    private ClassVendingMachineAuditDao auditDao;
    ClassVendingMachineDao dao;

    @Autowired
    public ClassVendingMachineServiceLayerImpl(ClassVendingMachineDao dao, ClassVendingMachineAuditDao myAuditDao) {
        this.dao = dao;
        this.auditDao = myAuditDao;
    }




    @Override
    public Map<String,Item> getAllItems() throws   ClassVendingMachinePersistenceException {
        return dao.getAllItems();
    }

    @Override
    public Map<Coins, BigDecimal> sellItem(String itemID, BigDecimal currency) throws  ClassInsufficientFundsException,ClassNoItemInventoryException, ClassVendingMachinePersistenceException {


        //validateItemQuantity();
        Item item=dao.getAllItems().get(itemID);
        if(item.getItemQuantity()==0){
            throw new ClassNoItemInventoryException("The Item Is Out Of Stock");
        }

        // validateSufficientFunds();
        if(item.getItemPrice().compareTo(currency.toString())==1){
            throw new ClassInsufficientFundsException("Your Deposit Is Insufficient");
        }

        Map<Coins, BigDecimal> change ;
//calculating the change
        change= dao.sellItem(itemID, currency);

        // Write to audit log
        auditDao.writeAuditEntry("The item was sold");

        return change;
    }

    @Override
    public void writeItem() throws ClassVendingMachinePersistenceException {
       dao.writeItem();
    }



    private void validateItemQuantity(Item item) throws
            ClassNoItemInventoryException {

        if (item.getItemQuantity() == 0)
            throw new ClassNoItemInventoryException(
                    "ERROR: This item has zero inventory.");
    }

    private void validateSufficientFunds () throws
            ClassInsufficientFundsException{

    }




}
