package com.sg.vendingMachine.dao;

public interface ClassVendingMachineAuditDao {
    public void writeAuditEntry(String entry) throws ClassVendingMachinePersistenceException;

}
