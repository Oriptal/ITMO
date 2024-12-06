package Attacks;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

final public class HammerArm extends PhysicalMove {
    public HammerArm() {
        super(Type.FIGHTING, 100, 0.9);
    }

    @Override
    public void applySelfEffects (Pokemon self) {
        self.setMod(Stat.SPEED, -1);
    }

    @Override
    public String describe() {
        return "Применяет Hammer Arm";
    }
}
