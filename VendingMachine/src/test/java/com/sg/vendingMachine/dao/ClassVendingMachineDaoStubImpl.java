package com.sg.vendingMachine.dao;

import com.sg.vendingMachine.dao.Change;
import com.sg.vendingMachine.dao.ClassVendingMachineDao;
import com.sg.vendingMachine.dao.ClassVendingMachinePersistenceException;
import com.sg.vendingMachine.dao.Coins;
import com.sg.vendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassVendingMachineDaoStubImpl implements ClassVendingMachineDao {

    public Item item1;
    private Map<String, Item> items = new HashMap<>();



    public ClassVendingMachineDaoStubImpl() {
        item1 = new Item("1");
        item1.setItemName("ChocolateA");
        item1.setItemPrice("20");
        item1.setItemQuantity(15);
        items.put("1",item1);



    }


    public ClassVendingMachineDaoStubImpl(Item testItem){
        this.item1 = testItem;
    }


    @Override
    public Map<String, Item> getAllItems() throws ClassVendingMachinePersistenceException {

        return items;

    }
    @Override
    public Map<Coins, BigDecimal> sellItem(String itemID, BigDecimal currency) throws ClassVendingMachinePersistenceException
        {
            int soldItemQuantity=((items.get(itemID)).getItemQuantity())-1;
            (items.get(itemID)).setItemQuantity(soldItemQuantity);

            // for change
            String price = String.valueOf(items.get(itemID).getItemPrice());
            BigDecimal priceInBigDecimal = new BigDecimal(price);
            BigDecimal change = currency.subtract(priceInBigDecimal);
            Change changClass = new Change();
            Map<Coins,BigDecimal> changeToShow = changClass.changeCalculation(change);

            return changeToShow;

        }

    @Override
    public void writeItem() throws ClassVendingMachinePersistenceException {

    }
}
