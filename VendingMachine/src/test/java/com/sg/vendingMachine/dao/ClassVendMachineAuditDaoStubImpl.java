package com.sg.vendingMachine.dao;

import com.sg.vendingMachine.dao.ClassVendingMachineAuditDao;
import com.sg.vendingMachine.dao.ClassVendingMachinePersistenceException;

public class ClassVendMachineAuditDaoStubImpl implements ClassVendingMachineAuditDao {

    @Override
    public void writeAuditEntry(String entry) throws ClassVendingMachinePersistenceException {
        //do nothing . . .
    }
}
