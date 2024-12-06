package Attacks;

import ru.ifmo.se.pokemon.*;

final public class Rest extends StatusMove {
    public Rest() {
        super(Type.PSYCHIC, 0, 1);
    }

    @Override
    public void applySelfEffects (Pokemon att) {
        att.setCondition((new Effect()).condition(Status.SLEEP).turns(2));
        att.setMod(Stat.HP, (int) -(att.getStat(Stat.HP) - att.getHP()));
    }

    @Override
    public String describe() {
        return "Применяет Rest";
    }
}
