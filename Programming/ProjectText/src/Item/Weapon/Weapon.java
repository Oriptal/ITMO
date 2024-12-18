package Item.Weapon;

import Item.*;
import Team.Human.Effect;
import Team.Human.ImaginaryHuman.ImaginaryHuman;

import java.util.Random;

abstract public class Weapon extends Item {
    final protected int maxRepairTime;
    final public int repairCost;
    private int curRepairTime;

    protected Weapon(double weight, double volume, int repairTime, int repairCost, int durabilityDelta, int cost) {
       super(weight, volume, ItemType.WEAPON, durabilityDelta, cost);
       this.maxRepairTime = Math.max(0, repairTime);
       this.repairCost = repairCost;
    }

    protected void setRepairTime(int repairTime) {
        this.curRepairTime = Math.max(0, Math.min(this.maxRepairTime, repairTime));
    }

    protected int getRepairTime() {
        return this.curRepairTime;
    }

    public void repair() {
        if (getRepairTime() > 0) {
            this.curRepairTime--;
        }
    }

    public void startRepair(ImaginaryHuman att) {
        if (!this.isRepaired()) {
            if (this.getRepairTime() == 0 && att.getMoneyAmount() >= this.repairCost) {
                att.spendMoney(this.repairCost);
                this.setDurability(100);
                this.setRepairTime(this.maxRepairTime);
            }
            System.out.println("Чинит оружие.\n");
        }
    }

    protected void dealDamage(ImaginaryHuman att, ImaginaryHuman opp) {
        Random rand = new Random();
        if (this.isReady()) {
            double sumDamage = 0;
            int shootNumber = rand.nextInt(3) + 1;
            for (int i = 0; i < shootNumber; i++) {
                double chance = 1;
                for (int j = 0; j < i; j++) {
                    chance = Math.min(chance, rand.nextDouble());
                }
                chance *= att.humanAccuracy;
                if (att.hasEffect(Effect.SLOPPINES)) chance *= 0.85;
                chance /= opp.agility;
                if (opp.hasEffect(Effect.OVERWEIGHT)) chance *= 1.15;
                if (att.hasEffect(Effect.INTERSTED)) chance *= 1.15;
                sumDamage += chance*calculatePureDamage();
                this.changeDurability();
                if (rand.nextDouble() < 0.01) {
                    this.setDurability(0);
                    System.out.println("Оружие заклинило.");
                } else if (this.getDurability() == 0) {
                    System.out.println("Оружие сломалось.");
                    break;
                }
            }
            double oldDamage = sumDamage;
            sumDamage = opp.calcDamageWithClothes(sumDamage);
            System.out.print(att.description() + " атакует " + opp.description() + ", используя " + this.getDescription() + ".\n" + opp.description() + " ");
            opp.changeHealth(-sumDamage);
            System.out.printf("Заблокировав, при этом %.2f урона.\n", (oldDamage - sumDamage));
            if (rand.nextInt(3) < 1) {
                System.out.println(att.description() + " заинтересовывается.");
                att.addEffect(Effect.INTERSTED, rand.nextInt(10));
            }
            if (rand.nextInt(3) < 1) {
                System.out.println(opp.description() + " шокирован происходящим.");
                opp.addEffect(Effect.SHOKED, rand.nextInt(10));
            }
        }
    }

    @Override
    public void use(ImaginaryHuman... humans) {
        var att = humans[0];
        var opp = humans[1];
        dealDamage(att, opp);
        startRepair(att);
    }

    protected boolean isReady() {
        return this.isRepaired();
    }

    abstract protected double calculatePureDamage();

    protected boolean isRepaired() {
           return (this.curRepairTime == 0) && this.getDurability() > 0;
    }

    @Override
    public String toString() {
        return "Огнестрельный ";
    }
}
