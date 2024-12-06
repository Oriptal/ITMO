package Attacks;

import ru.ifmo.se.pokemon.*;

final public class Bulldoze extends PhysicalMove {
    public Bulldoze() {
        super(Type.GROUND, 60, 1);
    }

    @Override
    public void applyOppEffects (Pokemon opp) {
        opp.setMod(Stat.SPEED, -1);
    }

    @Override
    public String describe() {
        return "Применяет Bulldoze";
    }
}
