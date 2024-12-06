package PokemonsClasses;

import Attacks.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Darumaka extends Pokemon {
    public Darumaka(String name, int lvl) {
        super(name, lvl);
        setStats(70,90,45,15,45,50);
        setType(Type.FIRE);
        setMove(new FlareBlitz(), new Flamethrower(), new HammerArm());
    }
}