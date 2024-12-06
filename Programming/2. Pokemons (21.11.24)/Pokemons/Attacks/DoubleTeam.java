package Attacks;

import ru.ifmo.se.pokemon.*;

final public class DoubleTeam extends StatusMove {
    public DoubleTeam() {
        super(Type.NORMAL, 0, 1);
    }

    @Override
    public void applySelfEffects (Pokemon att) {
        att.setMod(Stat.EVASION, 1);
    }

    @Override
    public String describe() {
        return "Применяет Double Team";
    }
}
