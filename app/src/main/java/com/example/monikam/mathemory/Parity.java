package com.example.monikam.mathemory;

import android.util.ArrayMap;

import java.util.HashSet;
import java.util.Set;

class Parity extends CategoryClass {

    private int[] generated;
    private String instruction;

    @Override
    public String getInstruction(int curr_level) {

        if (curr_level == 3 || curr_level == 6 || curr_level == 9) {
            instruction = "Wybierz liczby nieparzyste:";
        }
        else {
            instruction = "Wybierz liczby parzyste:";
        }

        return instruction;
    }

    @Override
    public String[] generateNumbers(int fields_num, int curr_level) {

        generated = new int[fields_num];
        String[] sGenerated = new String[fields_num];
        Set<Integer> numbers = new HashSet<>(); // zbiór pomocniczy do przechowywania i porównywania wylosowanych liczb
        ArrayMap<Integer, Integer> fractions = new ArrayMap<>();

        int counter; // zmienna do zliczania liczb spełniających warunek zadania

        do {
                numbers.clear();
                counter = 0;

                for (int i = 0; i < fields_num; i++) {

                    // pętla odpowiadająca za niepowtarzalność losowanych liczb
                    do {
                        int multiplier = 10;
                        if (curr_level == 1) {
                            generated[i] = (int) (Math.random() * (multiplier * (curr_level + fields_num) + 1));
                        }
                        else {
                            generated[i] = (int) (Math.random() * ((multiplier * curr_level - multiplier) + 1));
                        }
                    }
                    while (numbers.contains(generated[i]));

                    numbers.add(generated[i]);

                    // zliczanie liczb spełniających warunek zadania
                    if (instruction.equals("Wybierz liczby parzyste:")) {
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
    public boolean check(int sel_field) {

        if (instruction.equals("Wybierz liczby parzyste:")) {
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
}

