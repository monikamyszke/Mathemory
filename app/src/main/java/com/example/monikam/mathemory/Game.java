package com.example.monikam.mathemory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Klasa reprezentująca właściwą grę; obiekt tej klasy może zostać utworzony tylko 1 raz, wewnątrz tej klasy
 */
class Game {

    /**Obiekt klasy Game*/
    private static Game game;
    /**Plik do zapisu i odczytu danych*/
    private File save;
    /**Obiekt klasy Parity*/
    private Parity parity;
    /**Obiekt klasy Divisibility*/
    private Divisibility divisibility;
    /**Obiekt klasy PrimeAndComposite*/
    private PrimeAndComposite primeAndComposite;
    /**Obiekt klasy Fractions*/
    private Fractions fractions;

    /**Tablica przechowująca ilośc gwiazdek*/
    private static int[] stars = new int[40];
    /**Tablica przechowująca informacje o blokadzie poziomów*/
    static boolean[] unlockedLevels = new boolean[40];
    /**Tablica przechowująca informacje o blokadzie kategorii*/
    static boolean[] unlockedCategories = new boolean[4];

   /**
    * Funkcja wywoływana na początku gry; tworzy obiekt Game, jeśli nie istnieje i odczytuje dane z pliku
    * @param save plik z danymi do wczytania
    */
    static void initGame(File save) { // obiekt klasy Game może być utworzony tylko 1 raz
        if (game == null) {
            game = new Game();
            game.save = new File(save.toString(), "saved_data.txt");
            game.readFromFile();
        }
    }
    /**
     * Konstruktor obiektu klasy Game
     */
    private Game() { // obiekt klasy Game może być utworzony tylko wewnątrz tej klasy
        this.parity = new Parity();
        this.divisibility = new Divisibility();
        this.primeAndComposite = new PrimeAndComposite();
        this.fractions = new Fractions();
    }

    /**
     * Funkcja zwracająca obiekt danej kategorii w zależności od jej nazwy
     * @param category nazwa kategorii
     * @return pole klasy Game reprezentujące odpowiednią kategorię
     */
    static CategoryClass getCategory(String category) {
        switch (category) {
            case "Parzystość liczb":
                return game.parity;
            case "Podzielność liczb":
                return game.divisibility;
            case "Liczby pierwsze i złożone":
                return game.primeAndComposite;
            case "Ułamki właściwe i niewłaściwe":
                return game.fractions;
            default:
                return null;
        }

    }

    /**
     * Funkcja zapisująca dane po ukończeniu poziomu i odblokowująca kolejny poziom/kategorię
     * @param category bieżąca kategoria
     * @param whichLevel aktualny poziom
     * @param star liczba zdobytych gwiazdek
     */
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

    /**
     * Funkcja zwracająca liczbę gwiazdek przyznaną za dany poziom w kategorii
     * @param category bieżąca kategoria
     * @param whichLevel aktualny poziom
     * @return ilość przyznanych gwiazdek
     */
    static int getStars(CategoryClass category, int whichLevel ) {
        int starsAmount;
        int p = getElement(category);
        starsAmount = stars[whichLevel + p - 1];
        return starsAmount;
    }

    /**
     * Funkcja zwracająca stan poziomu (odblokowany/zablokowany)
     * @param category bieżąca kategoria
     * @param whichLevel aktualny poziom
     * @return true, jeżeli poziom jest odblokowany
     */
    static boolean getLevelStates(CategoryClass category, int whichLevel ) {
        boolean levelState;
        int p = getElement(category);
        levelState = unlockedLevels[whichLevel + p - 1];
        return levelState;
    }

    /**
     * Funkcja zwracająca stan kategorii (odblokowana/zablokowana)
     * @param category bieżąca kategoria
     * @return true, jeżeli kategoria jest odblokowana
     */
    static boolean getCategoryStates(int category) {
        boolean categoryState;
        categoryState = unlockedCategories[category - 1];
        return categoryState;
    }

    /**
     * Funkcja zwracająca zmienną pomocniczą do definiowania poziomów kategorii w tablicy
     * @param category bieżąca kategoria
     * @return zmienna pomocnicza do określania pozycji w tablicy
     */
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

    /**
     * Funkcja zapisująca dane gry do pliku
     */
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

    /**
     * Funkcja odczytująca dane gry z pliku
     */
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
    /**
     * Funkcja zwracająca obiekt klasy Game
     */
    public static Game getInstance() {
        return game;
    }

}
