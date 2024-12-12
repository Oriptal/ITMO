package Team.Human;

import java.util.Objects;
import java.util.Random;

abstract public class Human {
    final private String name;
    final private int age;
    protected Wallet wallet;

    protected Human(String name, int age) {
        this.name = name;
        this.age = Math.max(18, Math.min(40, age));
        this.wallet = new Wallet();
    }

    protected Human(String name) {
        this(name, (new Random()).nextInt(23) + 18);
    }

    public void raiseMoney(int value) {
        wallet.moneyAmount -= value;
    }

    public int getMoneyAmount() {
        return wallet.moneyAmount;
    }

    String getName() {
        return this.name;
    }

    int getAge() {
        return this.age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age && Objects.equals(name, human.name) && Objects.equals(wallet, human.wallet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, wallet);
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", wallet=" + wallet +
                '}';
    }
}
