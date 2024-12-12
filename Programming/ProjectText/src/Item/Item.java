package Item;

import Team.Human.ImaginaryHuman.ImaginaryHuman;

import java.util.Objects;

abstract public class Item {
    private int percentageDurability;
    final private int durabilityDelta;
    final private double weight;
    final private double volume;
    final public ItemType itemType;

    protected Item(int percentageDurability, double weight, double volume, ItemType itemType, int durabilityDelta) {
        this.percentageDurability = Math.min(100, Math.max(0, percentageDurability));
        this.weight = Math.max(0.1, weight);
        this.volume = Math.max(0.1, volume);
        this.itemType = itemType;
        this.durabilityDelta = durabilityDelta;
    }

    protected int getDurability() {
        return percentageDurability;
    }

    public double getWeight() {
        return weight;
    }

    public double getVolume() {
        return volume;
    }

    protected void changeDurability() {
        this.percentageDurability -= durabilityDelta;
        this.percentageDurability = Math.max(this.percentageDurability, 0);
    }

    protected void setDurability(int value) {
        this.percentageDurability = Math.min(100, value);
        this.percentageDurability = Math.max(this.percentageDurability, 0);
    }

    abstract public void use(ImaginaryHuman... user);

    protected abstract String describe();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return percentageDurability == item.percentageDurability && durabilityDelta == item.durabilityDelta && Double.compare(weight, item.weight) == 0 && Double.compare(volume, item.volume) == 0 && itemType == item.itemType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(percentageDurability, durabilityDelta, weight, volume, itemType);
    }

    @Override
    public String toString() {
        return "Item{" +
                "percentageDurability=" + percentageDurability +
                ", durabilityDelta=" + durabilityDelta +
                ", weight=" + weight +
                ", volume=" + volume +
                ", itemType=" + itemType +
                '}';
    }
}
