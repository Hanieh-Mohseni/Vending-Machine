package com.sg.vendingMachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private String itemID;
    private String itemName;
    private String itemPrice;
    private int itemQuantity;

    //constructor. it's read only
    public Item (String itemID){
        this.itemID = itemID;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemQuantity == item.itemQuantity && itemID.equals(item.itemID) && itemName.equals(item.itemName) && itemPrice.equals(item.itemPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemID, itemName, itemPrice, itemQuantity);
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID='" + itemID + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
