package PokemonsClasses;

import Attacks.Boomburst;
import ru.ifmo.se.pokemon.Type;

public class Vibrava extends Trapinch {
    public Vibrava(String name, int lvl) {
        super(name, lvl);
        setStats(50,70,50,50,50,70);
        addType(Type.DRAGON);
        addMove(new Boomburst());
    }
}