package Item.Weapon.Rifle;

public enum AmmoType {
    ParaBellumAmmo(10),
    MagnumAmmo(100),
    AssaultAmmo(50);

    final public float damage;
    AmmoType(float damage) {
        this.damage = damage;
    }
}
