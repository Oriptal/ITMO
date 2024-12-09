package Item.Weapon.Shooting.Shotgun;

import Item.Weapon.Shooting.AmmoType;
import Item.Weapon.Shooting.Shooting;
import javax.swing.SwingConstants;

public class Shotgun extends Shooting {
    private ShotgunType shotgunType;
    Shotgun(ShotgunType shotgunType) {
        super(100, getWeight(shotgunType), getVolume(shotgunType), 
        getReload(shotgunType), AmmoType.MagnumAmmo, 7, 20);
        this.shotgunType = shotgunType;
    }

    private double getWeight(ShotgunType shotgunType) {
        return switch(shotgunType) {
            case M1014->3.82;
            case Nova->3.63;
        }
    }
}

enum ShotgunType {
    M1014,
    Nova;
}