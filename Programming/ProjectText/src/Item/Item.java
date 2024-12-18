package Item;

import Team.Human.ImaginaryHuman.ImaginaryHuman;

import java.util.Objects;
import java.util.Random;

abstract public class Item implements Describable{
    private int percentageDurability;
    final private int durabilityDelta;
    final private double weight;
    final private double volume;
    final public ItemType itemType;
    final protected int cost;

    protected Item(double weight, double volume, ItemType itemType, int durabilityDelta, int cost) {
        Random rand = new Random();
        this.percentageDurability = rand.nextInt(100) + 1;
        this.weight = Math.max(0.1, weight);
        this.volume = Math.max(0.1, volume);
        this.itemType = itemType;
        this.cost = cost;
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
}
