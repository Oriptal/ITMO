package Item.Weapon.Rifle.Pistol;

import Item.Weapon.Rifle.Rifle;
import Item.Weapon.Rifle.AmmoType;

public class Pistol extends Rifle {
    final private PistolType pistolType;
    public Pistol(PistolType pistolType) {
        super(100, getWeight(pistolType), getVolume(pistolType),
                getRepair(pistolType), AmmoType.ParaBellumAmmo, 10, 5, 1, -2);
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

    private static int getRepair(PistolType pistolType) {
        return 1;
    }

    @Override
    protected String describe() {
        return "Pistol " + pistolType.name();
    }
}

