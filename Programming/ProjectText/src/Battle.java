import Team.Team;
import java.util.Random;

public record Battle(Team policy, Team bandit) {
    public void start() {
        System.out.println("Началась перестрелка:\n");
        Random rand = new Random();
        int queue = rand.nextInt(2);
        while (policy.isAlive() && bandit.isAlive()) {
            if (queue == 1) {
                policy.moveAgainst(bandit);
            } else {
                bandit.moveAgainst(policy);
            }
            policy.decreaseEffects();
            bandit.decreaseEffects();
            queue ^= 1;
        }
    }
}
