package com.example.kryguu.zadanie2;

/**
 * Created by kryguu on 11.03.2017.
 */

public class StringTools {

    static String numberToString(double number) { // changes number to String checking if number is whole or not
        long wholeNumber = (long) number;
        if (number == wholeNumber) {
            return String.valueOf(wholeNumber);
        } else {
            return String.format("%.3f", number);
        }
    }

}
