package Item.Weapon.Rifle.Pistol;

import Item.Weapon.Rifle.Rifle;
import Item.Weapon.Rifle.AmmoType;

public class Pistol extends Rifle {
    final private PistolType pistolType;
    public Pistol(PistolType pistolType) {
        super(getWeight(pistolType), getVolume(pistolType),
                1, AmmoType.ParaBellumAmmo, 10, 5, 1, -2, 10);
        this.pistolType = pistolType;
    }

    private static double getWeight(PistolType pistolType) {
        return switch(pistolType) {
            case LUGER->1.82;
            case MAKAROV->1.63;
        };
    }

    private static double getVolume(PistolType pistolType) {
        return switch(pistolType) {
            case LUGER->0.5;
            case MAKAROV->0.2;
        };
    }

    @Override
    public String getDescription() {
        return super.getDescription() + pistolType.toString();
    }

    @Override
    public String toString() {
        return super.toString() + "Пистолет " + pistolType.toString() + " с патронами " + this.getAmmoType().toString();
    }
}

