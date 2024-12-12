package Item.Medicines;

import Item.Item;
import Item.ItemType;
import Item.IRepurchasing;
import Team.Human.ImaginaryHuman.ImaginaryHuman;

abstract public class Medicines extends Item implements IRepurchasing {
    final private double healValue;
    final public int cost;

    protected Medicines(double healValue, int cost, double weight, double volume, int durabilityDelta) {
        super(100, weight, volume, ItemType.HEAL, durabilityDelta);
        this.healValue = healValue;
        this.cost = cost;
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

    @Override
    public String toString() {
        return "Heal{" +
                "healValue=" + healValue +
                ", cost=" + cost +
                '}';
    }
}
