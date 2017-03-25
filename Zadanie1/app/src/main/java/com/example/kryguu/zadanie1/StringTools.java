package com.example.kryguu.zadanie1;

/**
 * Created by kryguu on 25.03.2017.
 */

public class StringTools {
    static public String numberToString(double number) { // changes float to string checking if given number is whole or not
        long wholeNumber = (long) number;
        if (number == wholeNumber) {
            return String.valueOf(wholeNumber);
        } else {
            return String.valueOf(number);
        }
    }

    static public String roundNumberToString(double number) { // changes float to string checking if given number is whole or not and rounds
        long wholeNumber = (long) number;
        if (number == wholeNumber) {
            return String.valueOf(wholeNumber);
        } else {
            return String.format("%.2f", number);
        }
    }
}
