package Team.Human.ImaginaryHuman;

import Item.Clothes.Clothes;
import Item.Medicines.Bandages;
import Item.Medicines.Beer;
import Item.Medicines.MedKit;
import Item.Item;
import Item.ItemType;
import Item.Weapon.Weapon;
import Team.Human.Effect;
import Team.Human.Human;

import java.util.*;

abstract public class ImaginaryHuman extends Human {
    private double health;
    final public double agility;
    final public double humanAccuracy;
    final public double strength;
    protected ArrayList<Item> inventory = new ArrayList<Item>();
    protected HashMap<String, Clothes> clothes = new HashMap<>();
    protected int[] effectTime = new int[4];
    protected ImaginaryHumanType humanType;

    final protected class Hands extends Weapon {
        public Hands() {
            super(100, 0.0, 0.0, 1, 2, -1);
        }

        @Override
        public String describe() {
            return "Hands";
        }

        @Override
        protected double calculatePureDamage() {
            return 1;
        }
    }


    public ImaginaryHuman(String name, ImaginaryHumanType imaginaryHumanType) {
        super(name);
        Random rand = new Random();
        this.health = 100.0;
        double loadCapacity = rand.nextDouble(20) + 10;
        double maxInventoryVolume = rand.nextDouble(20) + 10;
        this.agility = rand.nextDouble(2);
        this.strength = rand.nextDouble(2);
        this.humanAccuracy = rand.nextDouble();
        if (rand.nextInt(3) < 1) {
            this.effectTime[1] = rand.nextInt(10);
        }
        this.humanType = imaginaryHumanType;
        this.addRifle();
        this.addHandWeapon();
        this.addClothes();
        this.addHeal();
        double sumWeight = 0;
        double sumVolume = 0;
        for (Item item : this.inventory) {
            sumWeight += item.getWeight();
            sumVolume += item.getVolume();
        }
        if (sumWeight > loadCapacity) {
            effectTime[2] = -1;
        }
        if (sumVolume > maxInventoryVolume) {
            effectTime[3] = -1;
        }
    }

    public void setClothes(String clothesName, Clothes clothesValue) {
        this.clothes.put(clothesName, clothesValue);
    }

    abstract protected void addRifle();
    abstract protected void addClothes();
    abstract protected void addHandWeapon();

    protected void addHeal() {
        Random rand = new Random();
        int healType = rand.nextInt(3);
        this.inventory.add(switch (healType) {
            case 0->(new Bandages());
            case 1->(new Beer());
            case 2->(new MedKit());
            default -> throw new IllegalStateException("Unexpected value: " + healType);
        });
    }

    public void repairInventory() {
        for (Item item : this.inventory) {
            if (item.itemType == ItemType.WEAPON) {
                ((Weapon) item).repair();
            }
        }
    }

    public void changeHealth(double value) {
        this.health += value;
        this.health = Math.max(0.0, Math.min(this.health, 100.0));
    }

    public boolean hasEffect(Effect e) {
        return effectTime[e.ordinal()] != 0;
    }

    public int getEffectTime(Effect e) {
        return effectTime[e.ordinal()];
    }

    public void addEffect(Effect e, int value) {
        this.effectTime[e.ordinal()] += value;
    }

    public boolean isAlive() {
        return (this.health > 0);
    }

    public Item chooseRandom(int itemType) {
        ArrayList<Item> items = new ArrayList<Item>();
        for (Item item : this.inventory) {
            if (item.itemType == ItemType.values()[itemType]) {
                items.add(item);
            }
        }
        int randomIndex = (new Random()).nextInt(items.size());
        return items.get(randomIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ImaginaryHuman that = (ImaginaryHuman) o;
        return Double.compare(health, that.health) == 0 && Double.compare(agility, that.agility) == 0 && Double.compare(humanAccuracy, that.humanAccuracy) == 0 && Double.compare(strength, that.strength) == 0 && Objects.equals(inventory, that.inventory) && Objects.equals(clothes, that.clothes) && Objects.deepEquals(effectTime, that.effectTime) && humanType == that.humanType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), health, agility, humanAccuracy, strength, inventory, clothes, Arrays.hashCode(effectTime), humanType);
    }

    @Override
    public String toString() {
        return "ImaginaryHuman{" +
                "health=" + health +
                ", agility=" + agility +
                ", humanAccuracy=" + humanAccuracy +
                ", strength=" + strength +
                ", inventory=" + inventory +
                ", clothes=" + clothes +
                ", effectTime=" + Arrays.toString(effectTime) +
                ", humanType=" + humanType +
                '}';
    }
}
