package Item.Weapon.Rifle.Assault;

public enum AssaultType {
    AK_74M("AK-74M"),
    M4A1_S("M4A1-S");

    final String assaultName;

    AssaultType(String assaultName) {
            this.assaultName = assaultName;
    }

    @Override
    public String toString() {
        return assaultName;
    }
}