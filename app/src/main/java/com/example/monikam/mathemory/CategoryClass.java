package com.example.monikam.mathemory;

/**
 * Created by MonikaM on 2017-11-23.
 */


abstract class CategoryClass {

    public abstract String[] generateNumbers(int fields_num, int curr_level); // funkcja generująca liczby dla plansz

    public abstract boolean check(int sel_field); // funkcja sprawdzająca poprawność
}
