package Team;

import Team.Human.*;
import Team.Human.ImaginaryHuman.Bandit;
import Team.Human.ImaginaryHuman.ImaginaryHuman;
import Team.Human.ImaginaryHuman.ImaginaryHumanType;
import Team.Human.ImaginaryHuman.Policy;
import java.util.*;

public class Team {
    final private static String[] namesDB = {"Wolfgang", "Willow", "Wilson",
            "Webber", "Wurt", "Wigfrid", "Winona", "Wormwood", "Wortex", "Charlie"};
    final ArrayList<ImaginaryHuman> team = new ArrayList<>();
    final String teamName;

    public Team(String teamName, ImaginaryHumanType humanType) {
        System.out.println("Команда " + humanType.toString() + " именует себя \"" + teamName + "\".");
        Random rand = new Random();
        this.teamName = teamName;
        int n = rand.nextInt(10) + 1;
        System.out.printf("Состоит из %d человек. А именно: \n\n", n);
        ArrayList<String> nameList = new ArrayList<>(Arrays.asList(namesDB));
        for (int i = 0; i < n; i++) {
            int nameIndex = rand.nextInt(nameList.size());
            ImaginaryHuman newMember;
            if (humanType == ImaginaryHumanType.POLICY) {
                newMember = new Policy(nameList.get(nameIndex));
            } else {
                newMember = new Bandit(nameList.get(nameIndex));
            }
            this.team.add(newMember);
            nameList.remove(nameIndex);
        }
    }

    public boolean isAlive() {
        boolean res = false;
        for (ImaginaryHuman human : this.team) {
            res = res || (human.isAlive());
        }
        return res;
    }

    public void decreaseEffects() {
        for (ImaginaryHuman human : this.team) {
            for (Effect e: Effect.values()) {
                if (human.getEffectTime(e) > 0) {
                    human.addEffect(e, -1);
                }
            }
            human.repairInventory();
        }
    }

    public void moveAgainst(Team opp) {
        Random rand = new Random();
        Iterator<ImaginaryHuman> iterator = this.team.iterator();
        while (iterator.hasNext()) {
            ImaginaryHuman human = iterator.next();
            if (human.isAlive()) {
                if (!human.hasEffect(Effect.SHOKED)) {
                    int itemType = rand.nextInt(2); // 1 - Weapon, 0 - Heal
                    if (human.getHealth() == 100.0) itemType = 1;
                    if (itemType == 1) {
                        int indexOpp = rand.nextInt(opp.team.size());
                        ImaginaryHuman oppHuman = opp.team.get(indexOpp);
                        human.chooseRandom(itemType).use(human, oppHuman);
                        if (!oppHuman.isAlive()) {
                            System.out.println(oppHuman.description() + " умер. Ему было всего " + oppHuman.getAge() + ".");
                            human.raiseMoney(oppHuman);
                            opp.team.remove(indexOpp);
                        }
                        if (opp.team.isEmpty()) {
                            System.out.println(this.teamName + " убили вражескую команду.");
                            break;
                        }
                    } else {
                        human.chooseRandom(itemType).use(human);
                    }
                } else {
                    System.out.println(human.description() + " шокированный пропускает ход.");
                }
                System.out.println();
            } else {
                iterator.remove();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Team team1 = (Team) o;
        return Objects.equals(team, team1.team) && Objects.equals(teamName, team1.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, teamName);
    }
}
