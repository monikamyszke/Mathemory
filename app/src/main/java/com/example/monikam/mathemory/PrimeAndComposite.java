package com.example.monikam.mathemory;

import java.util.HashSet;
import java.util.Set;

class PrimeAndComposite extends CategoryClass {

    private int[] generated;
    private String instruction;

    @Override
    public String getInstruction(int curr_level) {

        if (curr_level == 3 || curr_level == 6 || curr_level == 9) {
            instruction = "Wybierz liczby złożone:";
        }
        else {
            instruction = "Wybierz liczby pierwsze:";
        }

        return instruction;
    }

    @Override
    public String[] generateNumbers(int fields_num, int curr_level) {

        generated = new int[fields_num];
        String[] sGenerated = new String[fields_num];
        Set<Integer> numbers = new HashSet<>();

        int counter;

        do {
            numbers.clear();
            counter = 0;

            for (int i = 0; i < fields_num; i++) {

                do {
                    int multiplier = 10;
                    if (curr_level == 1) {
                        generated[i] = 2 + (int) (Math.random() * (multiplier * (curr_level + fields_num)) - 1);
                    }
                    else {
                        generated[i] = 2 + (int) (Math.random() * ((multiplier * curr_level - multiplier) - 1));
                    }
                }
                while (numbers.contains(generated[i]));

                numbers.add(generated[i]);

                if (instruction.equals("Wybierz liczby pierwsze:")) {
                    if (checkIfPrime(generated[i])) {
                        counter ++;
                    }
                }

                else {
                    if (!checkIfPrime(generated[i])) {
                        counter ++;
                    }
                }

                sGenerated[i] = String.valueOf(generated[i]);
            }
        }
        while (counter < (fields_num) / 2);

        return sGenerated;
    }

    private boolean checkIfPrime(int number) {
        for (int j = 2; j <= (number / 2); j++) {
            if (number % j == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean check(int sel_field) {

        if (instruction.equals("Wybierz liczby pierwsze:")) {
            if (checkIfPrime(generated[sel_field])) {
                return true;
            }
        }
        else {
            if (!checkIfPrime(generated[sel_field])) {
                return true;
            }
        }

        return false;
    }
}
