package Item.Clothes;

import Item.ItemType;
import Item.Item;
import Item.IRepurchasing;
import Team.Human.ImaginaryHuman.ImaginaryHuman;

abstract public class Clothes extends Item implements IRepurchasing {
    final double defence;

    protected Clothes(double weight, double volume, int durabilityDelta, double defence, int cost) {
        super(weight, volume, ItemType.CLOTHES, durabilityDelta, cost);
        this.defence = defence;
    }

    public double reduceDamage(ImaginaryHuman user, double damage) {
        double reducedDamage = defence * damage;
        damage = damage - reducedDamage;
        this.changeDurability();
        if (this.getDurability() == 0) {
            this.purchase(user, this);
        }
        return damage;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "defence=" + defence +
                '}';
    }
}
