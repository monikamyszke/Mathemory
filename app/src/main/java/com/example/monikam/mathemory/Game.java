package com.example.monikam.mathemory;

import com.example.monikam.mathemory.Divisibility;
import com.example.monikam.mathemory.Fractions;
import com.example.monikam.mathemory.Parity;
import com.example.monikam.mathemory.PrimeAndComposite;

/**
 * Created by MonikaM on 2017-11-24.
 */

class Game {

    private static Game game;

    public Parity parity;
    public Divisibility divisibility;
    public PrimeAndComposite primeAndComposite;
    public Fractions fractions;

    public static void initGame(){ // obiekt klasy Game może być utworzony tylko 1 raz
        if (game == null){
            game = new Game();
        }
    }
// obiekt klasy Game może być utworzony tylko wewnątrz tej klasy
    private Game(){
        this.parity= new Parity();
        this.divisibility = new Divisibility();
        this.primeAndComposite = new PrimeAndComposite();
        this.fractions= new Fractions();
    }

    public static CategoryClass getCategory(String category){
        if(category.equals("Parzystość liczb")){
            return game.parity;
        }
        else if(category.equals("Podzielność liczb")){
            return game.divisibility;
        }
        else if(category.equals("Liczby pierwsze i złożone")){
            return game.primeAndComposite;
        }
        else if(category.equals("Ułamki właściwe i niewłaściwe")){
            return game.fractions;
        }
        else
            return null;

    }

    public static Game getInstance(){
        return game;
    }
}
