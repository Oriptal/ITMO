package Item.Weapon.Rifle;

import Item.Weapon.Weapon;
import Team.Human.ImaginaryHuman.ImaginaryHuman;

abstract public class Rifle extends Weapon {
    final private AmmoType ammoType;
    final private int maxAmmoNumber;
    final protected double accuracy;
    private int currentAmmoNumber;

    protected Rifle(double weight, double volume, int repairTime, AmmoType ammoType, int maxAmmoNumber, int repairCost, double accuracy, int durabilityDelta, int cost) {
        super(weight, volume, repairTime, repairCost, durabilityDelta, cost);
        this.ammoType = ammoType;
        this.maxAmmoNumber = maxAmmoNumber;
        this.currentAmmoNumber = this.maxAmmoNumber;
        this.accuracy = accuracy;
    }

    protected AmmoType getAmmoType() {
        return ammoType;
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
        super.use(humans);
        reloadGun();
    }

    @Override
    public String getDescription() {
        return "Огнестрельный ";
    }
}
