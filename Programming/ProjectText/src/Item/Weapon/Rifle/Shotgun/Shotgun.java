package Item.Weapon.Rifle.Shotgun;

import Item.Weapon.Rifle.AmmoType;
import Item.Weapon.Rifle.Rifle;

public class Shotgun extends Rifle {
    final private ShotgunType shotgunType;
    public Shotgun(ShotgunType shotgunType) {
        super(getWeight(shotgunType), getVolume(shotgunType),
        getRepair(shotgunType), AmmoType.MagnumAmmo, 7, 20, 0.4, -5, 20);
        this.shotgunType = shotgunType;
    }

    private static double getWeight(ShotgunType shotgunType) {
        return switch(shotgunType) {
            case M1014->3.82;
            case Nova->3.63;
        };
    }

    private static double getVolume(ShotgunType shotgunType) {
        return switch(shotgunType) {
            case M1014->1.5;
            case Nova->1.2;
        };
    }

    private static int getRepair(ShotgunType shotgunType) {
        return switch(shotgunType) {
            case M1014->1;
            case Nova->2;
        };
    }

    @Override
    public double calculatePureDamage() {
        return AmmoType.MagnumAmmo.damage*accuracy*switch (shotgunType) {
            case M1014->4;
            case Nova->8;
        };
    }

    @Override
    public String getDescription() {
        return super.getDescription() + shotgunType.name();
    }

    @Override
    public String toString() {
        return super.toString() + "Дробовик " + shotgunType.name() + " с патронами " + this.getAmmoType().toString();
    }
}

