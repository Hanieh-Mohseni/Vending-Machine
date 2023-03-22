package com.sg.vendingMachine.dao;

import com.sg.vendingMachine.dto.Item;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ClassVendingMachineDaoFileImpl implements ClassVendingMachineDao {

    public  final String VENDINGMACHINE_FILE ;
            // "vendingMachine.txt";


    public static final String DELIMITER = "::";

    private Map<String, Item> items = new HashMap<>();

    public ClassVendingMachineDaoFileImpl(){
        VENDINGMACHINE_FILE = "VENDINGMACHINE_FILE.txt";
    }

    public ClassVendingMachineDaoFileImpl(String vendingMachineTextFile) throws ClassVendingMachinePersistenceException {
        VENDINGMACHINE_FILE = vendingMachineTextFile;
        loadVendingMachine();
    }



    @Override
    public Map<String,Item> getAllItems()
            throws ClassVendingMachinePersistenceException {
        loadVendingMachine();
        Map<String, Item> overZeroQuantityItems = items.values().stream()
                .filter((item) -> item.getItemQuantity() > 0)
                .collect(Collectors.toMap(m -> m.getItemID(), m -> m));

        return overZeroQuantityItems;
    }

    @Override
    public Map<Coins,BigDecimal> sellItem(String itemID, BigDecimal currency) throws ClassVendingMachinePersistenceException {

        //updating inventory
        loadVendingMachine();
        int soldItemQuantity=((items.get(itemID)).getItemQuantity())-1;
        (items.get(itemID)).setItemQuantity(soldItemQuantity);

       // for change
        String price = String.valueOf(items.get(itemID).getItemPrice());
        BigDecimal priceInBigDecimal = new BigDecimal(price);
        BigDecimal change = currency.subtract(priceInBigDecimal);
        Change changClass = new Change();
        //service layer
        Map<Coins,BigDecimal> changeToShow = changClass.changeCalculation(change);

        return changeToShow;

    }

    @Override
    public void writeItem() throws ClassVendingMachinePersistenceException {
        writeVendingMachine();
    }

    // Read
    private Item unmarshallItem(String itemAsText){

        String[] itemTokens = itemAsText.split(DELIMITER);

        // The item I-d is in index 0 of the array.
        String itemId = itemTokens[0];

        // Which we can then use to create a new item object to satisfy
        // the requirements of the item constructor.
        Item itemFromFile = new Item(itemId);

        // However, there are 3 remaining tokens that need to be set into the
        // new Item object:

        // Index 1 - itemName
        itemFromFile.setItemName(itemTokens[1]);

        // Index 2 - itemPrice
        itemFromFile.setItemPrice(itemTokens[2]);

        // Index 3 - itemQuantity
        itemFromFile.setItemQuantity(Integer.parseInt(itemTokens[3]));

        // We have now created an Item! Return it!
        return itemFromFile;
    }


    private void loadVendingMachine() throws ClassVendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(VENDINGMACHINE_FILE)));
        } catch (FileNotFoundException e) {
            throw new ClassVendingMachinePersistenceException(
                    "-_- Could not load vending Machine data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentItem holds the most recent item unmarshalled
        Item currentItem;
        // Go through VENDINGMACHINE_FILE line by line, decoding each line into a
        // Item object by calling the unmarshallItem method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into an Item
            currentItem = unmarshallItem(currentLine);

            // We are going to use the item id as the map key for our Item object.
            // Put currentItem into the map using Item id as the key
            items.put(currentItem.getItemID(), currentItem);
        }
        // close scanner
        scanner.close();
    }


    private String marshallItem(Item anItem){
        // We need to turn an Item object into a line of text for the file.
        // Just get out each property, and concatenate with the DELIMITER.

        String itemAsText = anItem.getItemID() + DELIMITER;

        // add the rest of the properties in the correct order:

        // itemName
        itemAsText += anItem.getItemName() + DELIMITER;

        // itemPrice
        itemAsText += anItem.getItemPrice() + DELIMITER;

        // itemQuantity
        itemAsText += anItem.getItemQuantity();

        // We have now turned an Item to text! Return it!
        return itemAsText;
    }



    /**
     * Writes all Items in the vending machine out to a VENDINGMACHINE_FILE.  See loadVendingMachine
     * for file format.
     *
     * @throws ClassVendingMachinePersistenceException if an error occurs writing to the file
     */
    private void writeVendingMachine() throws ClassVendingMachinePersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(VENDINGMACHINE_FILE));
        } catch (IOException e) {
            throw new ClassVendingMachinePersistenceException(
                    "Could not save Item data.", e);
        }

        // Write out the Item objects to the  file.

        String itemAsText;
        Map<String, Item> itemList = this.getAllItems();
        for (Item currentItem : itemList.values()) {
            // turn a Item into a String
            itemAsText = marshallItem(currentItem);
            // write the Item object to the file
            out.println(itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}






