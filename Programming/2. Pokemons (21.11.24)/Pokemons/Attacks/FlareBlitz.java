package Attacks;

import ru.ifmo.se.pokemon.*;

import java.util.Random;

final public class FlareBlitz extends PhysicalMove {
    public FlareBlitz() {
        super(Type.FIRE, 120, 1);
    }

    @Override
    public void applyOppEffects(Pokemon opp) {
        Random random = new Random();
        if (random.nextFloat(0, 1) < 0.1) {
            Effect.burn(opp);
        }
    }

    @Override
    public void applySelfDamage(Pokemon self, double damage) {
        self.setMod(Stat.HP, (int) damage / 3);
    }

    @Override
    public void applySelfEffects(Pokemon self) {
        Effect e = new Effect();
        e.condition(Status.NORMAL);
        if (self.getCondition() == Status.FREEZE) {
            self.setCondition(e);
        }
    }

    @Override
    public String describe() {
        return "Применяет Flare Blitz";
    }
}
