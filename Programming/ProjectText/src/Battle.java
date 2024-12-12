import Team.Team;
import java.util.Random;

public class Battle {
    Team policy;
    Team bandit;

    public Battle(Team policy, Team bandit) {
        this.policy = policy;
        this.bandit = bandit;
    }

    public void start() {
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
