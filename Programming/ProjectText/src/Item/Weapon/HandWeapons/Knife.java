package Item.Weapon.HandWeapons;

import Item.Weapon.Weapon;

final public class Knife extends Weapon {
    public Knife() {
        super(100, 1.0, 0.2, 1, 5, -3);
    }

    @Override
    public String describe() {
        return "Knife";
    }

    @Override
    protected double calculatePureDamage() {
        return 100;
    }
}
