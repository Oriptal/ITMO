package Team.Human;

import Team.Human.ImaginaryHuman.ImaginaryHuman;

import java.util.Objects;
import java.util.Random;

abstract public class Human {
    final private String name;
    final private int age;
    final protected Wallet wallet;

    protected Human(String name, int age) {
        this.name = name;
        this.age = Math.max(18, Math.min(40, age));
        this.wallet = new Wallet();

    }

    protected Human(String name) {
        this(name, (new Random()).nextInt(23) + 18);
    }

    protected void changeMoney(int value) {
        wallet.moneyAmount -= value;
    }

    public void spendMoney(int value) {
        changeMoney(value);
        System.out.print(name + " отдал капитализму " + value + "долларов, ");
    }

    public void raiseMoney(ImaginaryHuman opp) {
        System.out.println(name + " поднял " + opp.getMoneyAmount() + " долларов с убитого " + opp.description() + ".");
        changeMoney(Math.max(0,-opp.getMoneyAmount()));
        opp.changeMoney(opp.getMoneyAmount());
    }

    public int getMoneyAmount() {
        return wallet.moneyAmount;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
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
}
