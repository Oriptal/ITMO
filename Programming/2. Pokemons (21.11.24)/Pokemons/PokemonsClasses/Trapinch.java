package PokemonsClasses;

import Attacks.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Trapinch extends Pokemon {
    public Trapinch(String name, int lvl) {
        super(name, lvl);
        setStats(45,100,45,45,45,10);
        setType(Type.GROUND);
        setMove(new Rest(), new DoubleTeam());
    }
}