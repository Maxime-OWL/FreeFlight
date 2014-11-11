/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Maxime
 */
public class FormatMoney {

    public static void main(String args[]) {
        double price = 100.25;
    }

    /**
     * * Display price in US Dollar currency * * @param price * @param rate
     */
    public String showPriceInUSD(double price, double rate) {
        double priceInUSD = price * rate;
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return currencyFormat.format(priceInUSD);
    }

    /**
     * * Display prince in British Pound * * @param price * @param rate
     */
    public String showPriceInGBP(double price, double rate) {
        double princeInGBP = price * rate;
        NumberFormat GBP = NumberFormat.getCurrencyInstance(Locale.UK);
        return GBP.format(princeInGBP);
    }

    /**
     * * Display prince in Japanese Yen * * @param price * @param rate
     */
    public static void showPriceInJPY(double price, double rate) {
        double princeInJPY = price * rate;
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.JAPAN);
        System.out.printf("Price in JPY : %s %n", currency.format(princeInJPY));
    }

    /**
     * * @return FX exchange rate for USD * @param currency
     */
    public static double getExchangeRate(String currency) {
        switch (currency) {
            case "USD":
                return 1;
            case "JPY":
                return 102.53;
            case "GBP":
                return 0.60;
            case "EURO":
                return 0.73;
            default:
                throw new IllegalArgumentException(String.format("No rates available for currency %s %n", currency));
        }
    }
}
