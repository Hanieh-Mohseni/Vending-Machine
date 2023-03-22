package com.sg.vendingMachine.ui;

import com.sg.vendingMachine.dao.Coins;
import com.sg.vendingMachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@Component
public class ClassVendingMachineView {

    @Autowired
    private UserIO io = new UserIOConsoleImpl();

    public ClassVendingMachineView(UserIO io) {
        this.io = io;
    }




// Display Items List
    public void displayItemsList(Map<String,Item> itemList) {
        for (Item currentItem : itemList.values()) {
            String itemInfo = String.format("#%s : %s %s",
                    currentItem.getItemID(),
                    currentItem.getItemName(),
                    currentItem.getItemPrice());
            io.print(itemInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayExitOption(){
        io.print("EXIT");
    }


    // getting the selected item number
    public String getItemChoice() {

        return io.readString("Please enter the Item Number");
    }


    // Display All Items
    public void displayDisplayItemsBanner() {

        io.print("===  Items To Buy ===");
    }


    // Message after selling
    public void  displaySoldSuccessBanner(){
        io.print("=== Item sold successfully ===");

    }

// welcome message
public void  displayWelcomeBanner(){
    io.print("=== WELCOME ===");
}


// getting Deposit from customer
    public String getDeposit() {

        String customerDeposit = io.readString("Please Make a Deposit");
        return customerDeposit;
    }


    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Select Item");
        io.print("2. Exit");

        return io.readInt("Please select from the above choices.", 1, 2);
    }


    public void displayChange(Map<Coins, BigDecimal> change){

        for (int i=1; i< change.size();i++)
            io.print(change.values().toString());
    }

    public void displayChangeMessage(){
        io.print("Your Change: ");
    }



//  Exit Message
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    // Unknown message
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
