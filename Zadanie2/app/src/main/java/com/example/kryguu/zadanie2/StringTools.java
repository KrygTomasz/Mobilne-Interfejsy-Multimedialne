package com.example.kryguu.zadanie2;

/**
 * Created by kryguu on 11.03.2017.
 */

public class StringTools {

    static String numberToString(double number) { // funkcja zamieniająca liczbę na stringa, sprawdzając czy jest całkowita czy nie
        long wholeNumber = (long) number;
        if (number == wholeNumber) {
            return String.valueOf(wholeNumber);
        } else {
            return String.format("%.3f", number); //String.valueOf(number);
        }
    }

}
