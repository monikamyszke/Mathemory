package com.example.monikam.mathemory;

import java.util.HashSet;
import java.util.Set;

/**
 * Klasa implementująca metody dotyczące rozgrywki w kategorii Podzielność
 */
class Divisibility extends CategoryClass {

    /**Tablica wygenerowanych liczb*/
    private int[] generated;
    /**Instrukcja wyświetlana na górze ekranu*/
    private String instruction;
    /**Zmienna do zliczania liczb spełniających warunek zadania*/
    private int counter;

    @Override
    public String getInstruction(int curr_level) {

        if (curr_level == 3 || curr_level == 6 || curr_level == 9) {
            instruction = "liczby podzielne przez 5";
        }
        else {
            instruction = "liczby podzielne przez 3";
        }

        return instruction;
    }

    @Override
    public String[] generateNumbers(int fields_num, int curr_level) {

        generated = new int[fields_num];
        String[] sGenerated = new String[fields_num];
        Set<Integer> numbers = new HashSet<>();

        do {
            numbers.clear();
            counter = 0;

            for (int i = 0; i < fields_num; i++) {

                do {
                    int multiplier = 10;
                    int multiplier2 = 7;
                    if (curr_level == 1) {
                        generated[i] = 1 + (int) (Math.random() * (multiplier * (curr_level + fields_num))); // zakres 1-100
                    }
                    else {
                        if (instruction.equals("liczby podzielne przez 3")) {
                            generated[i] = 1 + (int) (Math.random() * (multiplier2 * curr_level));
                        }
                        else {
                            generated[i] = 1 + (int) (Math.random() * (multiplier * curr_level - multiplier));
                        }
                    }
                }
                while (numbers.contains(generated[i]));

                numbers.add(generated[i]);

                if (instruction.equals("liczby podzielne przez 3")) {
                    if ((generated[i]) % 3 == 0) {
                        counter ++;
                    }
                }

                else
                    {
                    if ((generated[i]) % 5 == 0) {
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

        if (instruction.equals("liczby podzielne przez 3")) {
            if (generated[sel_field] % 3 == 0) {
                return true;
            }
        }
        else {
            if (generated[sel_field] % 5 == 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * Funkcja generująca liczbę dla przykładu podzielności przez 3
     * @return liczba wykorzystana w przykładzie
     */
    int generateForExample() {
        int divisible;

        do {
            divisible = 12 + (int)(Math.random() * ((99 - 12) + 1)); // generowanie liczby podzielnej przez 3 z zakresu 12-99
        }
        while ( divisible % 3 != 0);

        return divisible;
    }
}
