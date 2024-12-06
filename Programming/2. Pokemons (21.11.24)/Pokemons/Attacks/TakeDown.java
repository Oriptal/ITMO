package Attacks;

import ru.ifmo.se.pokemon.*;

final public class TakeDown extends PhysicalMove {
    public TakeDown() {
        super(Type.NORMAL, 90, 0.85);
    }

    @Override
    public void applySelfDamage(Pokemon att, double damage) {
        att.setMod(Stat.HP, (int) damage / 4);
    }

    @Override
    public String describe() {
        return "Применяет Take Down";
    }
}
