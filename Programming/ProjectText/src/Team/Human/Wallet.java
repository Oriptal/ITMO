package Team.Human;

import java.util.Objects;
import java.util.Random;

public class Wallet {
    int moneyAmount;

    Wallet() {
        Random rand = new Random();
        this.moneyAmount = Math.max(rand.nextInt(50), rand.nextInt(50));
    }

    @Override
    public String toString() {
        if (moneyAmount == 0) return "В кошельке нет ни копейки. ";
        else return "В кошельке осталось " + moneyAmount + " рублей. ";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return moneyAmount == wallet.moneyAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(moneyAmount);
    }
}
