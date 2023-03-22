package com.sg.vendingMachine.controller;

import com.sg.vendingMachine.dao.ClassVendingMachinePersistenceException;
import com.sg.vendingMachine.dao.Coins;
import com.sg.vendingMachine.dto.Item;
import com.sg.vendingMachine.service.ClassInsufficientFundsException;
import com.sg.vendingMachine.service.ClassNoItemInventoryException;
import com.sg.vendingMachine.service.ClassVendingMachineServiceLayer;
import com.sg.vendingMachine.ui.ClassVendingMachineView;
import com.sg.vendingMachine.ui.UserIO;
import com.sg.vendingMachine.ui.UserIOConsoleImpl;
import com.sun.source.tree.MemberReferenceTree;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

@Controller

public class ClassVendingMachineController {

    private ClassVendingMachineView view;
    private ClassVendingMachineServiceLayer service;

    public ClassVendingMachineController(ClassVendingMachineServiceLayer service, ClassVendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        buy();
                        break;
                    case 2:
                        exit();
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (ClassVendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());

        }
    }


        public void buy() {

            //getting customer deposit from input in view
            String customerDeposit = view.getDeposit();
            BigDecimal inputMoney = new BigDecimal(customerDeposit);
            BigDecimal currency = inputMoney.setScale(2, RoundingMode.HALF_EVEN);

            boolean hasErrors = false;
            view.displayWelcomeBanner();
// should be inside run
            do {
                try {
                    // displaying the Items sending BigDecimal deposit to service for validating through
                    listItems();
                    String itemIDFromUser = view.getItemChoice();
                    vend(itemIDFromUser, currency);

                } catch (ClassVendingMachinePersistenceException | ClassNoItemInventoryException |
                         ClassInsufficientFundsException e) {
                    hasErrors = true;
                    view.displayErrorMessage(e.getMessage());
                }

            } while (hasErrors);

        }



    //menu
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }


 //Display all items
    private void listItems() throws ClassVendingMachinePersistenceException, ClassNoItemInventoryException, ClassInsufficientFundsException {

               Map<String,Item> itemsList = service.getAllItems();
               view.displayItemsList(itemsList);

    }

    // sell Item
    private  void vend(String itemID, BigDecimal currency) throws ClassVendingMachinePersistenceException, ClassNoItemInventoryException, ClassInsufficientFundsException {
        view.displayChangeMessage();
        Map<Coins,BigDecimal> change = service.sellItem(itemID, currency);
                view.displayChange(change);
                view.displaySoldSuccessBanner();

    }

    //exit item of menu
    private void exit() throws ClassVendingMachinePersistenceException {
        service.writeItem();
        view.displayExitBanner();
    }




    private void unknownCommand () {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage () {
        view.displayExitBanner();
    }

}
