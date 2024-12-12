package Item.Weapon.Shooting;

public enum AmmoType {
    ParaBellumAmmo(10),
    MagnumAmmo(100),
    AssaultAmmo(50);

    final float damage;
    AmmoType(float damage) {
        this.damage = damage;
    }
}
