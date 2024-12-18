package Item.Weapon.Rifle;

public enum AmmoType {
    ParaBellumAmmo(10),
    MagnumAmmo(60),
    AssaultAmmo(50);

    final public float damage;
    AmmoType(float damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return name().substring(0, name().length() - 4);
    }
}
