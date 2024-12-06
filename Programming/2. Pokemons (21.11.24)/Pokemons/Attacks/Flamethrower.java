package Attacks;

import ru.ifmo.se.pokemon.*;

import java.util.Random;

final public class Flamethrower extends SpecialMove {
    public Flamethrower() {
        super(Type.FIRE, 90, 1);
    }

    @Override
    public void applyOppEffects (Pokemon opp) {
        Random random = new Random();
        if (random.nextFloat(0, 1) < 0.1) {
            Effect.burn(opp);
        }
    }

    @Override
    public String describe() {
        return "Применяет Flamethrower";
    }
}
