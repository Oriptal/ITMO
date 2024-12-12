package Item.Weapon.Shooting;

import Item.Weapon.Weapon;

public class Shooting extends Weapon {
    final private AmmoType ammoType;
    final private int maxDistance;
    final private int maxAmmoNumber;
    private int currentAmmoNumber;

    protected Shooting(int percentageDurability, double weight, double volume, int reloadTime, AmmoType ammoType, int maxAmmoNumber, int maxDistance) {
        super(percentageDurability, weight, volume, reloadTime);
        this.ammoType = ammoType;
        this.maxAmmoNumber = maxAmmoNumber;
        this.maxDistance = maxDistance;
        this.currentAmmoNumber = this.maxAmmoNumber;
    }

    AmmoType getAmmoType() {
        return ammoType;
    }

    int getMaxAmmoNumber() {
        return maxAmmoNumber;
    }

    int getMaxDistance() {
        return maxDistance;
    }

    protected boolean isFull() {
        return (this.maxAmmoNumber == this.currentAmmoNumber);
    }

    public void shoot() {
        if (this.currentAmmoNumber > 0) {
            this.currentAmmoNumber--;
        }        
    };
}
