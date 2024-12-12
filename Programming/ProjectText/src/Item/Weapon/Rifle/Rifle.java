package Item.Weapon.Rifle;

import Item.Weapon.Weapon;
import Team.Human.ImaginaryHuman.ImaginaryHuman;

abstract public class Rifle extends Weapon {
    final private AmmoType ammoType;
    final private int maxAmmoNumber;
    final protected double accuracy;
    private int currentAmmoNumber;

    protected Rifle(int percentageDurability, double weight, double volume, int repairTime, AmmoType ammoType, int maxAmmoNumber, int repairCost, double accuracy, int durabilityDelta) {
        super(percentageDurability, weight, volume, repairTime, repairCost, durabilityDelta);
        this.ammoType = ammoType;
        this.maxAmmoNumber = maxAmmoNumber;
        this.currentAmmoNumber = this.maxAmmoNumber;
        this.accuracy = accuracy;
    }

    AmmoType getAmmoType() {
        return ammoType;
    }

    int getMaxAmmoNumber() {
        return maxAmmoNumber;
    }

    @Override
    protected boolean isReady() {  // винтовка не сломана, заряжена -> готова к выстрелу
        return (currentAmmoNumber > 0 && this.isRepaired());
    }

    @Override
    public double calculatePureDamage() {
        return ammoType.damage * accuracy;
    }

    private void reloadGun() {
        if (this.currentAmmoNumber == 0) {
            currentAmmoNumber = maxAmmoNumber;
        }
    }

    @Override
    public void use(ImaginaryHuman... humans) {
        var att = humans[0];
        var opp = humans[1];
        dealDamage(att, opp);
        reloadGun();
        startRepair(att);
    }

    @Override
    public String toString() {
        return "Rifle{" +
                "ammoType=" + ammoType +
                ", maxAmmoNumber=" + maxAmmoNumber +
                ", accuracy=" + accuracy +
                ", currentAmmoNumber=" + currentAmmoNumber +
                '}';
    }
}
