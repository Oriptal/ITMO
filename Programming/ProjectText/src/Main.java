import Team.Human.ImaginaryHuman.ImaginaryHumanType;
import Team.Team;

public class Main {
    public static void main(String... args) {
        Team policy = new Team("Bebra", ImaginaryHumanType.POLICY);
        Team bandit = new Team("Goida", ImaginaryHumanType.BANDIT);
        Battle battle = new Battle(policy, bandit);
        battle.start();
    }
}