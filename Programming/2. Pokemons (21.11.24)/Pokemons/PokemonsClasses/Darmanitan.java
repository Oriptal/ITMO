package PokemonsClasses;

import Attacks.*;

final public class Darmanitan extends Darumaka {
    public Darmanitan(String name, int lvl) {
        super(name, lvl);
        setStats(105,140,55,30,55,95);
        addMove(new Bulldoze());
    }
}