package PokemonsClasses;

import Attacks.SandAttack;
final public class Flygon extends Vibrava {
    public Flygon(String name, int lvl) {
        super(name, lvl);
        setStats(80,100,80,80,80,100);
        addMove(new SandAttack());
    }
}