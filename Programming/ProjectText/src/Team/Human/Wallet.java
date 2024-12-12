package Team.Human;

import java.util.Random;

public class Wallet {
    int moneyAmount;

    Wallet(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    Wallet() {
        Random rand = new Random();
        this.moneyAmount = Math.max(rand.nextInt(50), rand.nextInt(50));
    }
}
