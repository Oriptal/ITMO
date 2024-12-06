package Attacks;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

import java.util.Random;

final public class WaterPulse extends SpecialMove {
    public WaterPulse() {
        super(Type.WATER, 60, 1);
    }

    @Override
    public void applyOppEffects (Pokemon opp) {
        Random random = new Random();
        if (random.nextFloat(0, 1) < 0.2) {
            Effect.confuse(opp);
        }
    }

    @Override
    public String describe() {
        return "Применяет Water Pulse";
    }
}
