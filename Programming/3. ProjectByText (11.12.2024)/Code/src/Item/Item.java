package Item;

public class Item {
    private int percentageDurability;
    final private double weight;
    final private double volume;

    protected Item(int percentageDurability, double weight, double volume) {
        this.percentageDurability = Math.min(100, Math.max(0, percentageDurability));
        this.weight = Math.max(0.1, weight);
        this.volume = Math.max(0.1, volume);
    }

    public int getDurability() {
        return percentageDurability;
    }

    protected void changeDurability(int delta) {
        this.percentageDurability -= delta;
        this.percentageDurability = Math.max(this.percentageDurability, 0);
    }

    public double getWeight() {
        return weight;
    }
    
    public double getVolume() {
        return volume;
    }
}
