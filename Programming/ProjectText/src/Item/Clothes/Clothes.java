package Item.Clothes;

import Item.ItemType;
import Item.Item;
import Item.IRepurchasing;

abstract public class Clothes extends Item implements IRepurchasing {
    double defence;

    protected Clothes(double weight, double volume, int durabilityDelta, double defence) {
        super(100, weight, volume, ItemType.CLOTHES, durabilityDelta);
        this.defence = defence;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "defence=" + defence +
                '}';
    }
}
