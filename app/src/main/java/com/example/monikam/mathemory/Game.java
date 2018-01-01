package com.example.monikam.mathemory;

import com.example.monikam.mathemory.Divisibility;
import com.example.monikam.mathemory.Fractions;
import com.example.monikam.mathemory.Parity;
import com.example.monikam.mathemory.PrimeAndComposite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Game {

    private static Game game;
    private File save;
    private Parity parity;
    private Divisibility divisibility;
    private PrimeAndComposite primeAndComposite;
    private Fractions fractions;

    private static int[] stars = new int[40]; // tablica przechowująca ilośc gwiazdek
    static boolean[] unlockedLevels = new boolean[40]; // tablica przechowująca informacje o blokadzie poziomów
    static boolean[] unlockedCategories = new boolean[4]; // tablica przechowująca informacje o blokadzie kategorii

    static void initGame(File save) { // obiekt klasy Game może być utworzony tylko 1 raz
        if (game == null) {
            game = new Game();
            game.save = new File(save.toString(), "saved_data.txt");
            game.readFromFile();
        }
    }
// obiekt klasy Game może być utworzony tylko wewnątrz tej klasy
    private Game() {
        this.parity = new Parity();
        this.divisibility = new Divisibility();
        this.primeAndComposite = new PrimeAndComposite();
        this.fractions = new Fractions();
    }

    static CategoryClass getCategory(String category) {
        if (category.equals("Parzystość liczb")) {
            return game.parity;
        }
        else if (category.equals("Podzielność liczb")) {
            return game.divisibility;
        }
        else if (category.equals("Liczby pierwsze i złożone")) {
            return game.primeAndComposite;
        }
        else if (category.equals("Ułamki właściwe i niewłaściwe")) {
            return game.fractions;
        }
        else
            return null;

    }

    static void completeLevel(CategoryClass category, int whichLevel, int star) {
        int p = getElement(category);
        if (star > stars[whichLevel + p - 1]) { // sprawdzenie czy poprawiono wynik
            stars[whichLevel + p - 1] = star; // zapisanie liczby gwiazdek do tablicy
        }
        if ((whichLevel + p) < 40) {
            unlockedLevels[whichLevel + p] = true; // odblokowanie kolejnego poziomu
        }
        if (whichLevel %10 == 0 && p < 30) { // odblokowanie kolejnej kategorii
            unlockedCategories[(p + whichLevel) / 10] = true;
        }

        game.saveToFile(); // zapis danych do pliku
    }

    // funkcja zwracająca liczbę gwiazdek przyznaną za dany poziom w kategorii
    static int getStars(CategoryClass category, int whichLevel ) {
        int starsAmount;
        int p = getElement(category);
        starsAmount = stars[whichLevel + p - 1];
        return starsAmount;
    }

    // funkcja zwracająca stan poziomu (odblokowany/zablokowany)
    static boolean getLevelStates(CategoryClass category, int whichLevel ) {
        boolean levelState;
        int p = getElement(category);
        levelState = unlockedLevels[whichLevel + p - 1];
        return levelState;
    }

    // funkcja zwracająca stan kategorii (odblokowany/zablokowany)
    static boolean getCategoryStates(int category) {
        boolean categoryState;
        categoryState = unlockedCategories[category - 1];
        return categoryState;
    }

    // funkcja zwracająca zmienną pomocniczą do definiowania poziomów kategorii w tablicy
    private static int getElement(CategoryClass category) {
        int element;
        if (category == game.parity) {
            element = 0;
        }
        else if (category == game.divisibility) {
            element = 10;
        }
        else if (category == game.primeAndComposite) {
            element = 20;
        }
        else {
            element = 30;
        }

        return element;
    }

    // zapis danych do pliku
    private void saveToFile() {
        try {
            File file = save;
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream f = new FileOutputStream(file, false);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(unlockedCategories);
            o.writeObject(unlockedLevels);
            o.writeObject(stars);
            o.close();
            f.close();
        }
        catch (IOException e) {
           e.printStackTrace();
        }

   }

   // odczyt danych z pliku
   private void readFromFile() {
       try {
           File file = save;
           FileInputStream f = new FileInputStream(file);
           ObjectInputStream o = new ObjectInputStream(f);
           unlockedCategories = (boolean[]) o.readObject();
           unlockedLevels = (boolean[]) o.readObject();
           stars = (int[]) o.readObject();
           o.close();
           f.close();

       }
       catch (IOException | ClassNotFoundException e) {
           e.printStackTrace();
       }

   }

    public static Game getInstance() {
        return game;
    }

}
