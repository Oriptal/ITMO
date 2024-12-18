package Item.Weapon.HandWeapons;

import Item.Weapon.Weapon;

final public class Knife extends Weapon {
    public Knife() {
        super( 1.0, 0.2, 1, 5, -3, 20);
    }

    @Override
    public String toString() {
        return "Нож";
    }

    @Override
    protected double calculatePureDamage() {
        return 10;
    }
}
