package Item.Medicines;

import Item.Item;
import Item.ItemType;
import Item.IRepurchasing;
import Team.Human.ImaginaryHuman.ImaginaryHuman;

abstract public class Medicines extends Item implements IRepurchasing {
    final private double healValue;

    protected Medicines(double healValue, int cost, double weight, double volume, int durabilityDelta) {
        super(weight, volume, ItemType.HEAL, durabilityDelta, cost);
        this.healValue = healValue;
    }

    void heal(ImaginaryHuman human) {
        if (this.getDurability() != 0) {
            human.changeHealth(this.healValue);
        }
    }

    @Override
    public void use(ImaginaryHuman... humans) {
        var user = humans[0];
        purchase(user, this);
        heal(user);
    }
}
