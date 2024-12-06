package PokemonsClasses;

import Attacks.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

final public class Volcanion extends Pokemon {
    public Volcanion(String name, int lvl) {
        super(name, lvl);
        setStats(80,110,120,130,90,70);
        setType(Type.FIRE, Type.WATER);
        setMove(new WaterPulse(), new Overheat(), new TakeDown(), new Bulldoze());
    }
}