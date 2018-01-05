package com.example.monikam.mathemory;

import android.util.ArrayMap;

import java.util.HashSet;
import java.util.Set;

class Parity extends CategoryClass {

    private int[] generated;
    private String instruction;
    private int counter; // zmienna do zliczania liczb spełniających warunek zadania

    @Override
    public String getInstruction(int curr_level) {

        if (curr_level == 3 || curr_level == 6 || curr_level == 9) {
            instruction = "liczby nieparzyste";
        }
        else {
            instruction = "liczby parzyste";
        }

        return instruction;
    }

    @Override
    public String[] generateNumbers(int fields_num, int curr_level) {

        generated = new int[fields_num];
        String[] sGenerated = new String[fields_num];
        Set<Integer> numbers = new HashSet<>(); // zbiór pomocniczy do przechowywania i porównywania wylosowanych liczb

        do {
                numbers.clear();
                counter = 0;

                for (int i = 0; i < fields_num; i++) {

                    // pętla odpowiadająca za niepowtarzalność losowanych liczb
                    do {
                        int multiplier = 10;
                        if (curr_level == 1) {
                            generated[i] = (int) (Math.random() * (multiplier * (curr_level + fields_num) + 1)); // zakres 0-100
                        }
                        else {
                            generated[i] = (int) (Math.random() * ((multiplier * curr_level - multiplier) + 1));
                        }
                    }
                    while (numbers.contains(generated[i]));

                    numbers.add(generated[i]);

                    // zliczanie liczb spełniających warunek zadania
                    if (instruction.equals("liczby parzyste")) {
                        if ((generated[i]) % 2 == 0) {
                            counter ++;
                        }
                    }

                    else {
                        if ((generated[i]) % 2 != 0) {
                            counter ++;
                        }
                    }

                    sGenerated[i] = String.valueOf(generated[i]);
                }
            }
            while (counter < (fields_num) / 2);

        return sGenerated;
    }

    @Override
    public int getCounter() {
        return counter;
    }


    @Override
    public boolean check(int sel_field) {

        if (instruction.equals("liczby parzyste")) {
            if (generated[sel_field] %2 == 0) {
                return true;
            }
        }
        else {
            if (generated[sel_field] % 2 != 0) {
                return true;
            }
        }

        return false;
    }

    public int generateForExample(boolean parity) {
        int number, rest;

        if (parity) {
            rest = 0;
        }
        else {
            rest = 1;
        }

        do {
            number = 12 + (int)(Math.random() * ((99 - 12) + 1)); // generowanie liczby podzielnej przez 2 z zakresu 12-99
        } while ( number % 2 != rest);

        return number;
    }
}

