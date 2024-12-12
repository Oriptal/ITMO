package Item.Weapon;

import Item.*;

public class Weapon extends Item {
    private int reloadTime;

    protected Weapon(int percentageDurability, double weight, double volume, int reloadTime) {
       super(percentageDurability, weight, volume); 
       this.reloadTime = Math.max(0, reloadTime);
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }
}
