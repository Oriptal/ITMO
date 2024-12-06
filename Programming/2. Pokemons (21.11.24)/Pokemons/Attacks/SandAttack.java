package Attacks;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

final public class SandAttack extends StatusMove {
    public SandAttack() {
        super(Type.GROUND, 0, 1);
    }

    @Override
    public void applyOppEffects (Pokemon opp) {
        opp.setMod(Stat.ACCURACY, -1);
    }

    @Override
    public String describe() {
        return "Применяет Sand Attack";
    }
}
