package Attacks;

import ru.ifmo.se.pokemon.*;

final public class Overheat extends SpecialMove {
    public Overheat() {
        super(Type.FIRE, 130, 0.9);
    }

    @Override
    public void applySelfEffects (Pokemon att) {
        att.setMod(Stat.SPECIAL_ATTACK, -2);
    }

    @Override
    public String describe() {
        return "Применяет Overheat";
    }
}
