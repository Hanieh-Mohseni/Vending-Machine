package com.sg.vendingMachine.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Change {
    private Map<Coins, BigDecimal> allCoinTypes = new HashMap<>();

    public Map<Coins, BigDecimal> changeCalculation(BigDecimal change) {

        // To have all in pennies
        change = change.multiply(BigDecimal.valueOf(100));



        //calculation
        BigDecimal quarterNum = change.divide(BigDecimal.valueOf(25), 0, RoundingMode.HALF_DOWN);
        BigDecimal changeForDim = change.subtract(quarterNum.multiply(BigDecimal.valueOf(25)));
        allCoinTypes.put(Coins.QUARTER, quarterNum);


            BigDecimal dimNum = changeForDim.divide(BigDecimal.valueOf(10), 0, RoundingMode.HALF_DOWN);
            BigDecimal changeForNickel = changeForDim.subtract(dimNum.multiply(BigDecimal.valueOf(10)));
            allCoinTypes.put(Coins.DIME, dimNum);

            BigDecimal nickelNum = changeForNickel.divide(BigDecimal.valueOf(5), 0, RoundingMode.HALF_DOWN);
            BigDecimal changeForPennies = changeForNickel.subtract(nickelNum.multiply(BigDecimal.valueOf(10)));
            allCoinTypes.put(Coins.NICKEL, nickelNum);
//
            allCoinTypes.put(Coins.PENNIES, changeForPennies);


        return allCoinTypes;
    }



}






