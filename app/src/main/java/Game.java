/**
 * Created by MonikaM on 2017-11-24.
 */

class Game {
    public Parity cat1;
    public Divisibility cat2;
    public PrimeAndComposite cat3;
    public Fractions cat4;

    public Game(){
        cat1 = new Parity();
        cat2 = new Divisibility();
        cat3 = new PrimeAndComposite();
        cat4 = new Fractions();
    }
}
