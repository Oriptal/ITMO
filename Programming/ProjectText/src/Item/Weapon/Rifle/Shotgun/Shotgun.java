package Item.Weapon.Rifle.Shotgun;

import Item.Weapon.Rifle.AmmoType;
import Item.Weapon.Rifle.Rifle;

public class Shotgun extends Rifle {
    final private ShotgunType shotgunType;
    public Shotgun(ShotgunType shotgunType) {
        super(100, getWeight(shotgunType), getVolume(shotgunType), 
        getRepair(shotgunType), AmmoType.MagnumAmmo, 7, 20, 0.4, -5);
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
    public String describe() {
        return "Shotgun " + shotgunType.name();
    }
}

