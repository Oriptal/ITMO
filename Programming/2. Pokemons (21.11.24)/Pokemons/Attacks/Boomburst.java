package Attacks;

import ru.ifmo.se.pokemon.*;

final public class Boomburst extends SpecialMove {
    public Boomburst() {
        super(Type.NORMAL, 140, 1);
    }

    @Override
    public String describe() {
        return "Применяет Boomburst";
    }
}
