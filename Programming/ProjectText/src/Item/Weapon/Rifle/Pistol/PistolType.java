package Item.Weapon.Rifle.Pistol;

public enum PistolType {
    LUGER("Люгер"),
    MAKAROV("Макаров");

    final String pistolName;

    PistolType(String pistolName) {
        this.pistolName = pistolName;
    }

    @Override
    public String toString() {
        return pistolName;
    }
}
