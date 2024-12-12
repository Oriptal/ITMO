package Item.Weapon.HandWeapons;

import Item.Weapon.Weapon;

final public class Hands extends Weapon {
    public Hands() {
        super(100, 0.0, 0.0, 1, 2, -1);
    }

    @Override
    public String describe() {
        return "Hands";
    }

    @Override
    protected double calculatePureDamage() {
        return 1;
    }
}
