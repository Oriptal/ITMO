package Item.Weapon.Rifle.Assault;

import Item.Weapon.Rifle.Rifle;
import Item.Weapon.Rifle.AmmoType;

public class Assault extends Rifle {
    final private AssaultType assaultType;
    public Assault(AssaultType assaultType) {
        super(100, getWeight(assaultType), getVolume(assaultType),
                getRepair(assaultType), AmmoType.AssaultAmmo, 30, 30, 0.8, -3);
        this.assaultType = assaultType;
    }

    private static double getWeight(AssaultType assaultType) {
        return switch(assaultType) {
            case AK_74M->2.9;
            case M4A1_S->2.8;
        };
    }

    private static double getVolume(AssaultType assaultType) {
        return switch(assaultType) {
            case AK_74M->1.5;
            case M4A1_S->1.2;
        };
    }

    private static int getRepair(AssaultType assaultType) {
        return switch(assaultType) {
            case AK_74M->3;
            case M4A1_S->2;
        };
    }

    @Override
    protected String describe() {
        return "Assault " + assaultType.name();
    }
}

